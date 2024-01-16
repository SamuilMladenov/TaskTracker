/**
 * File: GlobalData.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */
package org.example;

public class GlobalData {
    private static String workingPosition;

    public static String getWorkingPosition() {
        return workingPosition;
    }

    public static void setWorkingPosition(String position) {
        workingPosition = position;
    }
}
