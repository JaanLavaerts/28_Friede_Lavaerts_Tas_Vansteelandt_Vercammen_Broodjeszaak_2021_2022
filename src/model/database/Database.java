package model.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Database {

    private HashMap<String, HashMap<String, Number>> db = new HashMap<>();
    private static String FILEPATH = "./src/bestanden/";

    //loads the thingy into the thingy
    protected void load(String load) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH + load + ".txt"))) {
            String line;
            try {
                line = reader.readLine();
                while (line != null) {
                    String[] lineElements = line.split(",");
                    String naam = lineElements[0];
                    double prijs = Double.parseDouble(lineElements[1]);
                    int stock = Integer.parseInt(lineElements[2]);
                    int verkocht = Integer.parseInt(lineElements[3]);
                    HashMap<String, Number> data = new HashMap<>();
                    data.put("prijs", prijs);
                    data.put("stock", stock);
                    data.put("verkocht", verkocht);
                    this.db.put(naam, data);
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

    //getter
    public int getStock(String soort) {
        return (int) this.db.get(soort).get("stock");
    }

    //getter
    public double getPrijs(String soort) {
        return (double) this.db.get(soort).get("prijs");
    }

    //getter
    public int getVerkocht(String soort) {
        return (int)  this.db.get(soort).get("verkocht");
    }

    //getter, liefst niet gebruiken buiten deze klasse, gebruik andere getters
    public HashMap<String, HashMap<String, Number>> getDatabase() {
        return db;
    }
}
