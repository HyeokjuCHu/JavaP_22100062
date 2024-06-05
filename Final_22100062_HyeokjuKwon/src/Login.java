import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    private static List<User> users = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void register() {
        System.out.print("Enter new username: ");
        String username = sc.next();
        System.out.print("Enter new password: ");
        String password = sc.next();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Try another one.");
                return;
            }
        }

        users.add(new User(username, password));
        System.out.println("User registered successfully.");
    }

    public static boolean login() {
        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

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
