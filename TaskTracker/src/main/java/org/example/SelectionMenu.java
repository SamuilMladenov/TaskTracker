/**
 * File: SelectionMenu.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */
package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionMenu {
    // Instance variables
    AppFrame frame;
    JButton addBtn;
    JPanel panel1;
    JButton editBtn;
    JButton delBtn;
    JButton logOutBtn;

    // Constructor
    SelectionMenu() {
        frame = new AppFrame();
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        // Add Task button
        addBtn = new JButton("Add Task");
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                AddTaskPanel addTaskPanel = new AddTaskPanel();
            }
        });

        // Edit Task button
        editBtn = new JButton("Edit Task");
        editBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                EditTaskPanel editTaskPanel = new EditTaskPanel();
                // Add logic to open a new window for editing tasks
            }
        });

        // Delete Task button
        delBtn = new JButton("Delete Task");
        delBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // Add logic to open a new window for deleting tasks
                DeleteTaskPanel deleteTaskPanel = new DeleteTaskPanel();
            }
        });

        // Log Out button
        logOutBtn = new JButton("Log Out");
        logOutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LogInPage afterLogOut = new LogInPage();
            }
        });

        // Add buttons to the panel
        panel1.add(addBtn);
        panel1.add(editBtn);
        panel1.add(delBtn);
        panel1.add(logOutBtn);

        // Add panel to the frame
        frame.add(panel1);
        frame.setVisible(true);
    }
}
