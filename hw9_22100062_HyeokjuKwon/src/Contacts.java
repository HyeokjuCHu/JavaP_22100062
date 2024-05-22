import java.io.*;
import java.util.*;

abstract class Person implements Serializable {
    protected String type;
    protected String dept;
    protected String name;
    protected String id;
    protected String email;

    public Person(String type, String dept, String name, String id, String email) {
        this.type = type;
        this.dept = dept;
        this.name = name;
        this.id = id;
        this.email = email;
    }

    abstract void writeOutput();

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }
}

abstract class Student extends Person {
    public Student(String type, String dept, String name, String id, String email) {
        super(type, dept, name, id, email);
    }
}

abstract class Employee extends Person {
    public Employee(String type, String dept, String name, String id, String email) {
        super(type, dept, name, id, email);
    }
}

class Under extends Student {
    private int studentNumber;

    public Under(String type, String dept, String name, String id, String email, int studentNumber) {
        super(type, dept, name, id, email);
        this.studentNumber = studentNumber;
    }

    @Override
    void writeOutput() {
        System.out.printf("%s, %s, Name: %s, Under Student Number: %d (%s)\n", type, dept, name, studentNumber, email);
    }
}

class Grad extends Student {
    private int studentNumber;

    public Grad(String type, String dept, String name, String id, String email, int studentNumber) {
        super(type, dept, name, id, email);
        this.studentNumber = studentNumber;
    }

    @Override
    void writeOutput() {
        System.out.printf("%s, %s, Name: %s, Grad Student Number: %d (%s)\n", type, dept, name, studentNumber, email);
    }
}

class Faculty extends Employee {
    private String facultyId;

    public Faculty(String type, String dept, String name, String id, String email, String facultyId) {
        super(type, dept, name, id, email);
        this.facultyId = facultyId;
    }

    @Override
    void writeOutput() {
        System.out.printf("%s, %s, Name: %s, Faculty ID: %s (%s)\n", type, dept, name, facultyId, email);
    }
}

class Staff extends Employee {
    public Staff(String type, String dept, String name, String id, String email) {
        super(type, dept, name, id, email);
    }

    @Override
    void writeOutput() {
        System.out.printf("%s, %s, Name: %s, Staff ID: %s (%s)\n", type, dept, name, id, email);
    }
}

public class Contacts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> contacts = new ArrayList<>();

        while (true) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split(", ");

                switch (parts[0]) {
                    case "print":
                        printContacts(contacts);
                        break;
                    case "sort":
                        if (parts.length > 1) {
                            sortContacts(contacts, parts[1]);
                        } else {
                            System.out.println("Error: Missing sort criteria (name/dept).");
                        }
                        break;
                    case "loadText":
                        if (parts.length > 1) {
                            contacts = loadFromTextFile(parts[1]);
                        } else {
                            System.out.println("Error: Missing filename for loadText.");
                        }
                        break;
                    case "saveText":
                        if (parts.length > 1) {
                            saveToTextFile(contacts, parts[1]);
                        } else {
                            System.out.println("Error: Missing filename for saveText.");
                        }
                        break;
                    case "loadBinary":
                        if (parts.length > 1) {
                            contacts = loadFromBinaryFile(parts[1]);
                        } else {
                            System.out.println("Error: Missing filename for loadBinary.");
                        }
                        break;
                    case "saveBinary":
                        if (parts.length > 1) {
                            saveToBinaryFile(contacts, parts[1]);
                        } else {
                            System.out.println("Error: Missing filename for saveBinary.");
                        }
                        break;
                    case "exit":
                        return;
                    default:
                        addContact(contacts, parts);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addContact(List<Person> contacts, String[] parts) {
        try {
            String type = parts[0];
            String dept = parts.length > 1 ? parts[1] : "Not Yet Defined";
            String name = parts.length > 2 ? parts[2] : "Not Yet Defined";
            String id = parts.length > 3 ? parts[3] : "Not Yet Defined";
            String email = parts.length > 4 ? parts[4] : "Not Yet Defined";

            switch (type) {
                case "Under":
                    int underStudentNumber = Integer.parseInt(id);
                    contacts.add(new Under(type, dept, name, id, email, underStudentNumber));
                    break;
                case "Grad":
                    int gradStudentNumber = Integer.parseInt(id);
                    contacts.add(new Grad(type, dept, name, id, email, gradStudentNumber));
                    break;
                case "Faculty":
                    contacts.add(new Faculty(type, dept, name, id, email, id));
                    break;
                case "Staff":
                    contacts.add(new Staff(type, dept, name, id, email));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for ID: " + parts[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Insufficient input data.");
        }
    }

    private static void printContacts(List<Person> contacts) {
        int underCount = 0;
        int gradCount = 0;
        int facultyCount = 0;
        int staffCount = 0;

        for (Person person : contacts) {
            if (person instanceof Under) {
                underCount++;
                System.out.printf("%s %d, %s\n", person.type, underCount, person.dept);
            } else if (person instanceof Grad) {
                gradCount++;
                System.out.printf("%s %d, %s\n", person.type, gradCount, person.dept);
            } else if (person instanceof Faculty) {
                facultyCount++;
                System.out.printf("%s %d, %s\n", person.type, facultyCount, person.dept);
            } else if (person instanceof Staff) {
                staffCount++;
                System.out.printf("%s %d, %s\n", person.type, staffCount, person.dept);
            }

            person.writeOutput();
        }
    }

    private static void sortContacts(List<Person> contacts, String criteria) {
        switch (criteria) {
            case "name":
                contacts.sort(Comparator.comparing(Person::getName).thenComparing(Person::getDept));
                break;
            case "dept":
                contacts.sort(Comparator.comparing(Person::getDept));
                break;
            default:
                System.out.println("Invalid sort criteria.");
        }
    }

    private static void saveToTextFile(List<Person> contacts, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Person person : contacts) {
                writer.write(person.type + ", " + person.dept + ", " + person.name + ", " + person.id + ", " + person.email);
                writer.newLine();
            }
            System.out.println("Contacts saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static List<Person> loadFromTextFile(String filename) {
        List<Person> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                addContact(contacts, parts);
            }
            System.out.println("Contacts loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return contacts;
    }

    private static void saveToBinaryFile(List<Person> contacts, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(contacts);
            System.out.println("Contacts saved to binary file " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to binary file: " + e.getMessage());
        }
    }

    private static List<Person> loadFromBinaryFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            List<Person> contacts = (List<Person>) in.readObject();
            System.out.println("Contacts loaded from binary file " + filename);
            return contacts;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from binary file: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
