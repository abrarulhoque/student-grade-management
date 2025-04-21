# Student Grade Management System

A Java application for managing student records, grades, and performance. This system allows users to add, search, update, and delete students, as well as track their grades and calculate averages.

## Features

- Add and manage both undergraduate and graduate students
- Add grades for students
- Calculate average grades
- List all students
- Search for students by ID
- Sort students by their average grades
- Save and load data to/from file
- Display the top-performing student

## Requirements

- Java Development Kit (JDK) 8 or higher

## How to Compile and Run

### Using Command Line

1. Navigate to the project directory in your terminal

2. Compile all Java files:
   ```
   javac src/*.java -d .
   ```

3. Run the program:
   ```
   java StudentGradeSystem
   ```

### Using an IDE (Recommended)

1. **IntelliJ IDEA**:
   - Open IntelliJ IDEA
   - Select "Open" and choose the project directory
   - Right-click on the `StudentGradeSystem.java` file
   - Select "Run 'StudentGradeSystem.main()'"

2. **Eclipse**:
   - Open Eclipse
   - Go to File > Import > General > Existing Projects into Workspace
   - Select the project directory
   - Right-click on the `StudentGradeSystem.java` file
   - Select "Run As > Java Application"

3. **VS Code**:
   - Open VS Code
   - Open the project folder
   - Install the "Extension Pack for Java" if not already installed
   - Open `StudentGradeSystem.java`
   - Click the "Run" button above the main method

## Usage

The program provides a menu-driven interface with the following options:

1. Add New Student - Add a new undergraduate or graduate student
2. Add Grades for a Student - Add one or more grades for an existing student
3. Search for a Student - Look up a student by their ID
4. Display Top Student - Show the student with the highest average grade
5. List All Students - Display all students with their details
6. Remove a Student - Delete a student from the system
7. Sort Students by Average Grade - Sort and display students by their grades
8. Save Data - Save the current data to a file
9. Exit - Close the program (data is automatically saved)

## Data Persistence

The program saves all student data to a file named `students.txt` in the same directory as the application. This data is automatically loaded when the program starts and saved when it exits.

## Classes Overview

- **Student** - Base class representing an undergraduate student
- **GraduateStudent** - Subclass of Student with additional thesis grade
- **GradeManager** - Class for managing a collection of students
- **StudentGradeSystem** - Main class with the program's entry point and menu interface

## Project Structure

```
Student Grade Manage/
├── src/
│   ├── Student.java             # Base student class
│   ├── GraduateStudent.java     # Graduate student subclass
│   ├── GradeManager.java        # Student collection manager
│   └── StudentGradeSystem.java  # Main class with UI
├── README.md                    # This file
└── students.txt                 # Data storage file (created on first run)
``` 