import java.util.Scanner;

public class Main {

    static final int MAX = 100;

    static String[] name = new String[MAX];
    static String[] id = new String[MAX];
    static double[][] grade = new double[MAX][3];
    static double[] average = new double[MAX];
    static String[] remark = new String[MAX];

    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nSTUDENT GRADE MANAGEMENT SYSTEM");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }

            choice = sc.nextInt();
            sc.nextLine(); // clear newline

            if (choice == 1) {
                addStudent(sc);
            } else if (choice == 2) {
                displayStudents();
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    // main function for adding a student
    static void addStudent(Scanner sc) {
        if (count == MAX) {
            System.out.println("Storage full.");
            return;
        }

        System.out.print("Enter ID Number: ");
        id[count] = sc.nextLine();

        // call function that inputs name and grades
        inputNameAndGrades(sc, count);

        // compute average and remark
        average[count] = computeAverage(grade[count]);
        remark[count] = getRemark(average[count]);

        count++;
        System.out.println("Student added successfully.");
    }

    // NEW FUNCTION: input student name and grades
    static void inputNameAndGrades(Scanner sc, int index) {
        System.out.print("Enter Name: ");
        name[index] = sc.nextLine();

        // input 3 grades with validation
        for (int i = 0; i < 3; i++) {
            while (true) {
                System.out.print("Enter grade for subject " + (i + 1) + " (0-100): ");

                if (!sc.hasNextDouble()) {
                    System.out.println("Invalid input. Enter a number.");
                    sc.next(); // discard invalid input
                    continue;
                }

                double g = sc.nextDouble();

                if (g < 0 || g > 100) {
                    System.out.println("Grade must be between 0 and 100.");
                } else {
                    grade[index][i] = g;
                    break;
                }
            }
        }
        sc.nextLine(); // clear newline after last grade
    }

    // compute average
    static double computeAverage(double[] g) {
        return (g[0] + g[1] + g[2]) / 3.0;
    }

    // get remark based on average
    static String getRemark(double avg) {
        if (avg >= 90) return "Excellent";
        else if (avg >= 80) return "Very Good";
        else if (avg >= 70) return "Good";
        else if (avg >= 60) return "Needs Improvement";
        else return "Fail";
    }

    // display all students in clean format
    static void displayStudents() {
        if (count == 0) {
            System.out.println("No records to display.");
            return;
        }

        System.out.println("\n-----------------------------------------------------");
        System.out.printf("%-8s %-18s %-10s %-15s%n", "ID", "Name", "Average", "Remark");
        System.out.println("-----------------------------------------------------");

        for (int i = 0; i < count; i++) {
            System.out.printf("%-8s %-18s %-10.2f %-15s%n",
                    id[i], name[i], average[i], remark[i]);
        }

        System.out.println("-----------------------------------------------------");
    }
}
