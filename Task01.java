import java.util.Random;
import java.util.Scanner;

public class Task01 {

    // Method to generate a random number
    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int min = 1, max = 100, maxAttempts = 7;
            boolean playAgain;
            do {
                int randomNumber = generateRandomNumber(min, max);
                int attempts = 0;
                boolean guessedCorrectly = false;
                
                System.out.println("Welcome to the Number Guessing Game!");
                System.out.println("I have generated a number between " + min + " and " + max + ". Can you guess it?");
                System.out.println("You have " + maxAttempts + " attempts to guess the number.");
                
                // Loop for guessing
                while (attempts < maxAttempts && !guessedCorrectly) {
                    System.out.print("Enter your guess: ");
                    int userGuess = scanner.nextInt();
                    attempts++;
                    
                    // Check the guess
                    if (userGuess == randomNumber) {
                        guessedCorrectly = true;
                        System.out.println("Congratulations! You guessed the number correctly in " + attempts + " attempts.");
                    } else if (userGuess > randomNumber) {
                        System.out.println("Too high! Try again.");
                    } else {
                        System.out.println("Too low! Try again.");
                    }
                }
                
                // Check if the user ran out of attempts
                if (!guessedCorrectly) {
                    System.out.println("Sorry, you've run out of attempts. Only 7 attempts are allowed. The correct number was " + randomNumber);
                }
                
                // Ask if they want to play again
                System.out.print("Do you want to play again? (yes/no): ");
                playAgain = scanner.next().equalsIgnoreCase("yes");
                
            } while (playAgain);
            System.out.println("Thanks for playing!");
        }
    }
}