import java.util.ArrayList;

public class BoxWithMaxWeight extends Box{
    private final int capacity;
    private ArrayList<Item> theBox = new ArrayList<>();

    public BoxWithMaxWeight(int capacity){
        this.capacity = capacity;
    }

    @Override
    public void add(Item item) {
        if(!(item.getWeight() > this.capacity)){
            int totalWeight = 0;
            for(Item i : theBox){
                totalWeight += i.getWeight();
            }
            if(totalWeight + item.getWeight() <= this.capacity){
                this.theBox.add(item);
            }
        }
    }

    @Override
    public boolean isInBox(Item item) {
        return this.theBox.contains(item);
    }
    public void getAllItems(){
        for(Item i : this.theBox){
            System.out.println(i.getName());
        }
    }
}
