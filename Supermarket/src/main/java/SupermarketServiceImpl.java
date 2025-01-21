import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import exceptions.*;

public class SupermarketServiceImpl implements SupermarketService {
    public Scanner scanner = new Scanner(System.in);
    public ProductStorage storage = new ProductStorage();
    public CashRegister cashRegister = new CashRegister();

    public void greetings() throws SoldOutException, NotEnoughChangeException, PayNotAcceptedException {
        System.out.println("Welcome to TECHIN supermarket!");
        while (true) {
            System.out.println("Please select initial state of the supermarket! Type 'Ready' or 'New'");
            String input = scanner.nextLine();
            switch (input) {
                case "Ready" -> readyInitialization();
                case "New" -> System.out.println("new");
                case "Quit" -> {
                    System.out.println("Bye");
                    return;
                }
                default -> System.out.println("No such option, try again!");
            }
        }
    }

    public void console() throws SoldOutException, NotEnoughChangeException, PayNotAcceptedException {
        while (true) {
            displayInitialInventory();

            System.out.println("What would you like to buy? Type in the name of desired product: ");
            String input = scanner.nextLine();

            if (input == null || input.isEmpty()) {
                System.out.println("Input is empty, try again");
            } else if (storage.hasProduct(input)) {
                processPurchase(input);
            } else if (input.equalsIgnoreCase("Quit")) {
                return;
            } else {
                System.out.println("Error! Product not available!");
            }
        }
    }

    public void readyInitialization() throws SoldOutException, NotEnoughChangeException, PayNotAcceptedException {
        storage.addProduct("APPLE", new Product("APPLE", 1.0, 20));
        storage.addProduct("BREAD", new Product("BREAD", 2.5, 5));
        storage.addProduct("MILK", new Product("MILK", 1.5, 10));

        cashRegister.addCash(0.1, 50);
        cashRegister.addCash(0.5, 20);
        cashRegister.addCash(1.0, 10);
        cashRegister.addCash(2.0, 10);

        console();
    }

    private void processPurchase(String productName) throws SoldOutException, NotEnoughChangeException, PayNotAcceptedException {
        System.out.println("You are trying to buy " + storage.getProduct(productName).getName()
                + ". You need to pay " + storage.getProduct(productName).getPrice());
        double needToPay = storage.getProduct(productName).getPrice();

        double paidInTotal = handlePayment(needToPay);
        storage.minusProduct(productName, 1);

        System.out.println("Here is your product: " + storage.getProduct(productName).getName());
        handleChange(paidInTotal, needToPay);

        System.out.println("Updated Product Inventory:");
        storage.displayProducts();

        System.out.println("Updated Cash Inventory:");
        cashRegister.displayCash();
    }

    private double handlePayment(double needToPay) throws PayNotAcceptedException {
        double paidInTotal = 0;
        ArrayList<Double> paid = new ArrayList<>();

        System.out.println("Provide bill or coin (accepted values: " + cashRegister.printValues() + "):");

        while (paidInTotal <= needToPay) {
            try {
                double payment = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            if (payment <= 0) {
                throw new PayNotAcceptedException("Can't enter 0 or less!");
            } else if (!cashRegister.getCoins().containsKey(payment)) {
                throw new PayNotAcceptedException("Invalid payment value! Accepted values: " + cashRegister.printValues());
            } else {
                paidInTotal += payment;
                System.out.println("You paid " + paidInTotal + " in total.");
                paid.add(payment);
            }
        }

        for (Double payment : paid) {
            cashRegister.addCash(payment, 1);
        }

        return paidInTotal;
    }

    private void handleChange(double paidInTotal, double needToPay) throws NotEnoughChangeException {
        if (paidInTotal > needToPay) {
            double change = paidInTotal - needToPay;
            System.out.println("Here is your change:");
            cashRegister.change(change).entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(i -> "Value: " + i.getKey() + ", quantity: " + i.getValue())
                    .forEach(System.out::println);
        }
    }

    private void displayInitialInventory() {
        System.out.println("Initial Product Inventory:");
        storage.displayProducts();

        System.out.println("Initial Cash Inventory:");
        cashRegister.displayCash();
    }
}
