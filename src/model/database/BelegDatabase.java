package model.database;

public class BelegDatabase extends Database{

    //constructor
    public BelegDatabase() {
        loadBeleg();
    }

    //loader
    public void loadBeleg() {
        load("beleg");
    }
}
