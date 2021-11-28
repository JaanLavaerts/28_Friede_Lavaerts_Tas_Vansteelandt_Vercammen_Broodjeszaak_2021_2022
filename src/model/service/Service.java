package model.service;

public class Service {

    private static Service instance;
    public BelegService belegService = new BelegService();
    public BroodjesService broodjesService = new BroodjesService();

    private Service() {
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public BelegService getBelegService() {
        return belegService;
    }

    public BroodjesService getBroodjesService() {
        return broodjesService;
    }
}
