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
}
}
public class Main {
    public static void main(String[] args) {
        System.out.println("System starting...");
    }
}