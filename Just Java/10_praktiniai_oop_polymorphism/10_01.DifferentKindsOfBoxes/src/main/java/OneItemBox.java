import java.util.ArrayList;

public class OneItemBox extends Box{
    private ArrayList<Item> theBox = new ArrayList<>();

    public OneItemBox(){

    }

    @Override
    public void add(Item item) {
        if(this.theBox.isEmpty()) this.theBox.add(item);
    }

    @Override
    public boolean isInBox(Item item) {
        return this.theBox.contains(item);
    }
}
