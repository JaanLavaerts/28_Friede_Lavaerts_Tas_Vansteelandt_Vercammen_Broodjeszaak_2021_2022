package model.database.Text;

import model.database.Database;

import java.util.HashMap;

public class BelegDatabaseTextFile extends LoaderTextFileLoader implements Database {

    @Override
    public HashMap<String, HashMap<String, Number>> load() {
        return load("beleg");
    }

    @Override
    public void safe(HashMap<String, HashMap<String, Number>> data) {

    }

}
