/**
 * File: ExecutiveMenu.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */
package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExecutiveMenu {
    AppFrame frame;
    JButton editRequestsBtn;
    JButton deleteRequestsBtn;
    JPanel panel1;

    ExecutiveMenu() {
        frame = new AppFrame();
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        editRequestsBtn = new JButton("Edit Requests");
        editRequestsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        editRequestsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                EditRequestsPanel editRequestsPanel = new EditRequestsPanel();
            }
        });

        deleteRequestsBtn = new JButton("Delete Requests");
        deleteRequestsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteRequestsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                DeleteRequestsPanel deleteRequestsPanel = new DeleteRequestsPanel();
            }
        });

        panel1.add(editRequestsBtn);
        panel1.add(deleteRequestsBtn);
        frame.add(panel1);
        frame.setVisible(true);
    }
}

