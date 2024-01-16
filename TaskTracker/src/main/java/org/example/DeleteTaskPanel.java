/**
 * File: DeleteTaskPanel.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */

package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteTaskPanel extends AppFrame {
    // Constructor for DeleteTaskPanel
    DeleteTaskPanel() {
        // create a new frame
        AppFrame frame = new AppFrame();

        // create a panel
        JPanel panel = new JPanel();

        // create a new button
        JButton button = new JButton("Back");

        // add action listener to the "Back" button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call dispose method to close the current frame
                frame.dispose();

                // create a new SelectionMenu
                SelectionMenu selmenu = new SelectionMenu();
            }
        });

        // Get the current working position from GlobalData
        String currentPosition = GlobalData.getWorkingPosition();
        System.out.println(currentPosition);

        // Read tasks from the Excel file based on the current position
        List<TaskData> tasks = ExcelReader.readTasks("src/main/java/org/example/TaskTracker.xlsx", currentPosition);

        setLayout(new GridLayout(0, 1)); // Adjust layout as needed

        // Iterate through the list of tasks and create buttons for each task
        for (TaskData taskData : tasks) {
            JButton taskButton = new JButton(taskData.getTaskName());
            taskButton.addActionListener(e -> {
                // Open a confirmation dialog with "Are you sure" question
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this task? An email will be sent to the Executive!",
                        "Delete Task", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    // Add logic to create a delete request in another sheet
                    ExcelWriter.writeDeleteRequest(taskData);
                }
            });
            panel.add(taskButton);
        }

        // add "Back" button to the panel
        panel.add(button);

        // set the panel to the content pane of the frame
        frame.getContentPane().add(panel);

        // set the size of frame
        frame.setSize(300, 300);

        // set the frame visibility to true
        frame.setVisible(true);
    }
}
