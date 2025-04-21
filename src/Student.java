import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private ArrayList<Integer> grades;

    // Constructor
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    // Method to add a single grade
    public void addGrade(int grade) {
        grades.add(grade);
    }

    // Method overloading - add multiple grades
    public void addGrade(int[] grades) {
        for (int grade : grades) {
            this.grades.add(grade);
        }
    }

    // Calculate average grade
    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        
        return (double) sum / grades.size();
    }

    // Display student details
    public void displayDetails() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Grades: " + grades);
        System.out.println("Average Grade: " + String.format("%.2f", calculateAverage()));
    }
} 