
public class DecreasingCounter {

    private int value;  // an object variable for storing the value of the counter

    public DecreasingCounter(int initialValue) {
        this.value = initialValue;
    }

    public void printValue() {
        // Do not change this code!
        System.out.println("value: " + this.value);
    }

    public void decrement() {
        setValue(value - 1);
        if (value < 0) setValue(0);
    }

    public void setValue(int value){
        this.value = value;
    }
    public void reset(){
        setValue(0);
    }
    // the other methods go here
}
