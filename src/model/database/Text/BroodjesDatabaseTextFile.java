/*
 * @author Maarten Vercammen
 */
package model.database.Text;

import model.database.Database;

import java.util.HashMap;

public class BroodjesDatabaseTextFile extends LoaderTextFileLoader implements Database {

    @Override
    public HashMap<String, HashMap<String, Number>> load() {
        return load("broodjes");
    }

    @Override
    public void safe(HashMap<String, HashMap<String, Number>> data) {

    }

}
