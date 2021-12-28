package model.database.LoadSaveStrategies;

public enum LoadSaveStrategyEnum {

    BROODJESTXT("model.database.LoadSaveStrategies.Text.BroodjeTekstLoadSaveStrategy"),
    BELEGTXT("model.database.LoadSaveStrategies.Text.BelegTekstLoadSaveStrategy"),
    BROODJESXLS("model.database.LoadSaveStrategies.Excel.BroodjeExcelLoadSaveStrategy"),
    BELEGXLS( "model.database.LoadSaveStrategies.Excel.BelegExcelLoadSaveStrategy");


    private String path;

    public String getPath() {
        return path;
    }

    private LoadSaveStrategyEnum(String path) {
        this.path = path;
    }
}
