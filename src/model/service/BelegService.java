package model.service;

import model.database.Text.BelegDatabaseTextFile;

public class BelegService extends ServiceClass {

    public BelegService() {
        setService();
    }

    @Override
    public void setService() {
        db = new BelegDatabaseTextFile();
    }
}
