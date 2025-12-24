import java.util.ArrayList;
import java.util.List;

public class Order implements Orderable {
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
        System.out.println("-----------------------------------------");
        for (MenuItem item : items) {
            System.out.println("- " + item.getName() + " : $" + item.getPrice());
        }
        if (customer.hasCoupon()) System.out.println(">> Discount Applied (10%)");
        System.out.printf("TOTAL AMOUNT: $%.2f\n", totalAmount);
        paymentMethod.pay(totalAmount);
        System.out.println("=========================================");
        customer.clearCart();
    }
}