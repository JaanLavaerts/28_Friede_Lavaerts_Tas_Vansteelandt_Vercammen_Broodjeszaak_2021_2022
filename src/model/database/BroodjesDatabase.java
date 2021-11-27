package model.database;

public class BroodjesDatabase extends Database{

    //constructor
    public BroodjesDatabase() {
        loadBroodjes();
    }

    //loader
    public void loadBroodjes() {
        load("broodjes");
    }

}
