package model.database.LoadSaveStrategies.Text;

import model.BelegSoort;
import model.Item;
import model.database.LoadSaveStrategies.LoadSaveStrategy;
import utilities.TekstLoadSaveTemplate;

import java.util.HashMap;

public class BelegTekstLoadSaveStrategy extends TekstLoadSaveTemplate implements LoadSaveStrategy{

    @Override
    public HashMap<String, Item> load() {
        return load("beleg");
    }

    @Override
    public void save(HashMap<String, Item> data) {
        save(data, "beleg");
    }

    protected Item FormatData(String naam, double prijs, int stock, int verkocht){
        return new BelegSoort(naam, prijs, stock, verkocht);
    }

}
