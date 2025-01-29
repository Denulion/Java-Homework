package lt.techin.strategy;

import java.util.ArrayList;
import java.util.List;

public class WarehouseTest implements Warehouse {
    List<Product> tempList = new ArrayList<>();
    @Override
    public void addProducts(List<Product> products) {
        tempList.addAll(products);
    }

    @Override
    public int getNumberOfProducts() {
        return tempList.size();
    }

    @Override
    public List<Product> executeFilteringStrategy(FilteringStrategy strategy) {
        List<Product> filtered = new ArrayList<>();
        for (Product product : tempList) {
            if (strategy.filter(product)) {
                filtered.add(product);
            }
        }
        return filtered;
    }
}
