import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryOfManyTranslations {
    private HashMap<String, ArrayList<String>> transtalions;

    public DictionaryOfManyTranslations(){
        this.transtalions = new HashMap<>();
    }
    public DictionaryOfManyTranslations(HashMap<String, ArrayList<String>> transtalions){
        this.transtalions = transtalions;
    }
    public void add(String word, String translation){
        ArrayList<String> temp = this.transtalions.getOrDefault(word, new ArrayList<>());
        temp.add(translation);
        this.transtalions.put(word, temp);
    }
    public ArrayList<String> translate(String word){
        if (!this.transtalions.containsKey(word)) return new ArrayList<>();
        return this.transtalions.get(word);
    }
    public void remove(String word){
        this.transtalions.remove(word);
    }
}
