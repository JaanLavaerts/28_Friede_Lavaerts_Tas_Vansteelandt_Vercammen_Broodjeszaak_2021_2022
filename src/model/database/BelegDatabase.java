package model.database;

import model.BelegSoort;
import model.database.LoadSaveStrategies.LoadSaveStrategy;
import model.database.LoadSaveStrategies.LoadSaveStrategyEnum;
import model.database.LoadSaveStrategies.LoadSaveStrategyFactory;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BelegDatabase {

    private LoadSaveStrategy database = LoadSaveStrategyFactory.createLoadSaveStrategy(LoadSaveStrategyEnum.TEKSTBELEG);

    public ArrayList<BelegSoort> getAll(){
        return database.load().values().stream().map(b -> (BelegSoort) b).collect(Collectors.toCollection(ArrayList::new));
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

}