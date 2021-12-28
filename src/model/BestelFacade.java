package model;

import controller.BestelViewController;
import javafx.scene.control.Alert;
import model.database.Service;
import model.kortingen.GeenKorting;
import model.kortingen.Korting;
import model.kortingen.KortingEnum;
import model.kortingen.KortingFactory;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BestelFacade implements Subject  {

    HashMap<BestellingEvents, ArrayList<Observer>> observers = new HashMap<>();
    Queue<Bestelling> queue = new ArrayDeque<>();
    Bestelling currentBestelling;
    Bestelling bestellingInKitchen;

    public void startBestelling(){
        currentBestelling = new Bestelling();
        currentBestelling.startBestelling();
        notifyObserver(BestellingEvents.TOEVOEGEN_BROODJE);
    }


    public void toevoegenBroodje(String name){
        currentBestelling.toevoegenBroodje();
        Broodje broodje = Service.getInstance().getBroodjesService().getBroodjeByName(name);
        currentBestelling.addBroodje(broodje);
        notifyObserver(BestellingEvents.TOEVOEGEN_BROODJE);
    }

    public void toevoegenBeleg(String name, Bestellijn bestellijn) {
        currentBestelling.toevoegenBeleg();
        BelegSoort belegSoort = Service.getInstance().getBelegService().getBelegByName(name);
        bestellijn.addBeleg(belegSoort);

        notifyObserver(BestellingEvents.TOEVOEGEN_BELEG);
    }

    public ArrayList<Bestellijn> getLijstBestellijnen(){
        return currentBestelling.getLijstBestellijnen();
    }

    public HashMap<String, Broodje> getVoorraadlijstBroodjes(){
        return Service.getInstance().getBroodjesService().getVoorraadlijstBroodjes();
    }

    public HashMap<String, BelegSoort> getVoorraadlijstBeleg() {
        return Service.getInstance().getBelegService().getVoorraadlijstBeleg();
    }

    @Override
    public void addObserver(Observer observer, BestellingEvents bestellingEvents) {
        if(!observers.containsKey(bestellingEvents)){
            observers.put(bestellingEvents, new ArrayList<>());
        }
        observers.get(bestellingEvents).add(observer);
    }

    @Override
    public void removeObserver(Observer observer, BestellingEvents bestellingEvents) {
        observers.get(bestellingEvents).remove(observer);
    }

    @Override
    public void notifyObserver(BestellingEvents bestellingEvents) {
        for(Observer o: observers.get(bestellingEvents)){
            o.update(currentBestelling,bestellingEvents);
        }
    }


    public int getAantalBroodjes() {
        return currentBestelling.getLijstBestellijnen().size();
    }

    public void addSameBroodje(Bestellijn bestellijn) {
        Broodje broodje = Service.getInstance().getBroodjesService().getBroodjeByName(bestellijn.getBroodje());
        if(Service.getInstance().getBroodjesService().getStockVanSoort(broodje.getBeschrijving()) <= 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Broodje niet meer in stock!");
            alert.show();
            return;
        }
        Bestellijn b = new Bestellijn(broodje);
        String[] beleg = bestellijn.getBeleg().split(" ");
        for(int i = 0; i < beleg.length; i++){
            if(Service.getInstance().getBelegService().getStockVanSoort(beleg[i]) <= 0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Beleg niet meer in stock!");
                alert.show();
                return;
            }
            BelegSoort belegSoort = Service.getInstance().getBelegService().getBelegByName(beleg[i]);
            b.addBeleg(belegSoort);
        }
        currentBestelling.addBestellijn(bestellijn);
        notifyObserver(BestellingEvents.TOEVOEGEN_BROODJE);
    }

    public void removeBroodje(Bestellijn bestellijn) {
        currentBestelling.removeBroodje(bestellijn);
        Service.getInstance().getBroodjesService().aanpassenVoorraadBroodje(bestellijn.getBroodje(), +1);
        String[] beleg = bestellijn.getBeleg().split(" ");
        if(beleg.length > 1) {
            for (int i = 0; i < beleg.length; i++) {
                Service.getInstance().getBelegService().aanpassenVoorraadBeleg(beleg[i], +1);
            }
        }
        notifyObserver(BestellingEvents.TOEVOEGEN_BROODJE);
    }

    public Bestelling getCurrentBestelling() {
        return currentBestelling;
    }

    public int volgNumber() {
        return Bestelling.getVOLGNR();
    }

    public void afsluitenBestelling() {
        currentBestelling.afsluiten();
        notifyObserver(BestellingEvents.AFSLUIT);
    }

    public double getPrice(String korting) {
        String kortingToUse = "";
        for (KortingEnum kortingEnum:
             KortingEnum.values()) {
            if(kortingEnum.NAME.equals(korting)){
                kortingToUse =  kortingEnum.PATH;
            }
        }
        Korting kortingObject = KortingFactory.getKorting(kortingToUse);
        return kortingObject.getPrice(currentBestelling);
    }

    public void addBestellingToQueue() {
        currentBestelling.zendNaarKeuken();
        queue.add(currentBestelling);
        notifyObserver(BestellingEvents.TO_KITCHEN);
    }

    public void payBestelling() {
        currentBestelling.betalen();
    }

    public int getAmountInQueue() {
        return queue.size();
    }

    public void updateSold() {
        ArrayList<Bestellijn> bestellijnen = currentBestelling.getLijstBestellijnen();
        for (Bestellijn bestellijn:
             bestellijnen) {
            updateSoldBroodje(bestellijn.getBroodje());
            String[] beleg = bestellijn.getBeleg().split(" ");
            if(beleg.length > 0) {
                for (String b :
                        beleg) {
                    updateSoldBeleg(b);
                }
            }
        }
        notifyObserver(BestellingEvents.TO_KITCHEN);
    }

    private void updateSoldBeleg(String beleg) {
        Service.getInstance().getBelegService().updateSold(beleg);
    }

    private void updateSoldBroodje(String broodje) {
        Service.getInstance().getBroodjesService().updateSold(broodje);
    }

    public void bestellingAfronden() {
        currentBestelling.afgewerkt();
        bestellingInKitchen = null;
        notifyObserver(BestellingEvents.AFRONDEN_BESTELLING);
    }

    public void volgendeBestelling() {
        bestellingInKitchen = queue.poll();
        bestellingInKitchen.startBereiding();
        notifyObserver(BestellingEvents.TO_KITCHEN);
    }

    public int getAmountBroodjesForBestellingInQueue() {
        return bestellingInKitchen.getLijstBestellijnen().size();
    }

    public int getVolgnumberInQueue() {
        return bestellingInKitchen.getVolgnr();
    }

    public String getKitchenOrder() {
        StringBuilder out = new StringBuilder();
        HashMap<String, Integer> bestlijnAantal = new HashMap<String, Integer>();
        for (Bestellijn bestellijn:
                bestellingInKitchen.getLijstBestellijnen()) {
            String lijn = bestellijn.getBroodje() + ": " + formatBeleg(bestellijn.getBeleg());
            if(bestlijnAantal.containsKey(lijn)){
                bestlijnAantal.put(lijn, bestlijnAantal.get(lijn) + 1);
            }else {
                bestlijnAantal.put(lijn, 1);
            }
        }
        for (String lijn:
             bestlijnAantal.keySet()) {
            out.append(bestlijnAantal.get(lijn));
            out.append("x ");
            out.append(lijn);
            out.append("\n");
        }
        return out.toString();
    }



    private boolean bestelijnIsTheSame(Bestellijn b1, Bestellijn b2){
        List<Integer> list = new ArrayList<>();
        if(!b1.getBroodje().equals(b2.getBroodje())){
            return false;
        }
        ArrayList<String> beleg1 = new ArrayList<>(Arrays.asList(b1.getBeleg().split(" "))).stream().sorted().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<String> beleg2 = new ArrayList<>(Arrays.asList(b2.getBeleg().split(" "))).stream().sorted().collect(Collectors.toCollection(ArrayList::new));
        int minimumLeght = Math.min(beleg1.size(), beleg2.size());
        for (int i = 0; i < minimumLeght; i++){
            if(!beleg1.get(i).equals(beleg2.get(i))){
                return false;
            }
        }
        return true;
    }

    private String formatBeleg(String beleg){
        StringBuilder out = new StringBuilder();
        ArrayList<String> sortedbeleg = new ArrayList<>(Arrays.asList(beleg.split(" "))).stream().sorted().collect(Collectors.toCollection(ArrayList::new));
        HashMap<String, Integer> belegcounter = new HashMap<>();
        for (String b:
                sortedbeleg) {
            if(belegcounter.containsKey(b)){
                belegcounter.put(b, belegcounter.get(b) + 1);
            }else {
                belegcounter.put(b, 1);
            }
        }
        for (String lijn:
                belegcounter.keySet()) {
            out.append(belegcounter.get(lijn));
            out.append("x ");
            out.append(lijn);
            out.append(", ");
        }
        out.delete(out.length() - 2, out.length() - 1);
        return out.toString();
    }

    public void saveSettings(String broodjesDatabase, String belegDatabase, String kortingChoice) {
        Properties properties = new Properties();
        properties.setProperty("broodjesDatabase", broodjesDatabase.replace(".", ""));
        properties.setProperty("belegDatabase", belegDatabase.replace(".", ""));
        properties.setProperty("kortingChoice", kortingChoice);
        try {
            File file = new File("src/bestanden/settings.properties");
            FileOutputStream fileOut = new FileOutputStream(file);
            properties.store(fileOut, "Favorite Things");
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getSettingsBroodjesDatabase() {
        try {
            Properties properties = new Properties();
            InputStream is = new FileInputStream("src/bestanden/settings.properties");
            properties.load(is);
            String prop = properties.getProperty("broodjesDatabase");
            String data = prop.substring(0,prop.length() - 3) + "." + prop.substring(prop.length() -3);
            return data;
        } catch (IOException e) {
            throw new DomainException(" file not found" + e.getMessage());
        }

    }

    public String getSettingsBelegDatabase() {
        try {
            Properties properties = new Properties();
            InputStream is = new FileInputStream("src/bestanden/settings.properties");
            properties.load(is);
            String prop = properties.getProperty("belegDatabase");
            String data = prop.substring(0,prop.length() - 3) + "." + prop.substring(prop.length() -3);
            return data;
        } catch (IOException e) {
            throw new DomainException(" file not found" + e.getMessage());
        }
    }

    public String getSettingsKorting() {
        try {
            Properties properties = new Properties();
            InputStream is = new FileInputStream("src/bestanden/settings.properties");
            properties.load(is);
            return properties.getProperty("kortingChoice");
        } catch (IOException e) {
            throw new DomainException(" file not found" + e.getMessage());
        }
    }
}
