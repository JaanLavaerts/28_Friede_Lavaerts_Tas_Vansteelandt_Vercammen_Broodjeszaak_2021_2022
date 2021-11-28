package model.service;

import model.database.Database;

import java.util.HashMap;

public abstract class ServiceClass {

    protected Database db;

    public abstract void setService();

    public HashMap<String, HashMap<String, Number>> getAll() {
        return db.load();
    }

    public Number getStock(String soort) {
        return getAll().get(soort).get("stock");
    }

    public Number getPrijs(String soort) {
        return getAll().get(soort).get("prijs");
    }

    public Number getVerkocht(String soort) {
        return getAll().get(soort).get("verkocht");
    }
}
