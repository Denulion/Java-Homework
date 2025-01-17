import java.util.Comparator;

public class BySuitInValueOrder implements Comparator<Card> {
    public int compare(Card c1, Card c2){
        int comparison = Integer.compare(c1.getSuit().ordinal(), c2.getSuit().ordinal());
        if(comparison != 0){
            return comparison;
        }
        return Integer.compare(c1.getValue(), c2.getValue());
    }
}
