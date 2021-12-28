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
import java.util.HashMap;
import java.util.stream.Collectors;

import model.Item;

public class ExcelPlugin {

    public void write(File file, HashMap<String, Item> data)
            throws BiffException, IOException, RowsExceededException, WriteException{

        WritableWorkbook workbook = Workbook.createWorkbook(file);
        workbook.createSheet("sheet1", 0);
        WritableSheet sheet = workbook.getSheet(0);
        ArrayList<Item> items = data.values().stream().collect(Collectors.toCollection(ArrayList::new));
        for(int i = 0; i < items.size(); i++){
            Item item = items.get(i);
            Label name = new Label(0,i,item.getBeschrijving());
            sheet.addCell(name);
            Label prijs = new Label(1,i,String.valueOf(item.getPrijs()));
            sheet.addCell(prijs);
            Label stock = new Label(2,i,String.valueOf(item.getInstock()));
            sheet.addCell(stock);
            Label verkocht = new Label(3,i,String.valueOf(item.getVerkocht()));
            sheet.addCell(verkocht);
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