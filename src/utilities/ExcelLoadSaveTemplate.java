package utilities;

import jxl.read.biff.BiffException;
import model.database.LoadSaveStrategies.LoadSaveStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class ExcelLoadSaveTemplate extends ExcelPlugin{

    public HashMap<String, Object> load(String load) {

        HashMap<String, Object> data = new HashMap<>();

        try {
            // first list has all the rows, second list has all the elements in a row
            ArrayList<ArrayList<String>> info = read(new File(String.format("./src/bestanden/%s.xls", load)));
            for (ArrayList<String> row:
                 info) {
                Object o = FormatData(row.get(0), Double.parseDouble(row.get(1)), Integer.parseInt(row.get(2)) , Integer.parseInt(row.get(3)));
                data.put(row.get(0), o);
            }

        }catch (Exception e){
            throw new IllegalArgumentException("Fout bij inlezen van bestand beleg.xls of broodjes.xls");
        }
        return data;
    }


    public void save(HashMap<String, Object> data) {

    }

    protected abstract Object FormatData(String naam, double prijs, int stock, int verkocht);
}
