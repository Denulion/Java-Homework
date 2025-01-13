public class ProductWarehouseWithHistory extends ProductWarehouse{
    private final ChangeHistory history;

    public ProductWarehouseWithHistory(String productName, double capacity, double initialBalance){
        super(productName, capacity);
        this.history = new ChangeHistory();
        super.addToWarehouse(initialBalance);
        this.history.add(getBalance());
    }
    @Override
    public void addToWarehouse(double amount) {
        super.addToWarehouse(amount);
        this.history.add(getBalance());
    }
    @Override
    public double takeFromWarehouse(double amount) {
        double temp = super.takeFromWarehouse(amount);
        this.history.add(getBalance());
        return temp;
    }
    public String history() {
        return this.history.toString();
    }
    public void printAnalysis(){
        System.out.println("Product: " + getName());
        System.out.println("History: " + history);
        System.out.println("Largest amount of product: " + history.maxValue());
        System.out.println("Smallest amount of product: " + history.minValue());
        System.out.println("Average: " + history.average());
    }
}

