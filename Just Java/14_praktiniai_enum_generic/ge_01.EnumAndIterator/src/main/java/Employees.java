import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Employees {
    private ArrayList<Person> employeeList;

    public Employees(){
        this.employeeList = new ArrayList<>();
    }
//    public Employees(ArrayList<Person> employeeList){
//        this.employeeList = employeeList;
//    }
    public void add(Person personToAdd){
        this.employeeList.add(personToAdd);
    }
    public void add(List<Person> peopleToAdd){
        peopleToAdd.forEach(this::add);
//        peopleToAdd.forEach(i -> this.add(i));
    }
    public void print(){
        this.employeeList.forEach(System.out::println);
    }
    public void print(Education education){
//        this.employeeList.stream().filter(i -> i.getEducation() == education).forEach(System.out::println)
        Iterator<Person> iter = this.employeeList.iterator();
        while(iter.hasNext()) {
            Person temp = iter.next();
            if (temp.getEducation().equals(education)){
                System.out.println(temp);
            }
        }
    }
    public void fire(Education education){
        Iterator<Person> iter = this.employeeList.iterator();
        while (iter.hasNext()){
            Person temp = iter.next();
            if(temp.getEducation().equals(education)){
                iter.remove();
            }
        }
    }
}
