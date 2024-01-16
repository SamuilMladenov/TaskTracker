/**
 * File: AppFrame.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */

package org.example;

import javax.swing.*;

public class AppFrame extends JFrame {
    // Constructor for AppFrame
    AppFrame() {
        // Set properties for the frame
        this.setTitle("Task Tracker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);

        // Set the application icon
        ImageIcon image = new ImageIcon("BEST_Logo_Letterhead.png");
        this.setIconImage(image.getImage());

        // Close the frame immediately after setting properties (it may be unintentional)
        this.dispose();
    }
}

