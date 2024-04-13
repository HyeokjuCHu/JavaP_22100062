import java.util.Scanner;

class Circle {
    private double radius;
    private double area;
    private double circumference;
    private final double PI = 3.14;

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void calculateArea() {
        this.area = PI * radius * radius;
    }

    public void calculateCircumference() {
        this.circumference = 2 * PI * radius;
    }

    public double getArea() {
        return this.area;
    }

    public double getCircumference() {
        return this.circumference;
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Circle circle1 = new Circle();
        Circle circle2 = new Circle();

        System.out.print("Enter the first radius: ");
        double radius1 = scanner.nextDouble();
        System.out.print("Enter the second radius: ");
        double radius2 = scanner.nextDouble();

        circle1.setRadius(radius1);
        circle2.setRadius(radius2);

        circle1.calculateArea();
        circle1.calculateCircumference();
        circle2.calculateArea();
        circle2.calculateCircumference();

        double ratioOfAreas = circle1.getArea() / circle2.getArea();

        System.out.printf("First circle: The area is %.2f and the circumference is %.2f.\n", circle1.getArea(), circle1.getCircumference());
        System.out.printf("Second circle: The area is %.2f and the circumference is %.2f.\n", circle2.getArea(), circle2.getCircumference());
        System.out.printf("The ratio of the areas is %.2f.\n", ratioOfAreas);
    }
}
