public class CircleSimplified {
    private double radius;

    public CircleSimplified(){
        radius = 1.0;
    }
    public CircleSimplified(double radius){
        this.radius = radius;
    }
    public double getRadius(){
        return radius;
    }
    public void setRadius(double radius){
        this.radius = radius;
    }
    public double getArea(){
        return radius * radius * Math.PI;
    }
    public double getCircumference(){
        return radius * 2 * Math.PI;
    }
    public String toString(){
        return "Circle[radius = " + radius + "]";
    }
}
