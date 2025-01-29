import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hand implements Comparable<Hand>{
    private ArrayList<Card> cardHand;

    public Hand(){
        this.cardHand = new ArrayList<>();
    }
    public Hand(ArrayList<Card> cardHand){
        this.cardHand = cardHand;
    }
    public void add(Card card){
        this.cardHand.add(card);
    }
    public void print(){
        this.cardHand.forEach(System.out::println);
    }
    public void sort(){
        Collections.sort(this.cardHand);
        //this.cardHand.sort(null);
    }

    @Override
    public int compareTo(Hand other) {
        int sumThisHand = this.cardHand.stream().mapToInt(Card::getValue).sum();
        int sumOtherHand = other.cardHand.stream().mapToInt(Card::getValue).sum();

        return Integer.compare(sumThisHand, sumOtherHand);
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SVARBU!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void sortBySuit(){
        this.cardHand.sort(Comparator.comparingInt((Card a) -> a.getSuit().ordinal()).thenComparingInt(Card::getValue));
    }
}
