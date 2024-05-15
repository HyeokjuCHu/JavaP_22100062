import java.util.Scanner;

// Shape interface
interface Shape {
    String getName();
    double getArea();
    double getPerimeter();
    void printShape();
}

// Rectangle class implementing Shape
class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String getName() {
        return "Rectangle";
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void printShape() {
        System.out.println("Name: " + getName());
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }
}

// Triangle class implementing Shape
class Triangle implements Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public String getName() {
        return "Triangle";
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

    @Override
    public double getPerimeter() {
        // Assuming it's a right triangle, using Pythagoras theorem to calculate hypotenuse
        double hypotenuse = Math.sqrt(base * base + height * height);
        return base + height + hypotenuse;
    }

    @Override
    public void printShape() {
        System.out.println("Name: " + getName());
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }
}

// Circle class implementing Shape
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "Circle";
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void printShape() {
        System.out.println("Name: " + getName());
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shape[] shapes = new Shape[5];

        for (int i = 0; i < shapes.length; i++) {
            System.out.println("Enter shape type (Rectangle, Triangle, or Circle):");
            String shapeType = scanner.nextLine();

            switch (shapeType.toLowerCase()) {
                case "rectangle":
                    System.out.println("Enter width:");
                    double rectWidth = Double.parseDouble(scanner.nextLine());
                    System.out.println("Enter height:");
                    double rectHeight = Double.parseDouble(scanner.nextLine());
                    shapes[i] = new Rectangle(rectWidth, rectHeight);
                    break;
                case "triangle":
                    System.out.println("Enter base length:");
                    double triBase = Double.parseDouble(scanner.nextLine());
                    System.out.println("Enter height:");
                    double triHeight = Double.parseDouble(scanner.nextLine());
                    shapes[i] = new Triangle(triBase, triHeight);
                    break;
                case "circle":
                    System.out.println("Enter radius:");
                    double circleRadius = Double.parseDouble(scanner.nextLine());
                    shapes[i] = new Circle(circleRadius);
                    break;
                default:
                    System.out.println("Invalid shape type. Please enter Rectangle, Triangle, or Circle.");
                    i--; // To repeat the iteration for valid input
            }
        }

        // Print details of all shapes
        for (Shape shape : shapes) {
            shape.printShape();
            System.out.println(); // Add newline for better readability
        }
    }
}
