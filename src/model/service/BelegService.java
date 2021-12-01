package model.service;

import model.Beleg;
import model.database.Database;
import model.database.Text.BelegDatabaseTextFile;
import model.database.Text.BroodjesDatabaseTextFile;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BelegService {

    private Database database = new BelegDatabaseTextFile();

    public ArrayList<Beleg> getAll(){
        return database.load().values().stream().map(b -> (Beleg) b).collect(Collectors.toCollection(ArrayList::new));
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
