import java.util.Scanner;

// Model Class
class Student {
    private String name;
    private int id;
    private String grade;

    // Constructor
    public Student(String name, int id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

// View Class
class StudentView {
    public void displayStudentDetails(String studentName, int studentId, String studentGrade) {
        System.out.println("Student Details:");
        System.out.println("Name: " + studentName);
        System.out.println("ID: " + studentId);
        System.out.println("Grade: " + studentGrade);
    }
}

// Controller Class
class StudentController {
    private Student model;
    private StudentView view;

    // Constructor
    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    // Get student details
    public String getStudentName() {
        return model.getName();
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public int getStudentId() {
        return model.getId();
    }

    public void setStudentId(int id) {
        model.setId(id);
    }

    public String getStudentGrade() {
        return model.getGrade();
    }

    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }

    // Update view
    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}

// Main Class
public class MVCPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a model
        Student model = new Student("John Doe", 1, "A");

        // Create a view
        StudentView view = new StudentView();

        // Create a controller
        StudentController controller = new StudentController(model, view);

        // Display initial details
        System.out.println("Initial Student Details:");
        controller.updateView();

        // Get user input to update student details
        System.out.println("\nEnter new student details:");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        controller.setStudentName(name);

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        controller.setStudentId(id);
        scanner.nextLine();  // Consume newline

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();
        controller.setStudentGrade(grade);

        // Display updated details
        System.out.println("\nUpdated Student Details:");
        controller.updateView();

        scanner.close();
    }
}
