import java.util.ArrayList;

public class Store {
    private ArrayList<Product> products;

    public Store(){
        this.products = new ArrayList<>();
    }
    public void addProduct(Product product){
        this.products.add(product);
    }
    public void listProducts(){
        for(Product i : this.products){
            double finalPrice = i.calculateFinalPrice();
            System.out.println(i.getName() + ":");
            System.out.println(" Final price (EUR): " + finalPrice);
            System.out.println(" Final price (CHF): " + CurrencyConverter.convertToCHF(finalPrice));
        }
    }
}
