import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

abstract class Person {
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
            String input = scanner.nextLine();
            String[] parts = input.split(", ");

            if (parts[0].equals("print")) {
                printContacts(contacts);
            } else if (parts[0].equals("sort")) {
                if (parts[1].equals("name")) {
                    Collections.sort(contacts, Comparator.comparing(Person::getName)
                            .thenComparing(Person::getDept));
                } else if (parts[1].equals("dept")) {
                    Collections.sort(contacts, Comparator.comparing(Person::getDept));
                }
            } else {
                String type = parts[0];
                String dept = parts.length > 1 ? parts[1] : "Not Yet Defined";
                String name = parts.length > 2 ? parts[2] : "Not Yet Defined";
                String id = parts.length > 3 ? parts[3] : "Not Yet Defined";
                String email = parts.length > 4 ? parts[4] : "Not Yet Defined";

                if (type.equals("Under")) {
                    int studentNumber = Integer.parseInt(id);
                    contacts.add(new Under(type, dept, name, id, email, studentNumber));
                } else if (type.equals("Grad")) {
                    int studentNumber = Integer.parseInt(id);
                    contacts.add(new Grad(type, dept, name, id, email, studentNumber));
                } else if (type.equals("Faculty")) {
                    String facultyId = id;
                    contacts.add(new Faculty(type, dept, name, id, email, facultyId));
                } else if (type.equals("Staff")) {
                    contacts.add(new Staff(type, dept, name, id, email));
                }
            }
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
                System.out.printf("%s %d, %s", person.type, underCount, person.dept);
            } else if (person instanceof Grad) {
                gradCount++;
                System.out.printf("%s %d, %s", person.type, gradCount, person.dept);
            } else if (person instanceof Faculty) {
                facultyCount++;
                System.out.printf("%s %d, %s", person.type, facultyCount, person.dept);
            } else if (person instanceof Staff) {
                staffCount++;
                System.out.printf("%s %d, %s", person.type, staffCount, person.dept);
            }

            person.writeOutput();
        }
    }
}
