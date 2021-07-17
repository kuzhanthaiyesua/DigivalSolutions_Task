package excel.reader;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelReader {

    FileInputStream fis;
    Workbook wb;
    Sheet sheet;
    Cell cell;
    FileOutputStream fos;

    static String file = System.getProperty("user.dir") + "//data//Testdata.xlsx";

    public void readExcel(String sheetName) {
        try {
            fis = new FileInputStream(new File(file));
            wb = WorkbookFactory.create(fis);
            sheet = wb.getSheet(sheetName);
        } catch (Exception e) {
            System.out.println(file + " File is not accessible" + e);
        }
    }

    /**
     * This method is used to get totalrow of Excelsheet
     * @return
     */
    public int getTotalRows() {
        return sheet.getLastRowNum();
    }

    /**
     * This method is used to update Results in the Excelsheet
     * @param row
     * @param column
     * @param sheetName
     * @param result
     */

    public void updateResult(int row, int column, String sheetName, String result) {
        try {
            readExcel(sheetName);
            cell = sheet.getRow(row).createCell(column);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(result);
            fos = new FileOutputStream(
                    new File(file));
            wb.write(fos);
            fos.close();
            fos.flush();
        } catch (Exception e) {
            System.out.println("The result is not updated to the sheet" + sheetName + " Error Log " + e);
        }
    }

    /**
     * This method is used to get texts from the Excelsheet
     * @param sheetName
     * @param columnTitle
     * @return
     */
    public String[] getTexts(String sheetName, String columnTitle) {
        try {
            readExcel(sheetName);
            int totalRows = getTotalRows();
            String[] Text = new String[totalRows];
            int column = 0;
            if (columnTitle == "English") {
                column = 0;
            } else if (columnTitle == "Spanish") {
                column = 1;
            }
            for (int row = 0; row < totalRows; row++) {
                int rowNum = row + 1;
                String text = sheet.getRow(rowNum).getCell(column).toString();
                if (!text.equals(columnTitle)) {
                    Text[row] = sheet.getRow(rowNum).getCell(column).toString();
                }
            }
            return Text;
        } catch (Exception e) {
            System.out.println("Unable to get the texts from sheet  " + sheetName + "Error log" + e);
        }
        return null;
    }
}
