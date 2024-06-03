import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class KHJBank {
    private static Account[] accountArray = new Account[100];
    private static Scanner sc = new Scanner(System.in);
    private static final String PREFIX = "Bank-";
    private static int seq = 0;

    public static void main(String[] args) {
        boolean runProgram = true;

        while (runProgram) {
            boolean loggedIn = false;
            while (!loggedIn) {
                System.out.println("-------------------------------------");
                System.out.println("1. Sign Up | 2. Sign in | 3. End");
                System.out.println("-------------------------------------");
                System.out.print("Choice> ");
                int selectNo = sc.nextInt();
                sc.nextLine();  // Clear the buffer after nextInt()
                switch (selectNo) {
                    case 1:
                        Login.register();
                        break;
                    case 2:
                        loggedIn = Login.login();
                        break;
                    case 3:
                        System.out.println("End Program");
                        return;
                }
            }

            boolean run = true;
            while (run) {
                System.out.println("-------------------------------------");
                System.out.println("1. Create Account - 2. Account List - 3. Deposit - 4. Withdraw - 5. End - 6. Log out");
                System.out.println("-------------------------------------");
                System.out.print("Choice> ");
                int selectNo = sc.nextInt();
                sc.nextLine();  // Clear the buffer after nextInt()
                switch (selectNo) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        accountList();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        withdraw();
                        break;
                    case 5:
                        run = false;
                        runProgram = false;
                        break;
                    case 6:
                        run = false; // Go back to login screen
                        break;
                }
            }
        }
        System.out.println("Terminated Program");
    }

    private static void withdraw() {
        if (!isRegistered()) {
            System.out.println("No Account Exist.");
            return;
        }
        accountList();
        System.out.println("Choose account that you want to withdraw from>");
        Account account;
        while (true) {
            String ano = sc.next();
            sc.nextLine();  // Clear the buffer after next()
            account = findAccount(ano);
            if (account == null)
                System.out.println("Check your account number");
            else
                break;
        }
        System.out.print("How much do you want to withdraw> ");
        int amount = sc.nextInt();
        sc.nextLine();  // Clear the buffer after nextInt()
        int result;
        try {
            result = account.withdraw(amount);
            System.out.println("Withdraw: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deposit() {
        if (!isRegistered()) {
            System.out.println("No Account Exist.");
            return;
        }
        accountList();
        System.out.println("Choose account that you want to deposit into>");
        Account account;
        while (true) {
            String ano = sc.next();
            sc.nextLine();  // Clear the buffer after next()
            account = findAccount(ano);
            if (account == null)
                System.out.println("No Account Exist.");
            else
                break;
        }
        System.out.print("How much do you want to deposit?> ");
        int amount = sc.nextInt();
        sc.nextLine();  // Clear the buffer after nextInt()
        account.deposit(amount);
        System.out.println("Deposit Completed");
    }

    private static void accountList() {
        if (!isRegistered()) {
            System.out.println("No Account Exist.");
            return;
        }
        for (Account account : accountArray) {
            if (account != null) {
                System.out.println(account.getAno() + " " + account.getOwner() + " " + account.getBalance());
            }
        }
    }

    private static void createAccount() {
        String ano = PREFIX + String.format(new DecimalFormat("0000").format(++seq));
        sc.nextLine();
        System.out.print("Name> ");
        String owner = sc.nextLine();
        int amount = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Initial Deposit> ");
                amount = sc.nextInt();
                sc.nextLine();  // Clear the buffer after nextInt()
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input! Type in correct number.");
                sc.next(); // Clear the invalid input from the scanner
            }
        }

        for (int i = 0; i < accountArray.length; i++) {
            if (accountArray[i] == null) {
                accountArray[i] = new Account(ano, owner, amount);
                System.out.println("Created Account");
                break;
            }
        }
    }

    private static boolean isRegistered() {
        for (Account account : accountArray) {
            if (account != null) {
                return true;
            }
        }
        return false;
    }

    private static Account findAccount(String ano) {
        for (Account account : accountArray) {
            if (account != null && account.getAno().equals(ano)) {
                return account;
            }
        }
        return null;
    }
}
