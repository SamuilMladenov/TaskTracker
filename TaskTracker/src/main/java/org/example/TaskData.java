/**
 * File: TaskData.java
 * Author: Samuil Mladenov
 * Date: 11/25/2023
 */
package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskData {
    // Instance variables
    private String position;
    private LocalDate date;
    private String taskName;
    private int duration;

    // Default constructor
    public TaskData(){
        this.position = "nillche";
        this.date= LocalDate.parse("01.01.1900");
        this.taskName="No task";
        this.duration = 0;
    }

    // Constructor with parameters for date components
    public TaskData(String position, int day, int month, int year, String taskName, int duration) {
        this.position = position;
        this.date = LocalDate.of(year, month, day);
        this.taskName = taskName;
        this.duration = duration;
    }

    // Constructor with parameters for LocalDate
    public TaskData(String position, LocalDate date, String taskName, int duration) {
        this.position = position;
        this.date = date;
        this.taskName = taskName;
        this.duration = duration;
    }

    // Constructor for creating TaskData from updated data
    public TaskData(String originalPosition, LocalDate originalDate, String originalTaskName, int originalDuration,
                    String updatedPosition, LocalDate updatedDate, String updatedTaskName, int updatedDuration) {
        this(originalPosition, originalDate, originalTaskName, originalDuration);
        // Additional fields for updated data
        this.position = updatedPosition;
        this.date = updatedDate;
        this.taskName = updatedTaskName;
        this.duration = updatedDuration;
    }

    // Constructor to handle creating TaskData from cell values
    public TaskData(Cell positionCell, Cell dateCell, Cell taskNameCell, Cell durationCell) {
        // Assuming positionCell, dateCell, taskNameCell, and durationCell are the cells from the Excel sheet
        this.position = getStringCellValue(positionCell);
        this.date = getDateCellValue(dateCell);
        this.taskName = getStringCellValue(taskNameCell);
        this.duration = getNumericCellValue(durationCell);
    }

    // Helper method to safely get a string value from a cell
    private String getStringCellValue(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC) {
                // Convert numeric value to string
                return String.valueOf((int) cell.getNumericCellValue());
            }
        }
        return null; // Return null for other cell types or null cells
    }

    // Helper method to safely get a date value from a cell
    private LocalDate getDateCellValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        }
        return null;
    }

    // Helper method to safely get a numeric value from a cell
    private int getNumericCellValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return 0; // Return a default value for other cell types or null cells
    }

    // Getter for formatted date
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    // Getter for taskName
    public String getTaskName() {
        return taskName;
    }

    // Getter for duration
    public int getDuration() {
        return duration;
    }

    // Getter for position
    public String getPosition(){
        return position;
    }

    // Setter for position
    public void setPosition(String pos){
        position = pos;
    }

    // Setter for date
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Setter for taskName
    public void setTaskName(String taskn){
        this.taskName = taskn;
    }

    // Setter for duration
    public void setDuration(int dur){
        this.duration = dur;
    }

    // Override toString method for better readability
    @Override
    public String toString() {
        return "Position: " + position +
                ", Date: " + getFormattedDate() +
                ", Task Name: " + taskName +
                ", Duration: " + duration;
    }
}
