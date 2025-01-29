import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import exceptions.*;

public class SupermarketServiceImpl implements SupermarketService {
    private static SupermarketServiceImpl instance;

    private Scanner scanner = new Scanner(System.in);
    ProductStorage storage = new ProductStorage();
    CashRegister cashRegister = new CashRegister();

    private SupermarketServiceImpl() {
    }

    public static SupermarketServiceImpl getInstance() {
        if (instance == null) {
            instance = new SupermarketServiceImpl();
        }
        return instance;
    }

    public void greetings() {
        System.out.println("Welcome to TECHIN supermarket!");
        while (true) {
            System.out.println("Please select initial state of the supermarket! Type 'Start' or 'Quit'");
            String input = scanner.nextLine().toLowerCase();
            try {
                switch (input) {
                    case "start" -> initialization();
                    case "quit" -> {
                        System.out.println("Bye!");
                        return;
                    }
                    default -> System.out.println("No such option, try again!");
                }
            } catch (SoldOutException | NotEnoughChangeException | PayNotAcceptedException e) {
                handleException(e);
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    public void console() {
        while (true) {
            try {
                displayInitialInventory();

                System.out.println("What would you like to buy? Type in the name of desired product: ");
                String input = scanner.nextLine().toUpperCase();

                if (input.isEmpty()) {
                    System.out.println("Input is empty, try again.");
                } else if (storage.hasProduct(input)) {
                    processPurchase(input);
                } else if (input.equalsIgnoreCase("Quit")) {
                    return;
                } else {
                    System.out.println("Error! Product not available!");
                }
            } catch (SoldOutException | NotEnoughChangeException | PayNotAcceptedException e) {
                handleException(e);
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    public void initialization() throws SoldOutException, NotEnoughChangeException, PayNotAcceptedException {
        storage.addProduct("APPLE", new Product("APPLE", 1.0, 20));
        storage.addProduct("BREAD", new Product("BREAD", 2.5, 5));
        storage.addProduct("MILK", new Product("MILK", 1.5, 10));

        cashRegister.addCash(0.1, 50);
        cashRegister.addCash(0.5, 20);
        cashRegister.addCash(1.0, 10);
        cashRegister.addCash(2.0, 10);

        console();
    }

    public void processPurchase(String productName) throws SoldOutException, NotEnoughChangeException, PayNotAcceptedException {
        try {
            System.out.println("You are trying to buy " + storage.getProduct(productName).getName()
                    + ". You need to pay " + storage.getProduct(productName).getPrice());
            double needToPay = storage.getProduct(productName).getPrice();

            double paidInTotal = handlePayment(needToPay);
            storage.minusProduct(productName, 1);

            System.out.println("Here is your product: " + storage.getProduct(productName).getName());
            handleChange(paidInTotal, needToPay);

            displayUpdatedInventory();

        } catch (SoldOutException | NotEnoughChangeException | PayNotAcceptedException e) {
            handleException(e);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public double handlePayment(double needToPay) throws PayNotAcceptedException {
        double paidInTotal = 0;
        ArrayList<Double> paid = new ArrayList<>();

        System.out.println("Provide bill or coin (accepted values: " + cashRegister.printValues() + "):");

        while (paidInTotal <= needToPay) {
            try {
                double payment = Double.parseDouble(scanner.nextLine());

                if (payment <= 0) {
                    throw new PayNotAcceptedException("Can't enter 0 or less!");
                } else if (!cashRegister.getCoins().containsKey(payment)) {
                    throw new PayNotAcceptedException("Invalid payment value! Accepted values: " + cashRegister.printValues());
                } else {
                    paidInTotal += payment;
                    System.out.println("You paid " + paidInTotal + " in total.");
                    paid.add(payment);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (PayNotAcceptedException e) {
                handleException(e);
            }
        }

        for (Double payment : paid) {
            cashRegister.addCash(payment, 1);
        }

        return paidInTotal;
    }

    public void handleChange(double paidInTotal, double needToPay) throws NotEnoughChangeException {
        if (paidInTotal > needToPay) {
            try {
                double change = paidInTotal - needToPay;
                System.out.println("Here is your change:");
                cashRegister.change(change).entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .map(i -> "Value: " + i.getKey() + ", quantity: " + i.getValue())
                        .forEach(System.out::println);
            } catch (NotEnoughChangeException e) {
                handleException(e);
            }
        }
    }

    public void displayInitialInventory() {
        System.out.println("Initial Product Inventory:");
        storage.displayProducts();

        System.out.println();

        System.out.println("Initial Cash Inventory:");
        cashRegister.displayCash();
    }

    public void displayUpdatedInventory() {
        System.out.println("Updated Product Inventory:");
        storage.displayProducts();

        System.out.println();

        System.out.println("Updated Cash Inventory:");
        cashRegister.displayCash();
    }

    public void handleException(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}