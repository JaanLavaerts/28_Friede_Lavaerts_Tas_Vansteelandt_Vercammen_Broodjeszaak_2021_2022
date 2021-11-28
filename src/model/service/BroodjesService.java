package model.service;

import model.database.Text.BroodjesDatabaseTextFile;

public class BroodjesService extends ServiceClass {

    public BroodjesService() {
        setService();
    }

    @Override
    public void setService() {
        db = new BroodjesDatabaseTextFile();
    }
}
