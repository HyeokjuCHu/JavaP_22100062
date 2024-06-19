

import java.io.*;
import java.util.*;

public class Login {
    private static final String FILE_PATH = "C:\\권혁주\\java\\Final_22100062_HyeokjuKwon\\src\\users.txt";
    private static List<User> users = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    static {
        loadUsersFromFile();
    }

    public static void registerUser() {
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
        saveUsersToFile();
        System.out.println("User registered successfully.");
    }

    public static boolean authenticate() {
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

    public static void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    private static void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.add(new User(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
    }

    public static List<User> getUsers() {
        return users;
    }
}
