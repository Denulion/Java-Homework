import java.util.ArrayList;

public class MisplacingBox extends Box{
    private ArrayList<Item> theBox = new ArrayList<>();

    public MisplacingBox(){}

    @Override
    public void add(Item item) {
        this.theBox.add(item);
        this.theBox.clear();
    }

    @Override
    public boolean isInBox(Item item) {
        return this.theBox.contains(item);
    }
}
