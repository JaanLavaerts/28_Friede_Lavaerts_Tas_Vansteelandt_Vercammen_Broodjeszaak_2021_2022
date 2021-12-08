package model.database.LoadSaveStrategies;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoadSaveStrategy {

    public HashMap<String, Object> load();
    public void save(HashMap<String, Object> data);

}
