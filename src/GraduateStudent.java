public class GraduateStudent extends Student {
    private int thesisGrade;

    public GraduateStudent(int id, String name) {
        super(id, name);
        this.thesisGrade = 0;
    }

    public int getThesisGrade() {
        return thesisGrade;
    }

    public void setThesisGrade(int thesisGrade) {
        this.thesisGrade = thesisGrade;
    }

    // Calculate final grade (60% coursework, 40% thesis)
    public double calculateFinalGrade() {
        double averageGrade = calculateAverage();
        return (averageGrade * 0.6) + (thesisGrade * 0.4);
    }

    // Method overriding - display details with thesis grade
    @Override
    public void displayDetails() {
        System.out.println("Graduate Student ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Grades: " + getGrades());
        System.out.println("Average Course Grade: " + String.format("%.2f", calculateAverage()));
        System.out.println("Thesis Grade: " + thesisGrade);
        System.out.println("Final Grade: " + String.format("%.2f", calculateFinalGrade()));
    }
} 