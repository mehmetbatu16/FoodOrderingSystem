import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant = new Restaurant("Java Burger House", 4.8);
        
        System.out.println("### FOOD ORDERING SYSTEM ###");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
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
                    if (customer.getCart().isEmpty()) {
                        System.out.println("Cart is empty.");
                    } else {
                        System.out.println("Payment: [1] Credit Card [2] Cash");
                        int pType = scanner.nextInt();
                        PaymentMethod method;
                        if (pType == 1) {
                            method = new CreditCardPayment();
                        } else {
                            method = new CashPayment();
                        }
                        
                        Order order = new Order(customer, method);
                        order.placeOrder();

                        System.out.print("\nDid you like your meal? Rate the restaurant (1-5): ");
                        int userRating = scanner.nextInt();
                        restaurant.addRating(userRating);
                    }
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
}