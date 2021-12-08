package model.database.LoadSaveStrategies;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoadSaveStrategy {

    HashMap<String, Object> load();
    void save(HashMap<String, Object> data);

}
