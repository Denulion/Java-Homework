import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<Shape> test1 = new ArrayList<>();

        Rectangle rectangleTest = new Rectangle(10,12, "blue", true);
        Triangle triangleTest = new Triangle(10,10,16, "blue", false);
        Circle circleTest = new Circle(4, "black", false);
        Square squareTest = new Square(12, "pink", true);

        test1.add(rectangleTest);
        test1.add(triangleTest);
        test1.add(circleTest);
        test1.add(squareTest);

        double totalArea = 0;
        double biggestPerimeter = 0;
        for(Shape shape : test1){
            System.out.println(shape);
            totalArea += shape.getArea();

            if(biggestPerimeter < shape.getPerimeter()){
                biggestPerimeter = shape.getPerimeter();
            }
        }
        System.out.println(totalArea);
        System.out.println(biggestPerimeter);
    }
}
