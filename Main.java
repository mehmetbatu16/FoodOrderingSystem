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
        return 0.0; // Gelecek adimda dolduracagiz
    }

    @Override
    public void placeOrder() {
        // Gelecek adimda dolduracagiz
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("System starting...");
    }
}