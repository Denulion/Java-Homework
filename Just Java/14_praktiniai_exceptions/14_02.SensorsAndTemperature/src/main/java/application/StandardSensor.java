package application;

public class StandardSensor implements Sensor{
    private final boolean isOn;
    private int arg;

    public StandardSensor (int arg){
        this.arg = arg;
        this.isOn = true;
    }

    @Override
    public boolean isOn() {
        return this.isOn;
    }

    @Override
    public void setOn() {

    }

    @Override
    public void setOff() {

    }

    @Override
    public int read() {
        return this.arg;
    }
}
