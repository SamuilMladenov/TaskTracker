/**
 * File: EditRequestsPanel.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */

package org.example;

import org.apache.poi.ss.usermodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EditRequestsPanel {
    AppFrame frame;
    JPanel panel;

    /**
     * Constructor for EditRequestsPanel.
     */
    EditRequestsPanel() {
        frame = new AppFrame();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Retrieve edit requests from the sheet (you need to implement this logic)
        List<String> editRequestLabels = getEditRequestLabels();

        for (String label : editRequestLabels) {
            JButton editRequestBtn = new JButton(label);
            editRequestBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            editRequestBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    showEditRequestDialog(label); // Pass the task name as an argument
                }
            });
            panel.add(editRequestBtn);
        }
        JButton button = new JButton("Back");
        // add action listener
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call dispose method
                frame.dispose();
                SelectionMenu selmenu = new SelectionMenu();
            }
        });

        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Show the EditRequestDialog for a given task name.
     *
     * @param taskName The task name for which to show the dialog.
     */
    private void showEditRequestDialog(String taskName) {
        // Retrieve original and updated task data based on the taskName
        TaskData originalTaskData = getOriginalTaskData(taskName);
        TaskData updatedTaskData = getUpdatedTaskData(taskName);

        // Create and show the EditRequestDialog
        EditRequestDialog.editRequest(originalTaskData, updatedTaskData);
    }

    /**
     * Retrieve edit request labels from the "UpdateRequests" sheet.
     *
     * @return List of edit request labels.
     */
    private List<String> getEditRequestLabels() {
        // Replace this with logic to retrieve edit request labels from the "UpdateRequests" sheet
        List<String> editRequestLabels = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/org/example/TaskTracker.xlsx");
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet("UpdateRequests");

            if (sheet != null) {
                int lastRowNum = sheet.getLastRowNum();
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    Cell taskNameCell = row.getCell(2); // Assuming task name is in the third column (index 2)
                    if (taskNameCell != null) {
                        editRequestLabels.add(taskNameCell.getStringCellValue());
                    }
                }
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return editRequestLabels;
    }

    /**
     * Retrieve original task data based on task name.
     *
     * @param taskName The task name for which to retrieve data.
     * @return TaskData object representing the original task data.
     */
    private TaskData getOriginalTaskData(String taskName) {
        TaskData originalTaskData = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/org/example/TaskTracker.xlsx");
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet("Task Data");

            if (sheet != null) {
                int lastRowNum = sheet.getLastRowNum();
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    Cell taskNameCell = row.getCell(2); // Assuming task name is in the third column (index 2)
                    if (taskNameCell != null && taskName.equals(taskNameCell.getStringCellValue())) {
                        originalTaskData = new TaskData(
                                row.getCell(0).getStringCellValue(), // Position
                                getLocalDateValue(row.getCell(1)), // Date
                                taskNameCell.getStringCellValue(), // Task Name
                                getNumericValue(row.getCell(3)) // Duration
                        );
                        break;
                    }
                }
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return originalTaskData;
    }

    /**
     * Retrieve updated task data based on task name.
     *
     * @param taskName The task name for which to retrieve data.
     * @return TaskData object representing the updated task data.
     */
    private TaskData getUpdatedTaskData(String taskName) {
        TaskData updatedTaskData = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/org/example/TaskTracker.xlsx");
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet("UpdateRequests");

            if (sheet != null) {
                int lastRowNum = sheet.getLastRowNum();
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    Cell taskNameCell = row.getCell(2); // Assuming task name is in the third column (index 2)
                    if (taskNameCell != null && taskName.equals(taskNameCell.getStringCellValue())) {
                        updatedTaskData = new TaskData(
                                row.getCell(0).getStringCellValue(), // Original Position
                                getLocalDateValue(row.getCell(1)), // Original Date
                                taskNameCell.getStringCellValue(), // Original Task Name
                                getNumericValue(row.getCell(3)), // Original Duration
                                row.getCell(4).getStringCellValue(), // Updated Position
                                getLocalDateValue(row.getCell(5)), // Updated Date
                                row.getCell(6).getStringCellValue(), // Updated Task Name
                                getNumericValue(row.getCell(7)) // Updated Duration
                        );
                        break;
                    }
                }
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updatedTaskData;
    }

    /**
     * Helper method to handle date values.
     *
     * @param cell The cell containing the date value.
     * @return LocalDate representing the date.
     */
    private LocalDate getLocalDateValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        } else {
            // Handle other cases, e.g., return a default value or throw an exception
            return LocalDate.parse(cell.getStringCellValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    }

    /**
     * Helper method to handle numeric values.
     *
     * @param cell The cell containing the numeric value.
     * @return int representing the numeric value.
     */
    private int getNumericValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        } else {
            // Handle other cases, e.g., return a default value or throw an exception
            return 0; // Default value, update as needed
        }
    }
}
