import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your test code here
        ArrayList<Person> list = new ArrayList<>();

        Teacher ada = new Teacher("Ada", "Gedimino 2g", 1200);
        Teacher ted = new Teacher("Ted", "Fabijoniskiu g. 7", 5600);
        System.out.println(ada);
        System.out.println(ted);

        Student ollie = new Student("Ollie", "Arroyo 15");
        System.out.println(ollie);
        int i = 0;
        while (i < 25){
            ollie.study();
            i++;
        }
        System.out.println(ollie);

        list.add(ada);
        list.add(ted);
        list.add(ollie);
        printPersons(list);
    }
    public static void printPersons(ArrayList<Person> persons){
        for(Person person : persons){
            System.out.println(person);
        }
    }
}
