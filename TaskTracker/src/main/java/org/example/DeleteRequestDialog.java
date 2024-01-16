/**
 * File: DeleteRequestDialog.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */

package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteRequestDialog extends JDialog {
    private TaskData taskData;
    private JTextField taskDataField;
    private JButton acceptBtn;
    private JButton declineBtn;

    // Constructor for DeleteRequestDialog
    public DeleteRequestDialog(TaskData taskData) {
        setTitle("Delete Request");
        setModal(true);
        this.taskData = taskData;
        initialize();
    }

    // Initialize method to set up the components
    private void initialize() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Use the formatted date from TaskData
        taskDataField = new JTextField(taskData.toString(), 20);

        acceptBtn = new JButton("Accept");
        acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle accept logic
                acceptDeleteRequest();
                dispose();
            }
        });

        declineBtn = new JButton("Decline");
        declineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle decline logic
                declineDeleteRequest();
                dispose();
            }
        });

        add(createLabeledField("Task Data:", taskDataField));
        add(acceptBtn);
        add(declineBtn);

        pack();
        setLocationRelativeTo(null); // Center on screen
    }

    // Helper method to create a labeled field panel
    private JPanel createLabeledField(String label, JComponent component) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        panel.add(component);
        return panel;
    }

    // Method to handle accept action
    private void acceptDeleteRequest() {
        ExcelWriter.deleteRowFromTaskData(taskData);
        ExcelWriter.deleteRowFromDeleteRequests(taskData);
        // For now, printing the accepted data to console
        System.out.println("Accepted Delete Request: " + taskData.toString());
    }

    // Method to handle decline action
    private void declineDeleteRequest() {
        // For now, printing the declined data to console
        System.out.println("Declined Delete Request: " + taskData.toString());
    }

    // Static method to create and show the dialog
    public static void deleteRequest(TaskData taskData) {
        DeleteRequestDialog dialog = new DeleteRequestDialog(taskData);
        dialog.setVisible(true);
    }
}
