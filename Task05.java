import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Student Class
class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    // Constructor
    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Display student info
    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

// Student Management System Class
class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_PATH = "students.ser"; // File for storing student data

    // Constructor - load data from file if it exists
    public StudentManagementSystem() {
        students = loadStudentsFromFile();
        if (students == null) {
            students = new ArrayList<>();
        }
    }

    // Method to add a student
    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
        System.out.println("Student added successfully.");
    }

    // Method to remove a student
    public void removeStudent(int rollNumber) {
        Student student = findStudent(rollNumber);
        if (student != null) {
            students.remove(student);
            saveStudentsToFile();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    // Method to search for a student
    public Student findStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    // Method to display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("List of Students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Method to save students to a file
    private void saveStudentsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    // Method to load students from a file
    @SuppressWarnings("unchecked")
    private List<Student> loadStudentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null; // No data found or file doesn't exist yet
        }
    }
}

// Main Class (User Interface)
public class Task05 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManagementSystem sms = new StudentManagementSystem();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> searchStudent();
                case 4 -> sms.displayAllStudents();
                case 5 -> System.out.println("Exiting the system...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }

    // Method to add a student with input validation
    private static void addStudent() {
        scanner.nextLine(); // Clear buffer
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();

        System.out.print("Enter Grade: ");
        String grade = scanner.next().toUpperCase();
        if (grade.isEmpty()) {
            System.out.println("Grade cannot be empty.");
            return;
        }

        Student student = new Student(name, rollNumber, grade);
        sms.addStudent(student);
    }

    // Method to remove a student
    private static void removeStudent() {
        System.out.print("Enter Roll Number of student to remove: ");
        int rollNumber = scanner.nextInt();
        sms.removeStudent(rollNumber);
    }

    // Method to search for a student
    private static void searchStudent() {
        System.out.print("Enter Roll Number of student to search: ");
        int rollNumber = scanner.nextInt();
        Student student = sms.findStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }
}
