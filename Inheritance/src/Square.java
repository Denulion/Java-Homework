public class Square extends Rectangle{

    public Square(){}
    public Square(double side){
        super(side, side);
    }
    public Square(double side, String color, boolean filled){
        super(side, side, color, filled);
    }
    public double getSide(){
        return this.getLength();
    }
    public void setSide(double side){
        setWidth(side);
        setLength(side);
    }
    @Override
    public void setWidth(double side){
        setSide(side);
    }
    @Override
    public void setLength(double side){
        setSide(side);
    }
//    @Override
//    public double getArea(){
//        return this.getWidth() * this.getLength();
//    }
    @Override
    public String toString(){
        return "A square with width and length of " + this.getSide() + ", which is a subclass of ????????????";
    }
}
