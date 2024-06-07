import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class KHJBank {
    private static Account[] accounts = new Account[100];
    private static Scanner scanner = new Scanner(System.in);
    private static final String PREFIX = "Bank-";
    private static int sequence = 0;
    private static boolean accountCreated = false;

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            boolean isLoggedIn = false;
            while (!isLoggedIn) {
                System.out.println("-------------------------------------");
                System.out.println("1. Register | 2. Log in | 3. Exit");
                System.out.println("-------------------------------------");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        Login.registerUser();
                        break;
                    case 2:
                        isLoggedIn = Login.authenticate();
                        break;
                    case 3:
                        System.out.println("Exiting program.");
                        return;
                }
            }

            boolean sessionActive = true;
            while (sessionActive) {
                System.out.println("-------------------------------------");
                System.out.println("1. Open Account - 2. List Accounts - 3. Deposit - 4. Withdraw - 5. View Transactions - 6. Delete Account - 7. Exit - 8. Log out");
                System.out.println("-------------------------------------");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        listAccounts();
                        break;
                    case 3:
                        depositAmount();
                        break;
                    case 4:
                        withdrawAmount();
                        break;
                    case 5:
                        showTransactionHistory();
                        break;
                    case 6:
                        deleteUser();
                        break;
                    case 7:
                        sessionActive = false;
                        isRunning = false;
                        break;
                    case 8:
                        sessionActive = false;
                        break;
                }
            }
        }
        System.out.println("Program terminated.");
    }

    private static void withdrawAmount() {
        if (!hasAccounts()) {
            System.out.println("No accounts available.");
            return;
        }
        listAccounts();
        System.out.print("Enter account number to withdraw from: ");
        Account account;
        while (true) {
            String accountNumber = scanner.next();
            account = findAccount(accountNumber);
            if (account == null)
                System.out.println("Invalid account number, try again.");
            else
                break;
        }
        System.out.print("Enter withdrawal amount: ");
        int amount = scanner.nextInt();
        try {
            int withdrawn = account.withdraw(amount);
            System.out.println("Withdrawn: " + withdrawn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void depositAmount() {
        if (!hasAccounts()) {
            System.out.println("No accounts available.");
            return;
        }
        listAccounts();
        System.out.print("Enter account number to deposit into: ");
        Account account;
        while (true) {
            String accountNumber = scanner.next();
            account = findAccount(accountNumber);
            if (account == null)
                System.out.println("Invalid account number, try again.");
            else
                break;
        }
        System.out.print("Enter deposit amount: ");
        int amount = scanner.nextInt();
        account.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private static void listAccounts() {
        if (!hasAccounts()) {
            System.out.println("No accounts available.");
            return;
        }
        for (Account account : accounts) {
            if (account != null) {
                System.out.println(account.getAccountNumber() + " " + account.getOwner() + " " + account.getBalance());
            }
        }
    }

    private static void createAccount() {
        String accountNumber = PREFIX + new DecimalFormat("0000").format(++sequence);
        scanner.nextLine();
        System.out.print("Enter your name: ");
        String owner = scanner.nextLine();
        int initialDeposit = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter initial deposit: ");
                initialDeposit = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                scanner.next();
            }
        }

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = new Account(accountNumber, owner, initialDeposit);
                System.out.println("Account created successfully.");
                accountCreated = true;
                break;
            }
        }
    }

    private static boolean hasAccounts() {
        return accountCreated;
    }

    private static Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account != null && account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private static void showTransactionHistory() {
        if (!hasAccounts()) {
            System.out.println("No accounts available.");
            return;
        }
        listAccounts();
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        for (String transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    private static void deleteUser() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        for (int i = 0; i < Login.getUsers().size(); i++) {
            User user = Login.getUsers().get(i);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                Login.getUsers().remove(i);
                System.out.println("User deleted successfully.");
                return;
            }
        }
        System.out.println("Invalid username or password.");
    }
}
