package tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//download from http://poi.apache.org/download.html

public class ReadXLS {
	 
    public List getData() {

          String path = "E:\\DataSheet.xlsx";
          List dataList = new ArrayList();
          FileInputStream fis = null;
          try {
              fis = new FileInputStream(new File(path));
              XSSFWorkbook workbook = new XSSFWorkbook(fis);
              XSSFSheet sheet = workbook.getSheet("TestData");
              java.util.Iterator rows = sheet.rowIterator();

              while (rows.hasNext()) {
                  XSSFRow row = ((XSSFRow) rows.next());
                  // int r=row.getRowNum();
                  java.util.Iterator cells = row .cellIterator();
                  int i = 0;
                  String[] testData= new String[3];
                  while (cells.hasNext()) {

                      XSSFCell cell = (XSSFCell) cells.next();
                      String value = cell.getStringCellValue();
                      if (!value.equals(null)) {
                           testData [i] = value;
                           i++;
                      }
                  }
                  dataList.add(testData);
              }
          }
          catch (Exception e) {
              e.printStackTrace();
          }
          return dataList;
    }

}