package model.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BroodjesDatabase {

    private static String FILEPATH = "./src/bestanden/";
    private HashMap<String, HashMap<String, Double>> broodjes = new HashMap<>();
    private HashMap<String, HashMap<String, Double>> beleg = new HashMap<>();


    //constructor
    public BroodjesDatabase() {
        loadBroodjes();
        loadBeleg();
    }



    //getter
    public double getBroodStock(String broodsoort) {
        return this.broodjes.get(broodsoort).get("stock");
    }

    //getter
    public double getBroodPrijs(String broodsoort) {
        return this.broodjes.get(broodsoort).get("prijs");
    }

    //getter
    public double getBroodVerkocht(String broodsoort) {
        return this.broodjes.get(broodsoort).get("verkocht");
    }

    //getter
    public double getBelegStock(String Beleg) {
        return this.broodjes.get(Beleg).get("stock");
    }

    //getter
    public double getBelegPrijs(String Beleg) {
        return this.broodjes.get(Beleg).get("prijs");
    }

    //getter
    public double getBelegVerkocht(String Beleg) {
        return this.broodjes.get(Beleg).get("verkocht");
    }

    //getter, liefst niet gebruiken buiten deze klasse, gebruik getters
    public HashMap<String, HashMap<String, Double>> getBroodjes() {
        return broodjes;
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
    private void load(String load,  HashMap<String, HashMap<String, Double>> loadTo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH + load + ".txt"))) {
            String line;
            try {
                line = reader.readLine();
                while (line != null) {
                    String[] lineElements = line.split(",");
                    String naam = lineElements[0];
                    double prijs = Double.parseDouble(lineElements[1]);
                    //waarom doubles ipv ints?
                    double stock = Double.parseDouble(lineElements[2]);
                    double verkocht = Double.parseDouble(lineElements[3]);
                    HashMap<String, Double> data = new HashMap<>();
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
