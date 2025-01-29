public class Teacher extends Person{
    private int salary = 100;

    public Teacher(){

    }
    public Teacher(int salary){
        this.salary = salary;
    }
    public Teacher(String fullName, String address, int salary){
        super(fullName, address);
        this.salary = salary;
    }
    public int getSalary(){
        return this.salary;
    }
    public String toString(){
        return super.toString() + "\n  salary "+ this.salary + " euro/month";
    }
}
