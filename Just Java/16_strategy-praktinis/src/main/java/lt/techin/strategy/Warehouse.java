package lt.techin.strategy;

import java.util.List;

public interface Warehouse {


     void addProducts(List<Product> products);

     int getNumberOfProducts();

    List<Product> executeFilteringStrategy(FilteringStrategy strategy);



}
