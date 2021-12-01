package model.database;

import java.util.ArrayList;
import java.util.HashMap;

public interface Database {

    public HashMap<String, Object> load();

    public void save(HashMap<String, Object> data);

}
