import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    private static List<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void registerUser() {
        System.out.print("Enter new username: ");
        String username = scanner.next();
        System.out.print("Enter new password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Please try a different one.");
                return;
            }
        }

        users.add(new User(username, password));
        System.out.println("User registered successfully.");
    }

    public static boolean authenticate() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful.");
                return true;
            }
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public static List<User> getUsers() {
        return users;
    }
}
