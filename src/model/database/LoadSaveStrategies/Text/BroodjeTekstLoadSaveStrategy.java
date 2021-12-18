/*
 * @author Maarten Vercammen
 */
package model.database.LoadSaveStrategies.Text;

import model.Broodje;
import model.Item;
import model.database.LoadSaveStrategies.LoadSaveStrategy;
import utilities.TekstLoadSaveTemplate;

import java.util.HashMap;

public class BroodjeTekstLoadSaveStrategy extends TekstLoadSaveTemplate implements LoadSaveStrategy {

    @Override
    public HashMap<String, Item> load() {
        return load("broodjes");
    }

    @Override
    public void save(HashMap<String, Item> data) {
        save(data, "broodjes");
    }

    protected Item FormatData(String naam, double prijs, int stock, int verkocht){
        return new Broodje(naam, prijs, stock, verkocht);
    }

}
