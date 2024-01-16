/**
 * File: AddTaskPanel.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */

package org.example;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.Arrays;

/**
 * The AddTaskPanel class represents a panel for adding tasks in the task management application.
 */
public class AddTaskPanel {


    AppFrame frame;
    JPanel panel2;
    JLabel datelbl;
    JTextField chooseDate;
    JLabel monthlbl;
    JSpinner chooseMonth;
    JLabel yearlbl;
    JTextField chooseYear;
    JLabel namelbl;
    JTextField taskName;
    JLabel timeWorkedLbl;
    JTextField minutesWorked;
    JButton submitBtn;
    JButton backBtn;

    /**
     * Constructs an instance of the AddTaskPanel class.
     * Initializes the frame, panel, and components for the form.
     */

    AddTaskPanel(){
        frame = new AppFrame();
        frame.setLayout(new CardLayout());
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));


        monthlbl = new JLabel("Month:");
        String[] months = new DateFormatSymbols().getMonths();
        SpinnerModel monthSpinnerModel = new SpinnerListModel(months);    // Create a SpinnerModel with the array of months as values
        chooseMonth = new JSpinner(monthSpinnerModel); // Create a JSpinner with the custom SpinnerModel
        chooseMonth.setValue(months[0]); // Set January as the initial value
        chooseMonth.setPreferredSize(new Dimension(250, 50));

        datelbl = new JLabel("Date:");
        chooseDate = new JTextField();
        PlainDocument doc = (PlainDocument) chooseDate.getDocument();

        // Create a DocumentFilter to restrict input to numbers between 1 and 30
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string == null)
                    return;

                // Check if the resulting text is within the valid range
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + string +
                        currentText.substring(offset, currentText.length());

                try {
                    int value = Integer.parseInt(newText);

                    if (value >= 1 && value <= 31) {
                        super.insertString(fb, offset, string, attr);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                // Handle replacement (e.g., when the user pastes text)
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text +
                        currentText.substring(offset + length, currentText.length());

                try {
                    int value = Integer.parseInt(newText);

                    if (value >= 1 && value <= 31) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                }
            }
        };
        chooseDate.setPreferredSize(new Dimension(250, 50));

        yearlbl = new JLabel("Year:");

        chooseYear = new JTextField();
        PlainDocument doc2 = (PlainDocument) chooseYear.getDocument();

        // Create a DocumentFilter to restrict input to 4-digit numbers
        DocumentFilter filter2 = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string == null)
                    return;

                // Check if the resulting text is a valid 4-digit number
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;

                if (newText.matches("\\d{0,4}") && newText.length() <= 4) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                // Handle replacement (e.g., when the user pastes text)
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text +
                        currentText.substring(offset + length, currentText.length());

                if (newText.matches("\\d{0,4}") && newText.length() <= 4) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
        chooseYear.setPreferredSize(new Dimension(250, 50));


        namelbl = new JLabel("Task Name:");
        taskName = new JTextField();

        timeWorkedLbl = new JLabel("Time worked in minutes:");

        minutesWorked = new JTextField();
        PlainDocument doc3 = (PlainDocument) minutesWorked.getDocument();

        // Create a DocumentFilter to restrict input to numeric characters
        DocumentFilter filter3 = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string == null)
                    return;

                // Check if the resulting text is numeric
                if (string.matches("[0-9]+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                // Handle replacement (e.g., when the user pastes text)
                if (text.matches("[0-9]+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
        minutesWorked.setPreferredSize(new Dimension(250, 50));

        submitBtn = new JButton("Submit");
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskData taskData = collectTaskData();
                //CsvWriter.writeToCSV("/Users/samuilmladenov/Desktop/TaskTracker/TaskTracker/src/tasks.csv", taskData);
                JOptionPane.showMessageDialog(frame, "Task added successfully!");
                ExcelWriter excelWriter = new ExcelWriter();
                excelWriter.writeTaskToExcel(taskData);
            }
        });


        backBtn = new JButton("Back");
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SelectionMenu afterGoBack = new SelectionMenu();

            }
        });

        panel2.add(monthlbl);
        panel2.add(chooseMonth);
        panel2.add(datelbl);
        panel2.add(chooseDate);
        panel2.add(yearlbl);
        panel2.add(chooseYear);
        panel2.add(namelbl);
        panel2.add(taskName);
        panel2.add(timeWorkedLbl);
        panel2.add(minutesWorked);
        panel2.add(submitBtn);
        panel2.add(backBtn);
        frame.add(panel2);
        frame.pack();
        frame.setVisible(true);



    }

    /**
     * Collects data from the form fields to create a TaskData object.
     *
     * @return TaskData object containing the collected task information.
     */
    public TaskData collectTaskData() {
        // Assuming you have methods to get values from your form fields.
        String position = GlobalData.getWorkingPosition();
        int day = Integer.parseInt(chooseDate.getText());

        // Get the selected month name from the spinner
        String selectedMonthName = chooseMonth.getValue().toString();
        // Convert month name to month number (1 for January, 2 for February, etc.)
        String[] months = new DateFormatSymbols().getMonths();
        int month = Arrays.asList(months).indexOf(selectedMonthName) + 1;

        int year = Integer.parseInt(chooseYear.getText());
        String task = taskName.getText();
        int duration = Integer.parseInt(minutesWorked.getText());

        return new TaskData(position, day, month, year, task, duration);
    }

}

