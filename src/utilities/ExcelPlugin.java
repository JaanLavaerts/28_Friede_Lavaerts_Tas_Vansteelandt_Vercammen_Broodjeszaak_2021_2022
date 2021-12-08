package utilities;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelPlugin {

    public void write(File file, ArrayList<ArrayList<String>> args)
            throws BiffException, IOException, RowsExceededException, WriteException{

        WritableWorkbook workbook = Workbook.createWorkbook(file);
        workbook.createSheet("sheet1", 0);
        WritableSheet sheet = workbook.getSheet(0);
        for(int i = 0; i < args.size(); i++){
            ArrayList<String> parameters = args.get(i);
            for(int j = 0; j < parameters.size(); j++){
                Label label = new Label(j, i, parameters.get(j));
                sheet.addCell(label);
            }
        }
        workbook.write();
        workbook.close();

    }

    public ArrayList<ArrayList<String>> read(File file)
            throws BiffException, IOException {

        Workbook workbook = Workbook.getWorkbook(file);
        Sheet sheet = workbook.getSheet(0);
        int row = 0;

        ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
        while(row < sheet.getRows())
        {
            int column = 0;
            ArrayList<String> rowinfo = new ArrayList<String>();
            while(column < sheet.getColumns()){
                Cell cell = sheet.getCell(column,row);
                String information = cell.getContents();
                rowinfo.add(information);
                column++;
            }
            info.add(rowinfo);
            row++;
        }
        workbook.close();
        return info;
    }

}