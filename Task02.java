import java.util.Scanner;

public class Task02 {

    // Method to calculate grade based on average percentage
    public static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of subjects: ");
            int numberOfSubjects = scanner.nextInt();
            int[] marks = new int[numberOfSubjects];
            int totalMarks = 0;
            
            // Input marks for each subject
            for (int i = 0; i < numberOfSubjects; i++) {
                System.out.print("Enter the marks obtained in subject " + (i + 1) + " (out of 100): ");
                marks[i] = scanner.nextInt();
                totalMarks += marks[i]; // Sum up the total marks
            }
            
            // Calculate average percentage
            double averagePercentage = (double) totalMarks / numberOfSubjects;
            
            // Calculate grade based on the average percentage
            String grade = calculateGrade(averagePercentage);
            
            // Display results
            System.out.println("\n--- Results ---");
            System.out.println("Total Marks Obtained: " + totalMarks + " out of " + numberOfSubjects*100);
            System.out.println("Average Percentage: " + averagePercentage + "%");
            System.out.println("Grade: " + grade);
        }
    }
}
