import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        double input = Double.parseDouble(scanner.nextLine());
//        String inputText = scanner.nextLine();
//        Circle test = new Circle(input, inputText);
//
//        System.out.println("Your radius is: " + test.getRadius() + "and area: " + test.getArea() + " " + test.getColor());

        Circle c4 = new Circle();
        c4.setRadius(5.0);
        System.out.println("Radius is " + c4.getRadius());
        c4.setColor("Black");
        System.out.println("Color is " + c4.getColor());

        Circle c2 = new Circle(1.2);
        System.out.println(c2.toString());
        System.out.println(c2);
        System.out.println("Operator '+' invokes toString() too: " + c2);

//        System.out.println(test.toString());
    }
}
