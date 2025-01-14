import java.util.ArrayList;

public class Herd implements Movable {
    private ArrayList<Movable> herd = new ArrayList<>();

    public Herd() {
    }
    @Override
    public void move(int dx, int dy) {
        for(Movable i : this.herd){
            i.move(dx, dy);
        }
    }
    public void addToHerd(Movable movable){
        this.herd.add(movable);
    }
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (Movable i : this.herd) {
            returnString.append(i.toString()).append("\n");
        }
        return returnString.toString();
    }
}
