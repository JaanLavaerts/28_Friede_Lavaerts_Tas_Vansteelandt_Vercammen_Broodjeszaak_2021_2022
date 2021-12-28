package model.database;

import model.Broodje;
import model.Item;
import model.database.LoadSaveStrategies.LoadSaveStrategy;
import model.database.LoadSaveStrategies.LoadSaveStrategyEnum;
import model.database.LoadSaveStrategies.LoadSaveStrategyFactory;

import java.util.*;
import java.util.stream.Collectors;

public class BroodjesDatabase {

    private LoadSaveStrategyEnum strat;
    private LoadSaveStrategy database;


    public ArrayList<Broodje> getAll(){
        return database.load().values().stream()
                .map(b -> (Broodje) b)
                .sorted(Comparator.comparing(Broodje::getBeschrijving))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void setLoadSaveStrategyEnum(LoadSaveStrategyEnum strat){
        this.strat = strat;
        database = LoadSaveStrategyFactory.createLoadSaveStrategy(strat);
    }

    public double getPrijsVanSoort(String soort){
        return getAll().stream()
                .filter(b -> b.getBeschrijving().equals(soort)).findFirst().get().getPrijs();
    }

    public int getStockVanSoort(String soort){
        return getAll().stream().filter(b -> b.getBeschrijving().equals(soort)).findFirst().get().getInstock();
    }

    public int getVerkochtVanSoort(String soort){
        return getAll().stream().filter(b -> b.getBeschrijving().equals(soort)).findFirst().get().getVerkocht();
    }

    public Broodje getBroodjeByName(String name){
        return getAll().stream().filter(b -> b.getBeschrijving().equals(name)).findFirst().get();
    }

    public void aanpassenVoorraad(String name){
        HashMap<String, Item> data = database.load();
        Broodje broodje = (Broodje) data.get(name);
        broodje.setInstock(broodje.getInstock() - 1);
        data.put(name, broodje);
        database.save(data);
    }

    public HashMap<String, Broodje> getVoorraadlijstBroodjes(){
        HashMap<String, Item> data = database.load();
        HashMap<String, Broodje> hashMap = new HashMap(data);
        return hashMap;
    }

    public void updateSold(String name) {
        HashMap<String, Item> data = database.load();
        Broodje broodje = (Broodje) data.get(name);
        broodje.setVerkocht(broodje.getVerkocht() + 1);
        data.put(name, broodje);
        database.save(data);
    }
}
