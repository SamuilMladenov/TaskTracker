# Project Report: Task Tracker Application

# Project description
The Task Tracker Application is designed to help users manage and track their working tasks efficiently. It provides a user-friendly interface for adding, editing, and deleting tasks, as well as handling delete and edit requests. The application uses Java with the Swing library for the graphical user interface (GUI) and Apache POI for working with Excel files to store task data.

# What does the application do?
The Task Tracker Application allows users to perform the following actions:

- Add Tasks: Workers can add new tasks by providing details such as the working position, date, task name, and duration. The application then records this information and updates the Excel file.

- Edit Tasks: Workers can request edits to existing tasks by submitting changes to task data. The application supports accepting or declining these edit requests.

- Delete Tasks: Users can request the deletion of tasks. Similar to edit requests, these delete requests can be accepted or declined.

- View Edit Requests: The application displays a list of edit requests, allowing the Executive Director (or person responsible in the company) to choose which tasks to edit as the workers request.

- View Delete Requests: The application displays a list of delete requests, allowing the Executive Director (or person responsible in the company) to choose which tasks to delete as requested by the workers.

# Why You Used the Technologies You Used
Java with Swing
Java with the Swing library was chosen for the GUI due to its platform independence and ease of use. Swing provides a set of components for building graphical user interfaces and is well-suited for desktop applications.

Apache POI
Apache POI was selected for working with Excel files. This library allows seamless interaction with Excel sheets, enabling the application to read, write, and modify task data stored in an Excel file.

# Some of the Challenges You Faced
While developing the Task Tracker Application, several challenges were encountered:

- Excel Integration: Working with Excel files, especially handling edit and delete requests, required careful consideration of file manipulation to ensure data integrity.

- UI Design: Designing an intuitive and aesthetically pleasing user interface using Swing components presented challenges in terms of layout and component alignment.

- Data Validation: Ensuring that the data entered by users is valid and handling potential errors or inconsistencies posed challenges in the application's logic.

# Features You Hope to Implement in the Future
The Task Tracker Application aims to evolve with additional features in the future:

- Better User Authentication: Implementing better user authentication to enhance security and personalize the experience for different users.

- Data Analytics: Providing statistical insights into task completion trends, helping users analyze their productivity.

# How to Install and Run the Project

1. Download the .zip file
2. Unzip it
3. Open TaskTracker/out/artifacts 
4. Run the .jar file 

# How to Use the Project
Add a New Task:
1. Launch the application.
2. Log in by choosing your position and inputting your password.
3. Click on the task operation of your choice.
4. Fill out the information (for Add Task) or choose a Task (For Edit/Delete task)

Navigate Back:
At any point, you can use the "Back" option to return to the main menu.
