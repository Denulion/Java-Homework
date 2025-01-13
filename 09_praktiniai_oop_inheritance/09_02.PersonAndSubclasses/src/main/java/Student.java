public class Student extends Person {
    private int credits = 0;

    public Student(){

    }
    public Student(int credits){
        this.credits = credits;
    }
    public Student(String fullName, String address){
        super(fullName, address);
    }
    public int credits() {
        return this.credits;
    }
    public void study(){
        this.credits += 1;
    }
    public String toString(){
        return super.toString() + "\n  Study credits " + this.credits;
    }
}
