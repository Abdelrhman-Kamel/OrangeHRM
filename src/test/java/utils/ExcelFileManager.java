package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelFileManager {
    XSSFWorkbook workbook;
    Sheet sheet;

    public ExcelFileManager(String filePath) {

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet("Sheet1");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getCellValue(int row, int col) {
        return sheet.getRow(row).getCell(col).getStringCellValue();
    }


    public Object[][] convertToDataProvider() {
        int totalRows = sheet.getLastRowNum();
        int totalCols = sheet.getRow(0).getLastCellNum();

        List<Object[]> dataList = new ArrayList<>();

        for (int i = 1; i <= totalRows; i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Object[] rowData = new Object[totalCols];
            boolean allEmpty = true;

            for (int j = 0; j < totalCols; j++) {
                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue().trim();
                rowData[j] = cellValue;

                if (!cellValue.isEmpty()) {
                    allEmpty = false;
                }
            }

            if (!allEmpty) {
                dataList.add(rowData);
            }
        }

        Object[][] data = new Object[dataList.size()][totalCols];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }

        return data;
    }


}
