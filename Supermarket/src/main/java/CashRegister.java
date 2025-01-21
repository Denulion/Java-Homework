import exceptions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CashRegister {
    private Map<Double, Integer> coins;

    public CashRegister() {
        this.coins = new HashMap<>();
    }

    public void addCash(double value, int count) {
        if (this.coins.containsKey(value)) {
            this.coins.put(value, this.coins.get(value) + count);
        } else {
            this.coins.put(value, count);
        }
    }

    public Map<Double, Integer> getCoins() {
        return this.coins;
    }

    public Map<Double, Integer> change(double change) throws NotEnoughChangeException {
        Map<Double, Integer> changeToReturn = new HashMap<>();
        for (double i : this.coins.keySet()) {
            while (change >= i && this.coins.get(i) > 0) {
                change -= i;
                change = Math.round(change * 100.0) / 100.0;
                changeToReturn.put(i, changeToReturn.getOrDefault(i, 0) + 1);
                this.coins.put(i, this.coins.get(i) - 1);
            }
        }

        if (change > 0) {
            throw new NotEnoughChangeException("Cannot provide exact change!");
        }

        return changeToReturn;
    }

    public void displayCash() {
        this.coins.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(i -> System.out.println("Value: " + i.getKey() + ", quantity: " + i.getValue()));
    }

    public String printValues() {
        return this.coins.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(i -> {
                    String[] parts = i.toString().split("=");
                    return parts[0];
                })
                .collect(Collectors.joining(", "));
    }
}
