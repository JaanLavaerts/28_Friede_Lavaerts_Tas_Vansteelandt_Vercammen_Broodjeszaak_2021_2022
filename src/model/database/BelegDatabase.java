package model.database;

import model.BelegSoort;
import model.Broodje;
import model.Item;
import model.database.LoadSaveStrategies.LoadSaveStrategy;
import model.database.LoadSaveStrategies.LoadSaveStrategyEnum;
import model.database.LoadSaveStrategies.LoadSaveStrategyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class BelegDatabase {

    private LoadSaveStrategyEnum strat;
    private LoadSaveStrategy database;


    public ArrayList<BelegSoort> getAll(){
        return database.load().values().stream().map(b -> (BelegSoort) b).collect(Collectors.toCollection(ArrayList::new));
    }

    public void setLoadSaveStrategyEnum(LoadSaveStrategyEnum strat){
        this.strat = strat;
        database = LoadSaveStrategyFactory.createLoadSaveStrategy(strat);
    }

    public double getPrijsVanSoort(String soort){
        return getAll().stream().filter(b -> b.getBeschrijving().equals(soort)).findFirst().get().getPrijs();
    }

    public int getStockVanSoort(String soort){
        return getAll().stream().filter(b -> b.getBeschrijving().equals(soort)).findFirst().get().getInstock();
    }

    public int getVerkochtVanSoort(String soort){
        return getAll().stream().filter(b -> b.getBeschrijving().equals(soort)).findFirst().get().getVerkocht();
    }

    public BelegSoort getBelegByName(String name) {
        return getAll().stream().filter(b -> b.getBeschrijving().equals(name)).findFirst().get();
    }

    public void aanpassenVoorraadBeleg(String name, int amount){
        HashMap<String, Item> data = database.load();
        BelegSoort beleg = (BelegSoort) data.get(name);
        beleg.setInstock(beleg.getInstock() + amount);
        data.put(name, beleg);
        database.save(data);
    }

    public HashMap<String, BelegSoort> getVoorraadlijstBeleg() {
        HashMap<String, Item> data = database.load();
        HashMap<String, BelegSoort> hashMap = new HashMap(data);
        return hashMap;
    }

    public void updateSold(String name) {
        HashMap<String, Item> data = database.load();
        BelegSoort beleg = (BelegSoort) data.get(name);
        beleg.setVerkocht(beleg.getVerkocht() + 1);
        data.put(name, beleg);
        database.save(data);
    }
}
