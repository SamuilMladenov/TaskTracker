/**
 * File: ExcelWriter.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */

package org.example;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter {

    // Variable to store the number of filled rows
    private static int filledRowCount = 1; // Start from 1 to skip the header row
    private static int filledUpdateRowCount = 1; // Start from 1 to skip the header row
    private static int filledDeleteRowCount = 1; // Start from 1 to skip the header row

    private static Workbook workbook;
    private static Sheet sheet;

    // Initialize workbook and sheet
    static {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/org/example/TaskTracker.xlsx");
            workbook = WorkbookFactory.create(fileInputStream);
            sheet = workbook.getSheet("Task Data");
            if (sheet == null) {
                sheet = workbook.createSheet("Task Data");
                createHeaderRow(sheet);
            } else {
                filledRowCount = sheet.getPhysicalNumberOfRows();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the header row for the "Task Data" sheet.
     *
     * @param sheet The sheet to which the header row will be added.
     */
    private static void createHeaderRow(Sheet sheet) {
        // Header Row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Position");
        headerRow.createCell(1).setCellValue("Date");
        headerRow.createCell(2).setCellValue("Task Name");
        headerRow.createCell(3).setCellValue("Duration");
    }

    /**
     * Write a TaskData object to the "Task Data" sheet.
     *
     * @param taskData The TaskData object to be written.
     */
    public static void writeTaskToExcel(TaskData taskData) {
        try {
            // Data Row
            Row dataRow = sheet.createRow(filledRowCount++);
            dataRow.createCell(0).setCellValue(taskData.getPosition());
            dataRow.createCell(1).setCellValue(taskData.getFormattedDate());
            dataRow.createCell(2).setCellValue(taskData.getTaskName());
            dataRow.createCell(3).setCellValue(taskData.getDuration());

            // Write to Excel file
            try (FileOutputStream fileOut = new FileOutputStream("src/main/java/org/example/TaskTracker.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Data written to Excel file successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write an update request to the "UpdateRequests" sheet.
     *
     * @param originalTask The original TaskData object.
     * @param updatedTask  The updated TaskData object.
     */
    public static void writeUpdateRequest(TaskData originalTask, TaskData updatedTask) {
        try {
            Sheet updateSheet = workbook.getSheet("UpdateRequests");

            // Create data row
            Row dataRow = updateSheet.createRow(filledUpdateRowCount++);
            dataRow.createCell(0).setCellValue(originalTask.getPosition());
            dataRow.createCell(1).setCellValue(originalTask.getFormattedDate());
            dataRow.createCell(2).setCellValue(originalTask.getTaskName());
            dataRow.createCell(3).setCellValue(originalTask.getDuration());
            dataRow.createCell(4).setCellValue(updatedTask.getPosition());
            dataRow.createCell(5).setCellValue(updatedTask.getFormattedDate());
            dataRow.createCell(6).setCellValue(updatedTask.getTaskName());
            dataRow.createCell(7).setCellValue(updatedTask.getDuration());

            // Save the workbook
            try (FileOutputStream fileOut = new FileOutputStream("src/main/java/org/example/TaskTracker.xlsx")) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write a delete request to the "DeleteRequests" sheet.
     *
     * @param taskData The TaskData object to be deleted.
     */
    public static void writeDeleteRequest(TaskData taskData) {
        try {
            Sheet deleteSheet = workbook.getSheet("DeleteRequests");

            // Create data row
            Row dataRow = deleteSheet.createRow(filledDeleteRowCount++);
            dataRow.createCell(0).setCellValue(taskData.getPosition());
            dataRow.createCell(1).setCellValue(taskData.getFormattedDate());
            dataRow.createCell(2).setCellValue(taskData.getTaskName());
            dataRow.createCell(3).setCellValue(taskData.getDuration());

            // Save the workbook
            try (FileOutputStream fileOut = new FileOutputStream("src/main/java/org/example/TaskTracker.xlsx")) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a row from the "UpdateRequests" sheet.
     *
     * @param updatedTaskData The TaskData object to be deleted.
     */
    public static void deleteRowFromUpdateRequests(TaskData updatedTaskData) {
        try {
            Sheet updateSheet = workbook.getSheet("UpdateRequests");
            if (updateSheet != null) {
                int lastRowNum = updateSheet.getLastRowNum();
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = updateSheet.getRow(i);
                    Cell taskNameCell = row.getCell(2); // Assuming task name is in the third column (index 2)
                    if (taskNameCell != null && updatedTaskData.getTaskName().equals(taskNameCell.getStringCellValue())) {
                        updateSheet.removeRow(row);
                        break;
                    }
                }

                // Save the workbook after deleting the row
                try (FileOutputStream fileOut = new FileOutputStream("src/main/java/org/example/TaskTracker.xlsx")) {
                    workbook.write(fileOut);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a row from the "Task Data" sheet.
     *
     * @param taskData The TaskData object to be deleted.
     */
    public static void deleteRowFromTaskData(TaskData taskData) {
        try {
            Sheet sheet = workbook.getSheet("Task Data");

            if (sheet != null) {
                int lastRowNum = sheet.getLastRowNum();
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {  // Add null check for the row
                        Cell taskNameCell = row.getCell(2); // Assuming task name is in the third column (index 2)
                        if (taskNameCell != null && taskData.getTaskName().equals(taskNameCell.getStringCellValue())) {
                            sheet.removeRow(row); // Remove the row from the sheet
                            filledRowCount--; // Decrement the filled row count
                            break;
                        }
                    }
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream("src/main/java/org/example/TaskTracker.xlsx")) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a row from the "DeleteRequests" sheet.
     *
     * @param taskData The TaskData object to be deleted.
     */
    public static void deleteRowFromDeleteRequests(TaskData taskData) {
        try {
            Sheet deleteSheet = workbook.getSheet("DeleteRequests");
            if (deleteSheet != null) {
                int lastRowNum = deleteSheet.getLastRowNum();
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = deleteSheet.getRow(i);
                    Cell taskNameCell = row.getCell(2); // Assuming task name is in the third column (index 2)
                    if (taskNameCell != null && taskData.getTaskName().equals(taskNameCell.getStringCellValue())) {
                        deleteSheet.removeRow(row);
                        break;
                    }
                }

                // Save the workbook after deleting the row
                try (FileOutputStream fileOut = new FileOutputStream("src/main/java/org/example/TaskTracker.xlsx")) {
                    workbook.write(fileOut);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
