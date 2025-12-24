import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
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

    public List<MenuItem> getCart() { return cart; }
    public void clearCart() { cart.clear(); }

    public void applyCoupon(String code) {
        if (code.equalsIgnoreCase("STUDENT10")) {
            hasCoupon = true;
            System.out.println("Coupon Applied! 10% Discount active.");
        } else {
            System.out.println("Invalid Coupon Code.");
        }
    }

    public boolean hasCoupon() { return hasCoupon; }
}