import java.util.ArrayList;
import java.util.Scanner;

// BankAccount class to store account balance and transaction history
class BankAccount {
    private double balance;
    private ArrayList<String> transactionHistory;

    // Constructor to initialize the balance and transaction history
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    // Method to deposit money
    public String deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + String.format("%.2f", amount));
            return "Successfully deposited $" + amount + ". New balance: $" + String.format("%.2f", balance);
        } else {
            return "Invalid deposit amount. Please enter a positive number.";
        }
    }

    // Method to withdraw money
    public String withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                transactionHistory.add("Withdrew: $" + String.format("%.2f", amount));
                return "Successfully withdrew $" + amount + ". New balance: $" + String.format("%.2f", balance);
            } else {
                return "Insufficient balance for this transaction.";
            }
        } else {
            return "Invalid withdrawal amount. Please enter a positive number.";
        }
    }

    // Method to check the current balance
    public String checkBalance() {
        return "Your current balance is: $" + String.format("%.2f", balance);
    }

    // Method to return transaction history
    public String getTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            return "No transactions have been made yet.";
        } else {
            StringBuilder history = new StringBuilder();
            for (String transaction : transactionHistory) {
                history.append(transaction).append("\n");
            }
            return history.toString();
        }
    }
}

// ATM class to interact with the BankAccount class
class ATM {
    private BankAccount bankAccount;
    private String correctPin;
    private final Scanner scanner; // Use a single Scanner instance

    // Constructor to initialize the bank account, the correct PIN, and the scanner
    public ATM(BankAccount bankAccount, String pin, Scanner scanner) {
        this.bankAccount = bankAccount;
        this.correctPin = pin;
        this.scanner = scanner; // Pass the scanner instance
    }

    public ATM(Scanner scanner) {
        this.scanner = scanner;
    }

    // Authenticate user with PIN
    private boolean authenticate() {
        int tries = 3;
        while (tries > 0) {
            System.out.print("Please enter your PIN (Default pin: 1234): ");
            String enteredPin = scanner.nextLine();
            if (enteredPin.equals(correctPin)) {
                System.out.println("PIN authentication successful!");
                return true;
            } else {
                tries--;
                System.out.println("Incorrect PIN. " + tries + " tries left.");
            }
        }
        System.out.println("Failed authentication. Exiting the system.");
        return false;
    }

    // Display ATM options to the user
    private void displayMenu() {
        System.out.println("\nWelcome to the ATM");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. View Transaction History");
        System.out.println("5. Exit");
    }

    // Handle user selection
    public void selectOption() {
        if (authenticate()) { // Only allow access if authentication succeeds
            while (true) {
                displayMenu();
                System.out.print("\nPlease choose an option: ");
                String choice = scanner.nextLine(); // Use the scanner for input

                switch (choice) {
                    case "1" -> System.out.println(bankAccount.checkBalance());
                    case "2" -> {
                        System.out.print("Enter the amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the leftover newline
                        System.out.println(bankAccount.deposit(depositAmount));
                    }
                    case "3" -> {
                        System.out.print("Enter the amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume the leftover newline
                        System.out.println(bankAccount.withdraw(withdrawAmount));
                    }
                    case "4" -> {
                        System.out.println("Transaction History:");
                        System.out.println(bankAccount.getTransactionHistory());
                    }
                    case "5" -> {
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}

// Main class to run the ATM system
public class Task03 {
    public static void main(String[] args) {
        // Create a new bank account with an initial balance
        try ( // Create a single scanner instance to be used throughout the program
                Scanner scanner = new Scanner(System.in)) {
            // Create a new bank account with an initial balance
            BankAccount userAccount = new BankAccount(100.0);
            // Create an ATM instance linked to the user's bank account and PIN
            ATM atm = new ATM(userAccount, "1234", scanner); // The PIN is "1234"
            // Start the ATM interface
            atm.selectOption();
            // Close the scanner when done
        }
    }
}
