package com.apple.phoenix.MyCode;

import java.io.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;

public class ExcelWriteColumnHeader {
    public static void main(String[] args) throws IOException {
        String soldTo = "1234";
        String module = "mor";
        String env = "TY6";
        String result = "Yes";

        addSoldToAsColumnHeader(soldTo,env);
        writeValueToCell(module, soldTo, result,env);
    }
    private static void addSoldToAsColumnHeader(String soldTo, String sheetname) {
        String filePath = "src/main/resources/data/files/dataAutomation/TestDataReadinessSheet.xlsx"; // Update with your file path
        FileInputStream fis = null;
        Workbook workbook = null;
        try {
            fis = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetname); // Get the first sheet
            // Find the column index with header "Account Number"
            Row headerRow = sheet.getRow(0); // Assuming the first row is the header
            int accountNumberColumn = -1;
            for (Cell cell : headerRow) {
                if ("Account Number".equals(cell.getStringCellValue())) {
                    accountNumberColumn = cell.getColumnIndex();
                    break;
                }
            }
            System.out.println("The account number column index is: " + accountNumberColumn);
            if (accountNumberColumn == -1) {
                throw new IllegalStateException("Column 'Account Number' not found");
            }
            boolean flag = true;
            for (int i = accountNumberColumn + 1; i < headerRow.getPhysicalNumberOfCells(); i++) {
                if (soldTo.equals(headerRow.getCell(i).getStringCellValue())) {
                    flag = false;
                    break;
                }
            }
            CellStyle style = workbook.createCellStyle();
            // Set border style
            style.setBorderTop(CellStyle.BORDER_THIN);
            style.setBorderBottom(CellStyle.BORDER_THIN);
            style.setBorderLeft(CellStyle.BORDER_THIN);
            style.setBorderRight(CellStyle.BORDER_THIN);

            if (flag) {
                Row row = sheet.getRow(0);
                int len = row.getPhysicalNumberOfCells();
                Cell cell = row.createCell(len);
                cell.setCellValue(soldTo);
                cell.setCellStyle(style);
            }
            // Write changes back to the file
            FileOutputStream fos = null;
            fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            fis.close();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    private static void writeValueToCell(String module, String soldTo, String valueToWrite, String sheetName) {
        String filePath = "src/main/resources/data/files/dataAutomation/TestDataReadinessSheet.xlsx"; // Update with your file path
        FileInputStream fis = null;
        Workbook workbook = null;
        try {
            fis = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName); // Get the first sheet
            // Find the column index with header "Account Number"
            Row headerRow = sheet.getRow(0); // Assuming the first row is the header
            int soldToColumnIndex = -1;
            for (Cell cell : headerRow) {
                if (soldTo.equals(cell.getStringCellValue())) {
                    soldToColumnIndex = cell.getColumnIndex();
                    break;
                }
            }
            System.out.println("The sold to column index is: " + soldToColumnIndex);
            int moduleColumnIndex = -1;
            Row rowGlobal = null;
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (module.trim().replaceAll(" ", "").equalsIgnoreCase(cell.getStringCellValue().trim()
                        .replaceAll(" ", ""))) {
                    moduleColumnIndex = cell.getRowIndex();
                    rowGlobal = row;
                    break;
                }
            }
            CellStyle style = workbook.createCellStyle();
            // Set border style
            style.setBorderTop(CellStyle.BORDER_THIN);
            style.setBorderBottom(CellStyle.BORDER_THIN);
            style.setBorderLeft(CellStyle.BORDER_THIN);
            style.setBorderRight(CellStyle.BORDER_THIN);
            System.out.println("The module column index is: " + moduleColumnIndex);
            Cell cellToWrite1 = rowGlobal.createCell(soldToColumnIndex);
            cellToWrite1.setCellValue(valueToWrite);
            cellToWrite1.setCellStyle(style);
            // Write changes back to the file
            FileOutputStream fos = null;
            fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            fis.close();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}


