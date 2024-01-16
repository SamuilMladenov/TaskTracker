/**
 * File: DeleteRequestsPanel.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */

package org.example;

import org.apache.poi.ss.usermodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DeleteRequestsPanel {
    private AppFrame frame;
    private JPanel panel;

    // Constructor for DeleteRequestsPanel
    public DeleteRequestsPanel() {
        frame = new AppFrame();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Retrieve delete requests from the sheet
        List<String> deleteRequestLabels = getDeleteRequestLabels();

        // Create buttons for each delete request label
        for (String label : deleteRequestLabels) {
            JButton deleteRequestBtn = new JButton(label);
            deleteRequestBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            deleteRequestBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Dispose the current frame and show the DeleteRequestDialog
                    frame.dispose();
                    showDeleteRequestDialog(label); // Pass the task name as an argument
                }
            });
            panel.add(deleteRequestBtn);
        }

        // Add "Back" button
        JButton button = new JButton("Back");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the current frame and go back to the SelectionMenu
                frame.dispose();
                SelectionMenu selmenu = new SelectionMenu();
            }
        });
        panel.add(button);

        // Add panel to frame and make it visible
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to show the DeleteRequestDialog
    private void showDeleteRequestDialog(String taskName) {
        // Retrieve task data based on the taskName
        TaskData taskData = getTaskData(taskName);

        // Create and show the DeleteRequestDialog
        DeleteRequestDialog.deleteRequest(taskData);
    }

    // Implement logic to retrieve delete request labels
    private List<String> getDeleteRequestLabels() {
        // Replace this with logic to retrieve delete request labels from the "DeleteRequests" sheet
        List<String> deleteRequestLabels = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("src/main/java/org/example/TaskTracker.xlsx"));
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet("DeleteRequests");

            if (sheet != null) {
                int lastRowNum = sheet.getLastRowNum();
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    Cell taskNameCell = row.getCell(2); // Assuming task name is in the third column (index 2)
                    if (taskNameCell != null) {
                        deleteRequestLabels.add(taskNameCell.getStringCellValue());
                    }
                }
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deleteRequestLabels;
    }

    // Implement logic to retrieve task data based on task name
    private TaskData getTaskData(String taskName) {
        TaskData taskData = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("src/main/java/org/example/TaskTracker.xlsx"));
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet("Task Data");

            if (sheet != null) {
                int lastRowNum = sheet.getLastRowNum();
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {  // Add null check for the row
                        Cell taskNameCell = row.getCell(2); // Assuming task name is in the third column (index 2)
                        if (taskNameCell != null && taskName.equals(taskNameCell.getStringCellValue())) {
                            taskData = new TaskData(
                                    row.getCell(0).getStringCellValue(), // Position
                                    getLocalDateValue(row.getCell(1)), // Date
                                    taskNameCell.getStringCellValue(), // Task Name
                                    getNumericValue(row.getCell(3)) // Duration
                            );
                            break;
                        }
                    }
                }
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return taskData;
    }

    // Helper method to handle date values
    private LocalDate getLocalDateValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        } else {
            // Handle other cases, e.g., return a default value or throw an exception
            return LocalDate.parse(cell.getStringCellValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    }

    // Helper method to get numeric values from cells
    private int getNumericValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        } else {
            // Handle other cases, e.g., return a default value or throw an exception
            return 0; // Default value, update as needed
        }
    }

}
