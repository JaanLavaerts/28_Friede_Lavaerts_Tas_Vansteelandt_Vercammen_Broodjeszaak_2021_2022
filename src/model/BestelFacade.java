package model;

import model.database.Service;

import java.util.ArrayList;
import java.util.HashMap;

public class BestelFacade implements Subject  {

    HashMap<BestellingEvents, ArrayList<Observer>> observers = new HashMap<>();
    Bestelling bestelling;

    public void toevoegenBroodje(String name){
        Broodje broodje = Service.getInstance().getBroodjesService().getBroodjeByName(name);
        bestelling = new Bestelling();
        bestelling.addBroodje(broodje);
        notifyObserver(BestellingEvents.TOEVOEGEN_BROODJE);
    }

    public ArrayList<Bestellijn> getLijstBestellijnen(){
        return bestelling.getLijstBestellijnen();
    }

    public HashMap<String, Broodje> getVoorraadlijstBroodjes(){
        return Service.getInstance().getBroodjesService().getVoorraadlijstBroodjes();
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
        for (Bestellijn bestellijn:
                bestellingInKitchen.getLijstBestellijnen()) {
            out.append(bestellijn.getBroodje());
            out.append(": ");
            out.append(bestellijn.getBeleg());
            out.append("\n");
        }
        return out.toString();
    }
}
