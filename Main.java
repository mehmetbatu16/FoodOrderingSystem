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
}
public class Main {
    public static void main(String[] args) {
        System.out.println("System starting...");
    }
}