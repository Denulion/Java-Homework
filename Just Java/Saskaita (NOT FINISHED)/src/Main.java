public class Main {
    public static void main(String[] args) {
        Saskaita debetineSaskaita = new Saskaita("LT123456", "Иван Иванов", 100);
        debetineSaskaita.addBalance(50);
        debetineSaskaita.takeBalance(120);
        System.out.println(debetineSaskaita);

        KreditineSaskaita kreditineSaskaita = new KreditineSaskaita("LT789012", "Петр Петров", 200, 500);
        kreditineSaskaita.takeBalance(300);
        kreditineSaskaita.takeBalance(500);
        System.out.println(kreditineSaskaita);
    }
}