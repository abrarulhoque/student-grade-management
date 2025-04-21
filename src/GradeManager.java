import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GradeManager {
    private ArrayList<Student> students;
    private static final String DATA_FILE = "students.txt";

    public GradeManager() {
        students = new ArrayList<>();
    }

    // Add a student to the list
    public void addStudent(Student student) {
        students.add(student);
    }

    // Remove a student by ID
    public boolean removeStudent(int studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == studentId) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    // Get a student by ID
    public Student getStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    // List all students
    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }

        System.out.println("==== List of All Students ====");
        for (Student student : students) {
            student.displayDetails();
            System.out.println("-------------------------");
        }
    }

    // Get student with highest average grade
    public Student getTopStudent() {
        if (students.isEmpty()) {
            return null;
        }

        Student topStudent = students.get(0);
        double highestAverage = topStudent.calculateAverage();

        for (Student student : students) {
            double currentAverage = student.calculateAverage();
            if (currentAverage > highestAverage) {
                highestAverage = currentAverage;
                topStudent = student;
            }
        }

        return topStudent;
    }

    // Sort students by average grade (descending)
    public void sortStudentsByAverage() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.calculateAverage(), s1.calculateAverage());
            }
        });
    }

    // Get list of all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // Save data to file
    public void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Student student : students) {
                writer.write(student.getId() + "," + student.getName() + ",");
                
                // Write grades
                List<Integer> grades = student.getGrades();
                for (int i = 0; i < grades.size(); i++) {
                    writer.write(grades.get(i).toString());
                    if (i < grades.size() - 1) {
                        writer.write(";");
                    }
                }
                
                // Write thesis grade if it's a graduate student
                if (student instanceof GraduateStudent) {
                    writer.write("," + ((GraduateStudent) student).getThesisGrade());
                    writer.write(",G"); // G for graduate student
                } else {
                    writer.write(",U"); // U for undergraduate student
                }
                
                writer.newLine();
            }
            System.out.println("Data saved successfully to " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load data from file
    public void loadDataFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No saved data found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            students.clear(); // Clear existing data

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length < 3) continue; // Skip invalid lines
                
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String[] gradeStrings = parts[2].split(";");
                
                Student student;
                if (parts[parts.length - 1].equals("G")) {
                    // Graduate student
                    student = new GraduateStudent(id, name);
                    if (parts.length >= 4) {
                        ((GraduateStudent) student).setThesisGrade(Integer.parseInt(parts[3]));
                    }
                } else {
                    // Undergraduate student
                    student = new Student(id, name);
                }
                
                // Add grades
                for (String gradeStr : gradeStrings) {
                    if (!gradeStr.isEmpty()) {
                        student.addGrade(Integer.parseInt(gradeStr));
                    }
                }
                
                students.add(student);
            }
            System.out.println("Data loaded successfully from " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data: " + e.getMessage());
        }
    }
} 