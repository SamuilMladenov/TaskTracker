/**
 * File: EditRequestDialog.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */

package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditRequestDialog extends JDialog {
    private TaskData originalTaskData;
    private TaskData updatedTaskData;
    private JTextField originalDataField;
    private JTextField updatedDataField;
    private JButton acceptBtn;
    private JButton declineBtn;

    /**
     * Constructor for EditRequestDialog.
     *
     * @param originalTaskData The original TaskData object.
     * @param updatedTaskData The updated TaskData object.
     */
    public EditRequestDialog(TaskData originalTaskData, TaskData updatedTaskData) {
        setTitle("Edit Request");
        setModal(true);
        this.originalTaskData = originalTaskData;
        this.updatedTaskData = updatedTaskData;
        initialize();
    }

    /**
     * Initialize the components of the dialog.
     */
    private void initialize() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Use the formatted date from TaskData
        originalDataField = new JTextField(originalTaskData.toString(), 20);
        updatedDataField = new JTextField(updatedTaskData.toString(), 20);

        acceptBtn = new JButton("Accept");
        acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle accept logic
                acceptEditRequest();
                dispose();
            }
        });

        declineBtn = new JButton("Decline");
        declineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle decline logic
                declineEditRequest();
                dispose();
            }
        });

        add(createLabeledField("Original Data:", originalDataField));
        add(createLabeledField("Updated Data:", updatedDataField));
        add(acceptBtn);
        add(declineBtn);

        pack();
        setLocationRelativeTo(null); // Center on screen
    }

    /**
     * Create a labeled field panel.
     *
     * @param label The label for the field.
     * @param component The component for the field.
     * @return JPanel containing the labeled field.
     */
    private JPanel createLabeledField(String label, JComponent component) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        panel.add(component);
        return panel;
    }

    /**
     * Handle the logic for accepting an edit request.
     */
    private void acceptEditRequest() {
        originalTaskData.setPosition(updatedTaskData.getPosition());

        // Convert the date string to a LocalDate object
        LocalDate updatedDate = LocalDate.parse(updatedTaskData.getFormattedDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        originalTaskData.setDate(updatedDate);

        originalTaskData.setTaskName(updatedTaskData.getTaskName());
        originalTaskData.setDuration(updatedTaskData.getDuration());

        // Now, you can write the updated task data back to the Excel file
        ExcelWriter.writeTaskToExcel(originalTaskData);

        ExcelWriter.deleteRowFromTaskData(originalTaskData);

        ExcelWriter.deleteRowFromUpdateRequests(updatedTaskData);

        // For now, printing the accepted data to console
        System.out.println("Accepted Edit Request: " + originalTaskData.toString());
    }

    /**
     * Handle the logic for declining an edit request.
     */
    private void declineEditRequest() {
        // For now, printing the declined data to console
        System.out.println("Declined Edit Request: " + updatedTaskData.toString());
    }

    /**
     * Static method to create and show the dialog.
     *
     * @param originalTaskData The original TaskData object.
     * @param updatedTaskData The updated TaskData object.
     */
    public static void editRequest(TaskData originalTaskData, TaskData updatedTaskData) {
        EditRequestDialog dialog = new EditRequestDialog(originalTaskData, updatedTaskData);
        dialog.setVisible(true);
    }
}
