package resource.TestComponents;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    FileInputStream fis;
    XSSFWorkbook wb;
    XSSFSheet sheet;
    XSSFRow row;
    XSSFCell cell;

    @DataProvider(name ="testData")
    public String[][] getData() throws IOException {
        File f = new File("D://Users//Guna.xlsx/");
        fis = new FileInputStream(f);
        wb = new XSSFWorkbook(fis);

        sheet = wb.getSheet("Personal exp");
        int rowNum = getRowCount();
        int colNum = getCellcount();

        System.out.println("Last Row and Cells: " + rowNum +" - "+ colNum);
        String[][] testData = new String[rowNum][colNum];
        for(int i = 1;i<rowNum; i++){
            for(int j=0; j<colNum;j++){
                testData[i-1][j]= sheet.getRow(i).getCell(j).getStringCellValue();
            }
        }
        System.out.println("Data feeded:"+ testData);
        for(String[] str:testData){
            for(String s: str){
                System.out.println(s);
            }
            System.out.println("---------------");
        }
        return testData;
    }

    private int getCellcount() throws IOException {
        File f = new File("D://Users//Guna.xlsx/");
        fis = new FileInputStream(f);
        wb = new XSSFWorkbook(fis);

        sheet = wb.getSheet("Personal exp");
        int lastColumn = sheet.getRow(1).getLastCellNum();
        return lastColumn;

    }

    private int getRowCount() throws IOException {
        File f = new File("D://Users//Guna.xlsx/");
        fis = new FileInputStream(f);
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheet("Personal exp");
        int lastRow = sheet.getLastRowNum();
        return lastRow;
    }
}
