package model.service;

import model.Brood;
import model.Broodje;
import model.database.Database;
import model.database.Text.BroodjesDatabaseTextFile;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class BroodjesService{

    private Database database = new BroodjesDatabaseTextFile();

    public ArrayList<Brood> getAll(){
        return database.load().values().stream()
                .map(b -> (Brood) b)
                .sorted(Comparator.comparing(Brood::getBeschrijving))
                .collect(Collectors.toCollection(ArrayList::new));
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
