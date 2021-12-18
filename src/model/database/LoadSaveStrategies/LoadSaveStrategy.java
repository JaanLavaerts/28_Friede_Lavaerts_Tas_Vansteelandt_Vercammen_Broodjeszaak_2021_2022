package model.database.LoadSaveStrategies;

import model.Item;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoadSaveStrategy {

    HashMap<String, Item> load();
    void save(HashMap<String, Item> data);

}
