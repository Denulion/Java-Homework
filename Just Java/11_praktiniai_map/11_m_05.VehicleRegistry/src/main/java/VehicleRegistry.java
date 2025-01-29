import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VehicleRegistry {
    private HashMap<LicensePlate, String> licensePlateStringHashMap;

    public VehicleRegistry(){
        this.licensePlateStringHashMap = new HashMap<>();
    }
    public VehicleRegistry(HashMap<LicensePlate, String> licenseHash){
        this.licensePlateStringHashMap = licenseHash;
    }
    public boolean add(LicensePlate licensePlate, String owner){
        if(licensePlateStringHashMap.containsKey(licensePlate)){
            return false;
        }else{
            this.licensePlateStringHashMap.put(licensePlate, owner);
            return true;
        }
    }
    public String get(LicensePlate licensePlate){
        if(!this.licensePlateStringHashMap.containsKey(licensePlate)) return null;
        return this.licensePlateStringHashMap.get(licensePlate);
    }
    public boolean remove(LicensePlate licensePlate){
        if(!this.licensePlateStringHashMap.containsKey(licensePlate)) return false;
        this.licensePlateStringHashMap.remove(licensePlate);
        return true;
    }
    public void printOwners(){
        List<String> names = new ArrayList<>();
        for(String name : this.licensePlateStringHashMap.values()){
            if(!names.contains(name)){
                System.out.println(name);
                names.add(name);
            }
        }
    }
    public void printLicensePlates(){
        for(LicensePlate key : this.licensePlateStringHashMap.keySet()){
            System.out.println(key);
        }
    }
}
