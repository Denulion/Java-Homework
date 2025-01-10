public class Gauge {
    private int value;

    public Gauge(){
        value = 0;
    }
    public void setValue(int value){
        this.value = value;
    }
    public void increase(){
        setValue(value + 1);
        if(value > 5) setValue(5);
    }
    public void decrease(){
        setValue(value - 1);
        if(value < 0) setValue(0);
    }
    public int value(){
        return value;
    }
    public boolean full(){
        return value == 5;
    }
}
