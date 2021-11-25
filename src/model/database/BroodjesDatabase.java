package model.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BroodjesDatabase {

    private static String FILEPATH = "./src/bestanden/";
    private HashMap<String, HashMap<String, Number>> broodjes = new HashMap<>();
    private HashMap<String, HashMap<String, Number>> beleg = new HashMap<>();


    //constructor
    public BroodjesDatabase() {
        loadBroodjes();
        loadBeleg();
    }



    //getter
    public int getBroodStock(String broodsoort) {
        return (int) this.broodjes.get(broodsoort).get("stock");
    }

    //getter
    public double getBroodPrijs(String broodsoort) {
        return (double) this.broodjes.get(broodsoort).get("prijs");
    }

    //getter
    public int getBroodVerkocht(String broodsoort) {
        return(int)  this.broodjes.get(broodsoort).get("verkocht");
    }

    //getter
    public int getBelegStock(String Beleg) {
        return (int) this.beleg.get(Beleg).get("stock");
    }

    //getter
    public double getBelegPrijs(String Beleg) {
        return (double) this.beleg.get(Beleg).get("prijs");
    }

    //getter
    public int getBelegVerkocht(String Beleg) {
        return (int) this.beleg.get(Beleg).get("verkocht");
    }

    //getter, liefst niet gebruiken buiten deze klasse, gebruik andere getters
    public HashMap<String, HashMap<String, Number>> getBroodjes() {
        return broodjes;
    }

    //getter, liefst niet gebruiken buiten deze klasse, gebruik andere getters
    public HashMap<String, HashMap<String, Number>> getBeleg() {
        return beleg;
    }

    //loader
    public void loadBroodjes() {
        load("broodjes", broodjes);
    }

    //loader
    public void loadBeleg() {
        load("beleg", beleg);
    }

    //loads the thingy into the thingy
    private void load(String load,  HashMap<String, HashMap<String, Number>> loadTo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH + load + ".txt"))) {
            String line;
            try {
                line = reader.readLine();
                while (line != null) {
                    String[] lineElements = line.split(",");
                    String naam = lineElements[0];
                    double prijs = Double.parseDouble(lineElements[1]);
                    //waarom doubles ipv ints?
                    int stock = Integer.parseInt(lineElements[2]);
                    int verkocht = Integer.parseInt(lineElements[3]);
                    HashMap<String, Number> data = new HashMap<>();
                    data.put("prijs", prijs);
                    data.put("stock", stock);
                    data.put("verkocht", verkocht);
                    loadTo.put(naam, data);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                //geen idee wat dit doet maar anders kan ik niet pushen
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
