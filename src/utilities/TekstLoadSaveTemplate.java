package utilities;

import model.Item;
import model.database.LoadSaveStrategies.LoadSaveStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public abstract class TekstLoadSaveTemplate{

    private static final String FILEPATH = "./src/bestanden/";

    //loads the thingy into the thingy
    protected HashMap<String, Item> load(String load) {
        HashMap<String, Item> db = new HashMap<>();
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
                    db.put(naam, FormatData(naam, prijs,stock,verkocht));
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

    protected void save(HashMap<String, Item> data, String type){
        try (FileWriter writer = new FileWriter(FILEPATH + type + ".txt")) {
            StringBuilder out = new StringBuilder();
            for (Item i:
                 data.values()) {
                out.append(i.getBeschrijving() + "," + i.getPrijs() + "," + i.getInstock() + "," + i.getVerkocht() + "\n");
            }
            writer.write(out.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract Item FormatData(String naam, double prijs, int stock, int verkocht);

}
