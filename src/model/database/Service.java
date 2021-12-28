package model.database;

import model.DomainException;
import model.database.LoadSaveStrategies.LoadSaveStrategyEnum;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Service {

    private static Service instance;
    public BelegDatabase belegService = new BelegDatabase();
    public BroodjesDatabase broodjesService = new BroodjesDatabase();

    private Service() {
        Properties properties = new Properties();

        try {
            InputStream is = new FileInputStream("src/bestanden/settings.properties");
            properties.load(is);
        } catch (IOException e) {
           throw new DomainException("could not find file" + e);
        }
        belegService.setLoadSaveStrategyEnum(LoadSaveStrategyEnum.valueOf(properties.getProperty("belegDatabase").toUpperCase()));
        broodjesService.setLoadSaveStrategyEnum(LoadSaveStrategyEnum.valueOf(properties.getProperty("broodjesDatabase").toUpperCase()));
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public BelegDatabase getBelegService() {
        return belegService;
    }


    public BroodjesDatabase getBroodjesService() {
        return broodjesService;
    }
}
