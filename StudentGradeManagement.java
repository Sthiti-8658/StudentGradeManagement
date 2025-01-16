package java_projects;

import java.util.ArrayList;
import java.util.Scanner;

//student class
class Student {
    private int id;
    private String name;
    private ArrayList<Double> testScores;
    private double gpa;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.testScores = new ArrayList<>();
        this.gpa = 0.0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public void addTestScore(double score) {
        testScores.add(score);
        calculateGPA();
    }

    public void updateTestScore(int index, double score) {
        if (index >= 0 && index < testScores.size()) {
            testScores.set(index, score);
            calculateGPA();
        } else {
            System.out.println("Invalid test index!");
        }
    }

    public void calculateGPA() {
        if (testScores.isEmpty()) {
            gpa = 0.0;
        } else {
            double total = 0.0;
            for (double score : testScores) {
                total += score;
            }
            gpa = total / testScores.size();
        }
    }

    public String getGrade() {
        if (gpa >= 90) return "A";
        else if (gpa >= 80) return "B";
        else if (gpa >= 70) return "C";
        else if (gpa >= 60) return "D";
        else return "F";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", GPA: " + gpa + ", Grade: " + getGrade();
    }
}

// Main Class
public class StudentGradeManagement {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Student Grade Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Test Score");
            System.out.println("3. Update Test Score");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addTestScore();
                    break;
                case 3:
                    updateTestScore();
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        students.add(new Student(id, name));
        System.out.println("Student added successfully!");
    }

    private static void addTestScore() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        Student student = findStudentById(id);
        if (student != null) {
            System.out.print("Enter Test Score: ");
            double score = scanner.nextDouble();
            student.addTestScore(score);
            System.out.println("Test score added successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void updateTestScore() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        Student student = findStudentById(id);
        if (student != null) {
            System.out.print("Enter Test Index (0-based): ");
            int index = scanner.nextInt();
            System.out.print("Enter New Test Score: ");
            double score = scanner.nextDouble();
            student.updateTestScore(index, score);
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\n--- Student Records ---");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
}
}
