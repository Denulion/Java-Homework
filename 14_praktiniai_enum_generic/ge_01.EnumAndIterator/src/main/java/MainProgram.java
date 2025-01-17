
public class MainProgram {

    public static void main(String[] args) {
        Employees t = new Employees();
        Person h = new Person("Arto", Education.PHD);
        t.add(h);
        Person w = new Person("Pekka", Education.BA);
        t.add(w);
        Person z = new Person("Matti", Education.PHD);
        t.add(z);
        t.fire(Education.PHD);
        t.print();
    }
}
