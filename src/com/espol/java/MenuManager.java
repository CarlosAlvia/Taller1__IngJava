package com.espol.java;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
class Menu {
    Map<String, Double> items;
    private final Logger logger = Logger.getLogger(Menu.class.getName());
    Menu() {
        items = new HashMap<>();
        items.put("Burger", 10.0);
        items.put("Pizza", 15.0);
        items.put("Salad", 8.0);
        items.put("Pasta", 12.0);
    }

    void show() {
        logger.info("menu:");
        for (Map.Entry<String, Double> item : items.entrySet()) {
            logger.info(item.getKey() + ": $" + item.getValue());
        }
    }

    boolean aval(String var45) {
        //is here
        logger.info("here i am in aval method");
        return var45.equals("Burger") || var45.equals("Pizza") || var45.equals("Salad") || var45.equals("Pasta");
    }

    double getPrice(String var45) {
        return items.get(var45);
    }
}

class Order {
    HashMap<String, Integer> var45s;

    Order() {
        //this will create a new order
        var45s = new HashMap<>();
    }

    void add(String var45, int quantity) {
        //this will add the meal and quantity to the order
        var45s.put(var45, quantity);
    }

    HashMap<String, Integer> getvar45s() {
        return var45s;
    }

    int getvar2() {
        int total = 0;
        for (int quantity : var45s.values()) {
            total += quantity;
        }
        return total;
    }
}

class SumTheTotal {
    double baseCost = 5;

    double calc(Order order, Menu menu) {
        //my function to calculate the total cost
        double totalC = baseCost;
        int var2 = 0;

        for (Map.Entry<String, Integer> item : order.getvar45s().entrySet()) {
            totalC += menu.getPrice(item.getKey()) * item.getValue();
            var2 += item.getValue();
        }

        double discount = 0;
        if (var2 > 5) {
            discount = 0.1;
        } else if (var2 > 10) {
            discount = 0.2;
        }

        totalC = totalC - (totalC * discount);

        double specialDiscount = 0;
        if (totalC > 100) {
            specialDiscount = 25;
        } else if (totalC > 50) {
            specialDiscount = 10;
        }

        totalC -= specialDiscount;

        return totalC;
    }
}

public class MenuManager {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Order order = new Order();
        SumTheTotal calculator = new SumTheTotal();
        Scanner scanner = new Scanner(System.in);
        final Logger logger = Logger.getLogger(Menu.class.getName());
        while (true) {
            menu.show();

            logger.info("Enter meal name to order or 'done' to finish: ");
            String var45 = scanner.nextLine();
            if (var45.equals("done")) break;

            if (!menu.aval(var45)) {
                logger.info("meal not available. Please re-select.");
            }else{
                String logQuantity = String.format("Enter quantity for %s: ", var45);
                logger.info(logQuantity);
                int quantity = scanner.nextInt();
                scanner.nextLine();
                scanner.close();
                if (quantity <= 0) {
                    logger.info("Invalid quantity. Please re-enter.");
                }else{
                    order.add(var45, quantity);
                }
            }

        }

        double totalC = calculator.calc(order, menu);
        int var2 = order.getvar2();

        if (var2 > 100) {
            logger.info("Order quantity exceeds maximum limit. Please re-enter.");
            return;
        }

        logger.info("Your Ord:");
        for (Map.Entry<String, Integer> item : order.getvar45s().entrySet()) {
            logger.info(item.getKey() + ": " + item.getValue());
        }
        String logTotal = String.format("Total Cost: $ %d", totalC);
        logger.info(logTotal);
        logger.info("Confirm order (yes/no): ");
        String confirm = scanner.nextLine();

        if (!confirm.equals("yes") || !confirm.equals("YES")) {
            logger.info("Order canceled.");
            logger.info("-1");
            return;
        }
        String logConfirmedOrder = String.format("Order confirmed. Total cost is: $ %d", totalC);
        logger.info(logConfirmedOrder);
    }
}
