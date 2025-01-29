public class Circle extends Shape{
    private double radius;

    public Circle() {
        this.radius = 1.0;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    @Override
    public double getArea() {
        return (this.radius * this.radius) * Math.PI;
    }

    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }
    @Override
    public String toString(){
        return "A circle with radius of " + this.radius + ", which is a subclass of ????????????";
    }
}
