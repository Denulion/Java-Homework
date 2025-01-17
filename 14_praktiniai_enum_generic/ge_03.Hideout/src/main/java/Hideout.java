import java.util.ArrayList;

public class Hideout<T> {
    private ArrayList<T> hideSpot;

    public Hideout(){
        this.hideSpot = new ArrayList<>();
    }
    public void putIntoHideout(T toHide){
        this.hideSpot.add(toHide);
    }
    public T takeFromHideout(){
        if (this.hideSpot.isEmpty()) return null;
        T temp = this.hideSpot.get(0);
        this.hideSpot.clear();
        return temp;
    }
    public boolean isInHideout(){
        return !this.hideSpot.isEmpty();
    }
}
