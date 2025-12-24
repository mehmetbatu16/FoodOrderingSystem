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
            System.out.println("\n[1] View Menu\n[2] Add to Cart\n[3] Apply Coupon\n[4] Place Order\n[5] Exit");
            System.out.print("Select: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: restaurant.displayMenu(); break;
                case 2:
                    System.out.print("Enter Item ID: ");
                    int id = scanner.nextInt();
                    MenuItem item = restaurant.getItemById(id);
                    if (item != null) customer.addToCart(item);
                    else System.out.println("Item not found.");
                    break;
                case 3:
                    System.out.print("Enter Code (STUDENT10): ");
                    customer.applyCoupon(scanner.next());
                    break;
                case 4:
                    if (customer.getCart().isEmpty()) System.out.println("Cart is empty.");
                    else {
                        System.out.println("Payment: [1] Credit Card [2] Cash");
                        PaymentMethod method = (scanner.nextInt() == 1) ? new CreditCardPayment() : new CashPayment();
                        new Order(customer, method).placeOrder();
                    }
                    break;
                case 5:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
            }
        }
        scanner.close();
    }
}