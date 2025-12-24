public class CashPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Payment type: Cash at Door. Amount to collect: $" + amount);
    }
}