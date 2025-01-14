import java.util.ArrayList;

public class Box implements Packable{
    private ArrayList<Packable> list = new ArrayList<>();
    private double maxWeight;

    public Box(double maxWeight){
        this.maxWeight = maxWeight;
    }
    public void add(Packable item) {
        if(!(item.weight() > this.maxWeight)){
            double totalWeight = 0;
            for(Packable i : this.list){
                totalWeight += i.weight();
            }
            if(totalWeight + item.weight() <= this.maxWeight){
                this.list.add(item);
            }
        }
    }
    public double weight(){
        double total = 0;
        for(Packable i : this.list){
            total += i.weight();
        }
        return total;
    }
    public String toString(){
        return "Box: " + this.list.size() + " items, total weight " + weight() + " kg";
    }
}
