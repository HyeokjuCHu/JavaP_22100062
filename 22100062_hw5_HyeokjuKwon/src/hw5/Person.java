package hw5;

public class Person {
    private String name;
    private int age;

    // Default constructor
    public Person() {
        this.name = "No name yet";
        this.age = 0;
    }

    // Constructor with specified name and age
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Accessor method for name
    public String getName() {
        return this.name;
    }

    // Accessor method for age
    public int getAge() {
        return this.age;
    }

    // Mutator method to set name given first and last name
    public void setName(String first, String last) {
        this.name = first + " " + last;
    }

    // Mutator method to set name given full name
    public void setName(String name) {
        this.name = name;
    }

    // Mutator method to set age
    public void setAge(int age) {
        this.age = age;
    }

    // Static method to create a generic adult instance
    public static Person createAdult() {
        return new Person("An adult", 21);
    }

    // Static method to create a toddler instance
    public static Person createToddler() {
        return new Person("A toddler", 2);
    }

    // Static method to create a preschooler instance
    public static Person createPreschooler() {
        return new Person("A preschooler", 5);
    }

    // Static method to create an adolescent instance
    public static Person createAdolescent() {
        return new Person("An adolescent", 9);
    }

    // Static method to create a teenager instance
    public static Person createTeenager() {
        return new Person("A teenager", 15);
    }
}

