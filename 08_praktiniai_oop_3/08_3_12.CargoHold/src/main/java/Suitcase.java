import java.util.ArrayList;

public class Suitcase {
    private ArrayList<Item> items = new ArrayList<>();
    private int maxWeight;

    public Suitcase(int maxWeight){
        this.maxWeight = maxWeight;
    }
    public int totalWeight() {
        int totalWeight = 0;
        for (Item item : this.items) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }
    public void addItem(Item item){
        if (totalWeight() + item.getWeight() <= maxWeight) {
            this.items.add(item);
        }
    }
    public void printItems(){
        for(Item item : this.items){
            System.out.println(item);
        }
    }
    public Item heaviestItem() {
        if (this.items.isEmpty()) return null;
        Item heaviest = this.items.get(0);
        for (Item item : this.items) {
            if (item.getWeight() > heaviest.getWeight()) {
                heaviest = item;
            }
        }
        return heaviest;
    }
    public String toString() {
        if (this.items.isEmpty()) {
            return "no items (0 kg)";
        } else if (this.items.size() == 1) {
            return "1 item (" + totalWeight() + " kg)";
        } else {
            return this.items.size() + " items (" + totalWeight() + " kg)";
        }
    }
}
