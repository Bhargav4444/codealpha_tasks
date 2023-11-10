import java.util.Scanner;
public class ATMInterface {
    public static void main(String[] args) {
        // Initialize account with user ID, PIN, and initial balance
        Account userAccount = new Account("12345678", "0495", 4000.00);

        // Create an ATM instance with the user's account
        ATM atm = new ATM(userAccount);

        // Start the ATM interface
        atm.start();
    }
}

// Class to represent a user account
class Account {
    private String userId;
    private String pin;
    private double balance;

    // Constructor to initialize the account
    public Account(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    // Getter methods
    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    // Method to transfer money to another account
    public void transfer(Account recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }
}

// Class to represent the ATM interface
class ATM {
    private Account account;
    private Scanner scanner;

    // Constructor to initialize the ATM with an account
    public ATM(Account account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Method to start the ATM interface
    public void start() {
        System.out.println("Welcome to the ATM");
        authenticate(); // Authenticate the user
        showMenu();     // Display the main menu
    }

    // Method to authenticate the user by entering user ID and PIN
    private void authenticate() {
        System.out.print("Enter user ID: ");
        String userIdInput = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pinInput = scanner.nextLine();

        if (userIdInput.equals(account.getUserId()) && pinInput.equals(account.getPin())) {
            System.out.println("Authentication successful");
        } else {
            System.out.println("Authentication failed.\nExiting...");
            System.exit(0);
        }
    }

    // Method to display the main menu and handle user choices
    private void showMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: ₹" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful. New balance: ₹" + account.getBalance());
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    System.out.println("Withdrawal successful. New balance: ₹" + account.getBalance());
                    break;
                case 4:
                    System.out.print("Enter recipient's user ID: ");
                    String recipientId = scanner.next();
                    Account recipient = new Account(recipientId, "", 0);
                    System.out.print("Enter transfer amount: ₹");
                    double transferAmount = scanner.nextDouble();
                    account.transfer(recipient, transferAmount);
                    System.out.println("Transfer successful. New balance: ₹" + account.getBalance());
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM.\nExiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
