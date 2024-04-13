package hw5;

public class Main {
    public static void main(String[] args) {
        // Creating a person with default values
        Person person1 = new Person();
        System.out.println("Person 1: " + person1.getName() + ", Age: " + person1.getAge());

        // Creating a person with specified values
        Person person2 = new Person("John Doe", 30);
        System.out.println("Person 2: " + person2.getName() + ", Age: " + person2.getAge());

        // Setting name using first and last name
        person1.setName("Jane", "Doe");
        System.out.println("Person 1 (updated name): " + person1.getName());

        // Setting age
        person2.setAge(35);
        System.out.println("Person 2 (updated age): " + person2.getAge());

        // Creating specific instances using static methods
        Person adult = Person.createAdult();
        System.out.println("Adult: " + adult.getName() + ", Age: " + adult.getAge());

        Person toddler = Person.createToddler();
        System.out.println("Toddler: " + toddler.getName() + ", Age: " + toddler.getAge());

        Person adolescent = Person.createAdolescent();
        System.out.println("Adolescent: " + adolescent.getName() + ", Age: " + adolescent.getAge());
    }
}

