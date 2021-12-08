package model.database;

import model.Broodje;
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

}
