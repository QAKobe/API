package apache_poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadData {

    @Test
    public void test1() throws IOException {

        File excelFile = new File("src/test/resources/test_data/TestDataDoc.xlsx");
        FileInputStream inputStream = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet page1 = workbook.getSheet("Sheet1");
        XSSFRow row1 = page1.getRow(0);
        XSSFCell cell1 = row1.getCell(0);

        System.out.println(cell1);
    }

    //

    @Test
    public void getAllValuesFromRowTest() throws IOException {

        File file = new File("src/test/resources/test_data/TestDataDoc.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet pag1 = workbook.getSheetAt(0);
        XSSFRow row1 = pag1.getRow(0);
        XSSFCell cellAll = row1.getCell(0);

        for (int i = 0; i < row1.getLastCellNum(); i++) {

            System.out.print(row1.getCell(i) + " | ");


        }
    }

    @Test
    public void getAllValuesTest() throws IOException {

        File file = new File("src/test/resources/test_data/TestDataDoc.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet1 = workbook.getSheetAt(0);

        for (int i = 0; i < sheet1.getLastRowNum(); i++) {

            XSSFRow tempRow = sheet1.getRow(i);
            System.out.print("|");
            for (int j = 0; j < tempRow.getLastCellNum(); j++) {

                System.out.print(tempRow.getCell(j) + " | ");

            }
            System.out.println();

        }


    }

    @Test
    public void printColumn() throws IOException {

        File file = new File("src/test/resources/test_data/TestDataDoc.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet1 = workbook.getSheetAt(0);

        int index = 0;
        XSSFRow row1 = sheet1.getRow(0);
        for (int i = 0; i < row1.getLastCellNum(); i++) {
            if (row1.getCell(i).toString().equals("Author")) {
                index = i;
            }
        }

        for (int k = 1; k <= sheet1.getLastRowNum(); k++) {
            System.out.println(sheet1.getRow(k).getCell(index));
        }
    }

    @Test
    public void printTCForSatishTest() throws IOException {

        File file = new File("src/test/resources/test_data/TestDataDoc.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet page1 = workbook.getSheetAt(0);


        int authorIndex = -1;
        int tcIndex = -1;
        for (int i = 0; i < page1.getRow(0).getLastCellNum(); i++) {

            XSSFRow tempRow = page1.getRow(0);
            if (tempRow.getCell(i).toString().equals("Author")) {

                authorIndex = i;

            } else if (tempRow.getCell(i).toString().equals("Test Case Id")) {
                tcIndex = i;
            }

        }

        for (int j = 0; j < page1.getLastRowNum(); j++) {

            XSSFRow tempRow = page1.getRow(j);
            if (tempRow.getCell(authorIndex).toString().equals("Satish")) {
                System.out.println(tempRow.getCell(tcIndex));
            }

        }


    }
}
