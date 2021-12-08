package model.database.LoadSaveStrategies;

import utilities.ExcelLoadSaveTemplate;
import utilities.TekstLoadSaveTemplate;

public enum LoadSaveStrategyEnum {

    TEKSTBROODJE("src/model/database/LoadSaveStrategies/Text/BroodjeTekstLoadSaveStrategy.class"),
    TEKSTBELEG("src/model/database/LoadSaveStrategies/Text/BelegTekstLoadSaveStrategy.class"),
    EXCELBROODJE("src/model/database/LoadSaveStrategies/Excel/BroodjeExcelLoadSaveStrategy.class"),
    EXCELBELEG("src/model/database/LoadSaveStrategies/Excel/BroodjeExcelLoadSaveStrategy.class");


    private String path;

    public String getPath() {
        return path;
    }

    private LoadSaveStrategyEnum(String path) {
        this.path = path;
    }
}
