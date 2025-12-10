import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
interface Orderable {
    void placeOrder();
    double calculateTotal();
}

interface PaymentMethod {
    void pay(double amount);
}
class User {
    private String name;
    private String phone;
    private String address;

    public User(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
class MenuItem {
    private int id;
    private String name;
    private double price;
    private String category;

    public MenuItem(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
@Override
    public String toString() {
        return String.format("%-3d %-20s ($%.2f) [%s]", id, name, price, category);
    }
}
class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Processing Credit Card payment...");
        System.out.println("Payment of $" + amount + " successful.");
    }
}

class CashPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Payment type: Cash at Door. Amount to collect: $" + amount);
    }
}
class Customer extends User {
    private List<MenuItem> cart;
    private boolean hasCoupon;

    public Customer(String name, String phone, String address) {
        super(name, phone, address);
        this.cart = new ArrayList<>();
        this.hasCoupon = false;
    }

    public void addToCart(MenuItem item) {
        cart.add(item);
        System.out.println(item.getName() + " added to cart.");
    }

    public List<MenuItem> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }

    public void applyCoupon(String code) {
        if (code.equalsIgnoreCase("STUDENT10")) {
            hasCoupon = true;
            System.out.println("Coupon Applied! 10% Discount active.");
        } else {
            System.out.println("Invalid Coupon Code.");
        }
    }

    public boolean hasCoupon() {
        return hasCoupon;
    }
}

class Restaurant {
    private String name;
    private double rating;
    private List<MenuItem> menu;

    public Restaurant(String name, double rating) {
        this.name = name;
        this.rating = rating;
        this.menu = new ArrayList<>();
        initMenu();
    }

    private void initMenu() {
        menu.add(new MenuItem(1, "Cheeseburger", 12.50, "Food"));
        menu.add(new MenuItem(2, "Pepperoni Pizza", 15.00, "Food"));
        menu.add(new MenuItem(3, "Caesar Salad", 9.00, "Food"));
        menu.add(new MenuItem(4, "Coke", 2.50, "Drink"));
        menu.add(new MenuItem(5, "Iced Coffee", 4.00, "Drink"));
        menu.add(new MenuItem(6, "Chocolate Cake", 6.50, "Dessert"));
    }

    public void displayMenu() {
        System.out.println("\n--- " + name + " Menu (Rating: " + rating + "/5.0) ---");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }

    public MenuItem getItemById(int id) {
        for (MenuItem item : menu) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
class Order implements Orderable {
    private Customer customer;
    private List<MenuItem> items;
    private PaymentMethod paymentMethod;
    private double totalAmount;

    public Order(Customer customer, PaymentMethod paymentMethod) {
        this.customer = customer;
        this.items = new ArrayList<>(customer.getCart());
        this.paymentMethod = paymentMethod;
        this.totalAmount = calculateTotal();
    }
    @Override
    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        if (customer.hasCoupon()) {
            total = total * 0.90;
        }
        return total;
    }
    @Override
    public void placeOrder() {
        System.out.println("\n============= ORDER SUMMARY =============");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Phone   : " + customer.getPhone());
        System.out.println("Address : " + customer.getAddress());
        System.out.println("-----------------------------------------");

        for (MenuItem item : items) {
            System.out.println("- " + item.getName() + " : $" + item.getPrice());
        }

        if (customer.hasCoupon()) {
            System.out.println(">> Discount Applied (10%)");
        }

        System.out.println("-----------------------------------------");
        System.out.printf("TOTAL AMOUNT: $%.2f\n", totalAmount);

        paymentMethod.pay(totalAmount);

        System.out.println("=========================================");
        customer.clearCart();
    }
}

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
                    System.out.print("Enter Code (Try 'STUDENT10'): ");
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