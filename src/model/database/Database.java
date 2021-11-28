package model.database;

import java.util.HashMap;

public interface Database {

    public HashMap<String, HashMap<String, Number>> load();

    public void safe(HashMap<String, HashMap<String, Number>> data);

}
