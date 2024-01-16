/**
 * File: ExcelReader.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */

package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    /**
     * Read tasks from an Excel file for a specific position.
     *
     * @param filePath The path to the Excel file.
     * @param position The position for which to retrieve tasks.
     * @return List of TaskData objects for the specified position.
     */
    public static List<TaskData> readTasks(String filePath, String position) {
        List<TaskData> tasks = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath))) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Cell positionCell = currentRow.getCell(0);
                if (positionCell.getStringCellValue().equals(position)) {
                    String taskName = currentRow.getCell(2).getStringCellValue();
                    String dateString = currentRow.getCell(1).getStringCellValue();
                    int duration = (int) currentRow.getCell(3).getNumericCellValue();

                    // Parse the date string into LocalDate
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(dateString, formatter);

                    // Create a TaskData object
                    TaskData taskData = new TaskData(position, date.getDayOfMonth(), date.getMonthValue(), date.getYear(), taskName, duration);
                    tasks.add(taskData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}
