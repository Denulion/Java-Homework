import java.util.ArrayList;

public class ChangeHistory{
    private ArrayList<Double> history;

    public ChangeHistory(){
    }
    public void add(double status){
        this.history.add(status);
    }
    public void clear(){
        history.clear();
    }
    public double maxValue(){

    }
    public String toString(){
        return history.toString();
    }
}
