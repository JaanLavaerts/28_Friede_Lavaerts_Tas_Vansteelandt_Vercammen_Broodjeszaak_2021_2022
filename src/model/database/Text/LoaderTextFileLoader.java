package model.database.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public abstract class LoaderTextFileLoader {

    private static final String FILEPATH = "./src/bestanden/";

    //loads the thingy into the thingy
    protected HashMap<String, HashMap<String, Number>> load(String load) {
        HashMap<String, HashMap<String, Number>> db = new HashMap<>();
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
                    db.put(naam, data);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                //geen idee wat dit doet maar anders kan ik niet pushen
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }

}