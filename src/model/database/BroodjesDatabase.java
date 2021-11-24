package model.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BroodjesDatabase {

    private static String FILEPATH = "./src/bestanden/";
    private HashMap<String, ArrayList<Integer>> broodjes = new HashMap<>();

    public void loadBroodjes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))) {
            String line = null;
            try {
                line = reader.readLine();
                while (line != null) {
                    String[] lineElements = line.split(",");
                    String naam = lineElements[0];
                    int prijs = Integer.parseInt(lineElements[1]);
                    int stock = Integer.parseInt(lineElements[2]);
                    int verkocht = Integer.parseInt(lineElements[3]);
                    ArrayList<Integer> data = new ArrayList<>();
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
