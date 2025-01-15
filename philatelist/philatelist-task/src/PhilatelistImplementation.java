import lt.techin.philatelist.Philatelist;
import lt.techin.philatelist.PostStamp;

import java.util.*;

public class PhilatelistImplementation implements Philatelist {
    protected ArrayList<PostStamp> stamps = new ArrayList<>();
    @Override
    public void addToCollection(PostStamp postStamp) {
        if( postStamp == null || postStamp.getName() == null || postStamp.getName().isEmpty()) {
            throw new IllegalArgumentException("Null given");
        }else {
            this.stamps.add(postStamp);
        }
    }

    @Override
    public int getNumberOfPostStampsInCollection() {
        return this.stamps.size();
    }

    @Override
    public void printAllPostStampNames() {
        for(PostStamp i : this.stamps){
            System.out.println(i.getName());
        }
    }

    @Override
    public void printPostStampsWithPriceGreaterThan(double v) {
        for(PostStamp i : this.stamps){
            if(v < i.getMarketPrice()){
                System.out.println(i.getName());
            }
        }
    }

    @Override
    public boolean isPostStampInCollection(PostStamp postStamp) {
        return this.stamps.contains(postStamp);
    }

    @Override
    public boolean isPostStampWithNameInCollection(String s) {
        for(PostStamp i : this.stamps){
            return Objects.equals(i.getName(), s);
        }
        return false;
    }

    @Override
    public double calculateTotalMarketPrice() {
        double totalMarketPrice = 0;
        for(PostStamp i : this.stamps){
            totalMarketPrice += i.getMarketPrice();
        }
        return totalMarketPrice;
    }

    @Override
    public double getAveragePostStampPrice() {
        return calculateTotalMarketPrice() / this.stamps.size();
    }

    @Override
    public PostStamp getTheMostExpensivePostStampByMarketValue() {
        PostStamp theMostExpensive = this.stamps.getFirst();
        for(PostStamp i : this.stamps){
            if(i.getMarketPrice() > theMostExpensive.getMarketPrice()){
                theMostExpensive = i;
            }
        }
        return theMostExpensive;
    }

    @Override
    public List<PostStamp> findPostStampsByNameContaining(String s) {
        if(s == null || s.isEmpty()){
            return List.of();
        }
        String stringToLowerCase = s.toLowerCase();
        List<PostStamp> result = new ArrayList<>();

        for(PostStamp i : this.stamps){
            if(i.getName().toLowerCase().contains(stringToLowerCase)){
                result.add(i);
            }
        }
        return result;
    }

    @Override
    public List<PostStamp> getSortedPostStampsByName() {
        List<PostStamp> sortedStamps = new ArrayList<>(this.stamps);
        sortedStamps.sort(new Comparator<PostStamp>() {
            @Override
            public int compare(PostStamp p1, PostStamp p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });

        return sortedStamps;
    }
}
