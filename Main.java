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
public class Main {
    public static void main(String[] args) {
        System.out.println("System starting...");
    }
}