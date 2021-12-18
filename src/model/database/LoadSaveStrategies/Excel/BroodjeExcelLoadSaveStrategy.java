package model.database.LoadSaveStrategies.Excel;

import model.BelegSoort;
import model.Broodje;
import model.Item;
import model.database.LoadSaveStrategies.LoadSaveStrategy;
import utilities.ExcelLoadSaveTemplate;

import java.util.HashMap;

public class BroodjeExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy {
    @Override
    public HashMap<String, Item> load() {
        return load("broodjes");
    }

    @Override
    public void save(HashMap<String, Item> data) {
        save(data);
    }

    @Override
    protected Item FormatData(String naam, double prijs, int stock, int verkocht) {
        return new Broodje(naam, prijs, stock, verkocht);

    }
}
