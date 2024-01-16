/**
 * File: EditTaskPanel.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */

package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditTaskPanel extends AppFrame {

    /**
     * Constructor for EditTaskPanel.
     */
    EditTaskPanel() {
        // create a new frame
        final AppFrame frame = new AppFrame();
        // create a panel
        JPanel panel = new JPanel();
        // create a new button
        JButton button = new JButton("Back");
        // add action listener
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call dispose method
                frame.dispose();
                SelectionMenu selmen = new SelectionMenu();
            }
        });

        // Get the current working position
        String currentPosition = GlobalData.getWorkingPosition();
        System.out.println(currentPosition);

        // Read tasks based on the current position from the Excel file
        List<TaskData> tasks = ExcelReader.readTasks("src/main/java/org/example/TaskTracker.xlsx", currentPosition);

        // Set layout
        setLayout(new GridLayout(0, 1)); // Adjust layout as needed

        // Create buttons for each task and add an action listener to open the edit task dialog
        for (TaskData taskData : tasks) {
            JButton taskButton = new JButton(taskData.getTaskName());
            taskButton.addActionListener(e -> {
                // Open the edit task dialog with the TaskData object
                EditTaskDialog.editTask(taskData);
            });
            panel.add(taskButton);
        }

        // add button to panel
        panel.add(button);
        // set the label to the panel
        frame.getContentPane().add(panel);
        // set the size of frame
        frame.setSize(300, 300);
        // set the frame visibility to true
        frame.setVisible(true);
    }
}
