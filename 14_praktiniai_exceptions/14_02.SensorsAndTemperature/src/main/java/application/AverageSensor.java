package application;

import java.util.ArrayList;
import java.util.List;

public class AverageSensor implements Sensor{
    private ArrayList<Sensor> sensorList;
    private List<Integer> readList = new ArrayList<>();

    public AverageSensor(){
        this.sensorList = new ArrayList<>();
    }

    @Override
    public boolean isOn() {
        return this.sensorList.stream().allMatch(Sensor::isOn);
    }

    @Override
    public void setOn() {
        this.sensorList.forEach(Sensor::setOn);
    }

    @Override
    public void setOff() {
        this.sensorList.forEach(Sensor::setOff);
    }

    @Override
    public int read() {
        if (this.sensorList.isEmpty() || !isOn()) {
            throw new IllegalStateException("Average sensor read error");
        }
        int total = 0;
        for (Sensor sensor : this.sensorList) {
            total += sensor.read();
        }
        readList.add(total / this.sensorList.size());
        return total / this.sensorList.size();
    }
    public void addSensor(Sensor toAdd) {
        if (toAdd == null){
            throw new IllegalArgumentException("Error is addSensor - arg is null");
        }
        this.sensorList.add(toAdd);
    }
    public List<Integer> readings() {
        return this.readList;
    }
}
