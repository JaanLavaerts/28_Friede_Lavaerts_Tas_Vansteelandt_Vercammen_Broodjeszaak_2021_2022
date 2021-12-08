package model.database.LoadSaveStrategies;

public class LoadSaveStrategyFactory {

    public static LoadSaveStrategy createLoadSaveStrategy(LoadSaveStrategyEnum strat){
        LoadSaveStrategy strategy;
        try {
            Class<?> c = Class.forName(strat.getPath());
            strategy = (LoadSaveStrategy) c.newInstance();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new IllegalStateException("Probleem factory: ", e);
        }
        return strategy;
    }


}
