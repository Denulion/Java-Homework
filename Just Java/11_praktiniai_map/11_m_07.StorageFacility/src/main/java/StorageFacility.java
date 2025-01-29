import java.util.ArrayList;
import java.util.HashMap;

public class StorageFacility {
    private HashMap<String, ArrayList<String>> storageList;

    public StorageFacility(){
        this.storageList = new HashMap<>();
    }
    public StorageFacility(HashMap<String, ArrayList<String>> storageList){
        this.storageList = storageList;
    }
    public void add(String unit, String item){
        ArrayList<String> temp = this.storageList.getOrDefault(unit, new ArrayList<>());
        temp.add(item);
        this.storageList.put(unit, temp);
    }
    public ArrayList<String> contents(String storageUnit){
        if (!this.storageList.containsKey(storageUnit)) return new ArrayList<>();
        return this.storageList.get(storageUnit);
    }
    public void remove(String unit, String item){
        if (this.storageList.containsKey(unit)){
            ArrayList<String> temp = this.storageList.get(unit);

            if(temp != null){
                temp.remove(item);

                if(temp.isEmpty()){
                    this.storageList.remove(unit);
                }
            }
        }
        //My version of code is commented, after completion I used chatGPT version to have it as example for the future
//        this.storageList.get(unit).remove(item);
//        if (this.storageList.get(unit).equals(new ArrayList<>())){
//            this.storageList.remove(unit);
//        }
    }
    public ArrayList<String> storageUnits(){
        ArrayList<String> temp = new ArrayList<>();
        for(String i : this.storageList.keySet()){
            if(!this.storageList.get(i).isEmpty()){
                temp.add(i);
            }
        }
        return temp;
    }
}
