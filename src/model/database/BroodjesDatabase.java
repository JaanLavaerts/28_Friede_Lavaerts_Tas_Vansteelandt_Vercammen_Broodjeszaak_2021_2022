package model.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BroodjesDatabase {

    private static String FILEPATH = "./src/bestanden/";
    private HashMap<String, ArrayList<Double>> broodjes = new HashMap<>();

    public HashMap<String, ArrayList<Double>> getBroodjes() {
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
                    ArrayList<Double> data = new ArrayList<>();
                    data.add(prijs);
                    data.add(stock);
                    data.add(verkocht);
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
