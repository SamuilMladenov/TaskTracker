/**
 * File: EditTaskDialog.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */

package org.example;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditTaskDialog extends JDialog {
    private JTextField dateField;
    private JTextField taskName;
    private JTextField minutesWorked;
    private JButton submitBtn;
    private JButton cancelBtn;
    private TaskData originalTaskData;

    /**
     * Constructor for EditTaskDialog.
     *
     * @param taskData The original TaskData object to be edited.
     */
    public EditTaskDialog(TaskData taskData) {
        setTitle("Edit Task");
        setModal(true);
        initialize(taskData);
    }

    /**
     * Initialize the components of the dialog.
     *
     * @param taskData The original TaskData object to be edited.
     */

    private void initialize(TaskData taskData) {
        originalTaskData = taskData;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Use the formatted date from TaskData
        dateField = new JTextField(taskData.getFormattedDate(), 10);
        taskName = new JTextField(taskData.getTaskName(), 15);
        minutesWorked = new JTextField(String.valueOf(taskData.getDuration()), 10);

        submitBtn = new JButton("Update");
        submitBtn.addActionListener(e -> {
            // Handle update logic
            updateTask();
            dispose();
        });
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dispose()); // Close dialog without saving

        // Layout
        add(createLabeledField("Date (dd/mm/yyyy):", dateField));
        add(createLabeledField("Task Name:", taskName));
        add(createLabeledField("Duration (minutes):", minutesWorked));
        add(submitBtn);
        add(cancelBtn);

        pack();
        setLocationRelativeTo(null); // Center on screen
    }

    /**
     * Create a labeled field panel.
     *
     * @param label     The label for the field.
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
     * Handle the logic for updating a task.
     */
    private void updateTask() {
        // Retrieve the updated data
        String newPosition = GlobalData.getWorkingPosition();
        LocalDate newDate = LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String newTaskName = taskName.getText();
        int newDuration = Integer.parseInt(minutesWorked.getText());

        // Create the updated TaskData object
        TaskData updatedTaskData = new TaskData(newPosition, newDate.getDayOfMonth(), newDate.getMonthValue(), newDate.getYear(), newTaskName, newDuration);

        // Add logic to update another sheet with information about update requests
        ExcelWriter.writeUpdateRequest(originalTaskData, updatedTaskData);
    }

    /**
     * Static method to create and show the dialog.
     *
     * @param taskData The original TaskData object to be edited.
     */
    public static void editTask(TaskData taskData) {
        EditTaskDialog dialog = new EditTaskDialog(taskData);
        dialog.setVisible(true);
    }
}
