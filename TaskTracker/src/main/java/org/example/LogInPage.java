/**
 * File: LoginPage.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */
package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPage extends AppFrame implements ActionListener {

    final JComboBox<String> positionComboBox;
    JButton loginBtn;
    AppFrame frame;
    final JPasswordField pfPassword;

    TaskData taskData;

    String position;

    LogInPage() {
        frame = new AppFrame(); // Create our app frame
        frame.setLayout(new CardLayout());

        // Class for panel - inherits the main frame
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Class for position picker - inherits the panel
        frame.add(panel);
        JLabel workPosition = new JLabel("Pick position");
        workPosition.setAlignmentX(Component.CENTER_ALIGNMENT);
        workPosition.setVisible(true);
        panel.add(workPosition);

        // Create a dropdown with position choices
        String[] choices = {"Executive director", "Business Development Manager", "Project Manager Tech", "Project Manager Logistics"};
        positionComboBox = new JComboBox<String>(choices);
        positionComboBox.setMaximumSize(positionComboBox.getPreferredSize());
        positionComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        positionComboBox.setVisible(true);
        panel.add(positionComboBox);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a password field
        pfPassword = new JPasswordField();
        pfPassword.setSize(180, 30);
        pfPassword.setMaximumSize(new Dimension(180, 30));
        pfPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPassword.setLabelFor(pfPassword);

        JButton btnGet = new JButton("Display Password");
        btnGet.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String password = new String(pfPassword.getPassword());
                        JOptionPane.showMessageDialog(frame, "Password is " + password);
                    }
                });
        btnGet.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblPassword);
        panel.add(pfPassword);
        panel.add(btnGet);

        // Create a login button
        loginBtn = new JButton("Log In");
        loginBtn.addActionListener(this);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(loginBtn);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) {
            String edPass = "ivanivan";
            String bdPass = "ivan123";
            String pmtPass = "ivanski";
            String pmlPass = "ivanivanivan";
            String p = new String(pfPassword.getPassword());

            // Check the selected position and compare passwords
            if ("Executive director".equals(positionComboBox.getSelectedItem())) {
                if (p.equals(edPass)) {
                    String selectedPosition = "Executive director";
                    GlobalData.setWorkingPosition(selectedPosition);
                    frame.dispose();
                    ExecutiveMenu selectStep = new ExecutiveMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong password!");
                }

            } else if ("Business Development Manager".equals(positionComboBox.getSelectedItem())) {
                if (p.equals(bdPass)) {
                    String selectedPosition = "Business Development Manager";
                    GlobalData.setWorkingPosition(selectedPosition);
                    frame.dispose();
                    SelectionMenu selectStep = new SelectionMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong password!");
                }
            } else if ("Project Manager Tech".equals(positionComboBox.getSelectedItem())) {
                if (p.equals(pmtPass)) {
                    String selectedPosition = "Project Manager Tech";
                    GlobalData.setWorkingPosition(selectedPosition);
                    frame.dispose();
                    SelectionMenu selectStep = new SelectionMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong password!");
                }

            } else if ("Project Manager Logistics".equals(positionComboBox.getSelectedItem())) {
                if (p.equals(pmlPass)) {
                    String selectedPosition = "Project Manager Logistics";
                    GlobalData.setWorkingPosition(selectedPosition);
                    frame.dispose();
                    SelectionMenu selectStep = new SelectionMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong password!");
                }
            }
        }
    }
}
