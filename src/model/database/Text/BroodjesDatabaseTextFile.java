/*
 * @author Maarten Vercammen
 */
package model.database.Text;

import model.Beleg;
import model.Brood;
import model.database.Database;

import java.util.HashMap;

public class BroodjesDatabaseTextFile extends LoaderTextFileLoader implements Database {

    @Override
    public HashMap<String, Object> load() {
        return load("broodjes");
    }

    @Override
    public void save(HashMap<String, Object> data) {

    }

    protected Object FormatData(String naam, double prijs, int stock, int verkocht){
        return new Brood(naam, prijs, stock, verkocht);
    }

}
