

public class Main {

    public static void main(String[] args) {
//        ProductWarehouseWithHistory juice = new ProductWarehouseWithHistory("Juice", 1000.0, 1000.0);
//
//        juice.takeFromWarehouse(11.3);
//        juice.addToWarehouse(1.0);
//
//        juice.printAnalysis();

        ProductWarehouseWithHistory coffee = new ProductWarehouseWithHistory("coffee", 10, 2);

        coffee.addToWarehouse(4);
        coffee.takeFromWarehouse(2);
        coffee.printAnalysis();
    }
}