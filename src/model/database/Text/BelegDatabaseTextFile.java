package model.database.Text;

import model.Beleg;
import model.database.Database;

import java.util.HashMap;

public class BelegDatabaseTextFile extends LoaderTextFileLoader implements Database {

    @Override
    public HashMap<String, Object> load() {
        return load("beleg");
    }

    @Override
    public void save(HashMap<String, Object> data) {

    }

    protected Object FormatData(String naam, double prijs, int stock, int verkocht){
        return new Beleg(naam, prijs, stock, verkocht);
    }

}
