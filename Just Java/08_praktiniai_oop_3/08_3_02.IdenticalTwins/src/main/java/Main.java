
public class Main {

    public static void main(String[] args) {
        // you can write code here to try out your program

        SimpleDate date = new SimpleDate(24, 3, 2017);
        SimpleDate date2 = new SimpleDate(23, 7, 2017);

        Person leo = new Person("Leo", date, 62, 9);
        Person lily = new Person("Leo", date, 62, 9);

        if (leo.equals(lily)) {
            System.out.println("Is this quite correct?");
        }else System.out.println("NO");

        Person leoWithDifferentWeight = new Person("Leo", date, 62, 10);

        if (leo.equals(leoWithDifferentWeight)) {
            System.out.println("Is this quite correct?");
        }
        Person first = new Person("Leevi", new SimpleDate(1, 1, 2017), 70, 10);
        Person second = new Person("Leevi", new SimpleDate(1, 1, 2017), 70, 10);
        System.out.println(first.equals(second));
    }
}
