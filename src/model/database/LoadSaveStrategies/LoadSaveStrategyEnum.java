package model.database.LoadSaveStrategies;

import utilities.ExcelLoadSaveTemplate;
import utilities.TekstLoadSaveTemplate;

public enum LoadSaveStrategyEnum {

    TEKSTBROODJE("model.database.LoadSaveStrategies.Text.BroodjeTekstLoadSaveStrategy"),
    TEKSTBELEG("model.database.LoadSaveStrategies.Text.BelegTekstLoadSaveStrategy"),
    EXCELBROODJE("model.database.LoadSaveStrategies.Excel.BroodjeExcelLoadSaveStrategy"),
    EXCELBELEG( "model.database.LoadSaveStrategies.Excel.BelegExcelLoadSaveStrategy");


    private String path;

    public String getPath() {
        return path;
    }

    private LoadSaveStrategyEnum(String path) {
        this.path = path;
    }
}
