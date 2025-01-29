public class Person {
    private String fullName;
    private String address;

    public Person(){
        this.fullName = "John Smith";
        this.address = "Breaking Bad st. 1";
    }
    public Person(String fullName, String address) {
        this.address = address;
        this.fullName = fullName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return this.fullName + "\n  " + this.address;
    }
}
