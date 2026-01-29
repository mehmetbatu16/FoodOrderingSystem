import java.util.Scanner;
import java.io.*;

public class MenuSystem {
    public void start() {
        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant = new Restaurant("Java Burger House", 4.8);
        loadMenu(restaurant, "menu.csv");       
        System.out.println("### FOOD ORDERING SYSTEM ###");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        String phone;
        while (true) {
            System.out.print("Enter Phone: ");
            phone = scanner.nextLine();
            if (phone.matches("\\d+")) {
                break;
            }
            System.out.println("Invalid input! Please enter only numbers.");
        }

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        Customer customer = new Customer(name, phone, address);
        boolean running = true;
        while (running) {
            System.out.println("\n[1] View Menu");
            System.out.println("[2] Add to Cart");
            System.out.println("[3] Apply Coupon Code");
            System.out.println("[4] Place Order");
            System.out.println("[5] Exit");
            System.out.print("Select: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    restaurant.displayMenu();
                    break;
                case 2:
                    System.out.print("Enter Item ID: ");
                    int id = scanner.nextInt();
                    MenuItem item = restaurant.getItemById(id);
                    if (item != null) {
                        customer.addToCart(item);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Code (STUDENT10): ");
                    String code = scanner.next();
                    customer.applyCoupon(code);
                    break;
                case 4:
                    processOrder(customer, restaurant, scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for choosing Java Burger House! Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private void loadMenu(Restaurant restaurant, String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                String category = data[3];
                restaurant.addMenuItem(new MenuItem(id, name, price, category));
            }
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }
    }

    private void processOrder(Customer customer, Restaurant restaurant, Scanner scanner) {
        if (customer.getCart().isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Payment: [1] Credit Card [2] Cash");
            int pType = scanner.nextInt();
            PaymentMethod method = (pType == 1) ? new CreditCardPayment() : new CashPayment();
            new Order(customer, method).placeOrder();

            boolean ratingDone = false;
            while (!ratingDone) {
                try {
                    System.out.print("\nRate the restaurant (1-5): ");
                    int userRating = scanner.nextInt();
                    if (userRating >= 1 && userRating <= 5) {
                        restaurant.addRating(userRating);
                        ratingDone = true;
                    } else {
                        System.out.println("Please enter 1 to 5.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input! Enter a number.");
                    scanner.next();
                }
            }
        }
    }
}