import java.util.ArrayList;

public class Stack {
    private ArrayList<String> temp;

    public Stack(){
        this.temp = new ArrayList<>();
    }
    public boolean isEmpty(){
        return this.temp.isEmpty();
    }
    public void add(String value){
        this.temp.add(value);
    }
    public ArrayList<String> values(){
        return this.temp;
    }
    public String take(){
        if(this.temp.isEmpty()){
            return null;
        }
        return this.temp.remove(this.temp.size() - 1);
    }
}
