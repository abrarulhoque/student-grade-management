import java.util.Scanner;

public class StudentGradeSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static GradeManager gradeManager = new GradeManager();

    public static void main(String[] args) {
        System.out.println("Welcome to Student Grade Management System");
        
        // Load data from file
        gradeManager.loadDataFromFile();
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1: // Add new student
                    addStudent();
                    break;
                case 2: // Add grades for a student
                    addGrades();
                    break;
                case 3: // Search for a student
                    searchStudent();
                    break;
                case 4: // Display top student
                    displayTopStudent();
                    break;
                case 5: // List all students
                    gradeManager.listAllStudents();
                    break;
                case 6: // Remove a student
                    removeStudent();
                    break;
                case 7: // Sort students by average
                    sortStudents();
                    break;
                case 8: // Save data to file
                    gradeManager.saveDataToFile();
                    break;
                case 9: // Exit
                    System.out.println("Saving data before exit...");
                    gradeManager.saveDataToFile();
                    System.out.println("Thank you for using Student Grade Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n==== Menu ====");
        System.out.println("1. Add New Student");
        System.out.println("2. Add Grades for a Student");
        System.out.println("3. Search for a Student");
        System.out.println("4. Display Top Student");
        System.out.println("5. List All Students");
        System.out.println("6. Remove a Student");
        System.out.println("7. Sort Students by Average Grade");
        System.out.println("8. Save Data");
        System.out.println("9. Exit");
        System.out.println("=============");
    }

    private static void addStudent() {
        int id = getIntInput("Enter student ID: ");
        
        // Check if ID already exists
        if (gradeManager.getStudentById(id) != null) {
            System.out.println("Error: A student with this ID already exists!");
            return;
        }
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("Is this a graduate student? (y/n): ");
        String isGrad = scanner.nextLine().trim().toLowerCase();
        
        Student student;
        if (isGrad.equals("y") || isGrad.equals("yes")) {
            student = new GraduateStudent(id, name);
            int thesisGrade = getIntInput("Enter thesis grade (0-100): ", 0, 100);
            ((GraduateStudent) student).setThesisGrade(thesisGrade);
        } else {
            student = new Student(id, name);
        }
        
        gradeManager.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private static void addGrades() {
        int id = getIntInput("Enter student ID: ");
        Student student = gradeManager.getStudentById(id);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        int numGrades = getIntInput("How many grades would you like to add? ");
        for (int i = 0; i < numGrades; i++) {
            int grade = getIntInput("Enter grade " + (i + 1) + " (0-100): ", 0, 100);
            student.addGrade(grade);
        }
        
        System.out.println("Grades added successfully!");
    }

    private static void searchStudent() {
        int id = getIntInput("Enter student ID to search: ");
        Student student = gradeManager.getStudentById(id);
        
        if (student == null) {
            System.out.println("Student not found!");
        } else {
            System.out.println("\n==== Student Details ====");
            student.displayDetails();
        }
    }

    private static void displayTopStudent() {
        Student topStudent = gradeManager.getTopStudent();
        
        if (topStudent == null) {
            System.out.println("No students found in the system.");
        } else {
            System.out.println("\n==== Top Student ====");
            topStudent.displayDetails();
        }
    }

    private static void removeStudent() {
        int id = getIntInput("Enter student ID to remove: ");
        boolean removed = gradeManager.removeStudent(id);
        
        if (removed) {
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void sortStudents() {
        gradeManager.sortStudentsByAverage();
        System.out.println("Students sorted by average grade (highest to lowest).");
        gradeManager.listAllStudents();
    }

    // Helper method to get valid integer input with validation
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    // Helper method to get integer input within range
    private static int getIntInput(String prompt, int min, int max) {
        while (true) {
            int value = getIntInput(prompt);
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            }
        }
    }
} 