import exceptions.*;
import java.util.HashMap;
import java.util.Map;

public class CashRegister {
    private Map<Double, Integer> coins;

    public CashRegister() {
        this.coins = new HashMap<>();
    }

    public void addCash(double amount, int count) throws PayNotAcceptedException {
        if (this.coins.containsKey(amount)) {
            this.coins.put(amount, this.coins.get(amount) + count);
        } else {
            throw new PayNotAcceptedException("Invalid currency value!");
        }
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
}
