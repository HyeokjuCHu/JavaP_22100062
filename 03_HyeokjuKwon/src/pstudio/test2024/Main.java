package pstudio.test2024;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main test2024 = new Main();
        test2024.run();
    }

    public void printMenu(){
        System.out.print("\n[Menu] "+
                "1. Add "+
                "2. Delete "+
                "3. Update "+
                "4. List "+
                "5. Save "+
                "6. Report "+
                "7. Find "+
                "8. Find by level "+
                "0. Quit > ");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        BmiCRUD manager = new BmiCRUD();
        try {
            manager.loadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean quit = false;
        do {
            printMenu();
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    manager.addItem();
                    break;
                case 2:
                    manager.deleteItem();
                    break;
                case 3:
                    manager.updateItem();
                    break;
                case 4:
                    manager.printAll();
                    break;
                case 5:
                    try {
                        manager.saveData();
                        System.out.println("Data saved.");
                    } catch (IOException e) {
                        System.out.println("Error saving data.");
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        manager.Filewrite();
                        System.out.println("Data saved.");
                    } catch (IOException e) {
                        System.out.println("Error saving data.");
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    manager.findWithinBMIRange();
                    break;
                case 8:
                    manager.searchByLevel();
                    break;
                case 0:
                    quit = true;
                    break;
                default:
                    System.out.println("Wrong number.\n");
                    break;
            }
        } while (!quit);
    }
}
