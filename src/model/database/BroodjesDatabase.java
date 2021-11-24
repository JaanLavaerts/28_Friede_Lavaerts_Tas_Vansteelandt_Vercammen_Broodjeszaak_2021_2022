package model.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BroodjesDatabase {

    private static String FILEPATH = "./src/bestanden/";
    private HashMap<String, HashMap<String, Double>> broodjes = new HashMap<>();

    public HashMap<String, HashMap<String, Double>> getBroodjes() {
        return broodjes;
    }

    public void loadBroodjes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH + "broodjes.txt"))) {
            String line = null;
            try {
                line = reader.readLine();
                while (line != null) {
                    String[] lineElements = line.split(",");
                    String naam = lineElements[0];
                    double prijs = Double.parseDouble(lineElements[1]);
                    double stock = Double.parseDouble(lineElements[2]);
                    double verkocht = Double.parseDouble(lineElements[3]);
                    HashMap<String, Double> data = new HashMap<>();
                    data.put("prijs", prijs);
                    data.put("stock", stock);
                    data.put("verkocht", verkocht);
                    broodjes.put(naam, data);
                    line = reader.readLine();
                }
            } catch (IOException e) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
