import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
public class CustomerTest {
    private Customer customer;
    @BeforeEach
    public void setup() {
        customer = new Customer("Batu Buladi", "5323064217", "BOMBO SOKAK");
    }
    @Test
    public void testApplyValidCoupon() {
        customer.applyCoupon("STUDENT10");
        assertTrue(customer.hasCoupon());
    }
    @Test
    public void testApplyInvalidCoupon() {
        customer.applyCoupon("DISCOUNT50");
        assertFalse(customer.hasCoupon());
    }
    @Test
    public void testAddToCart() {
        MenuItem item = new MenuItem(1, "Pizza", 12.0, "Food");
        customer.addToCart(item);
        assertEquals(1, customer.getCart().size());
    }
    @Test
    public void testClearCart() {
        MenuItem item = new MenuItem(1, "Pizza", 12.0, "Food");
        customer.addToCart(item);
        customer.clearCart();
        assertEquals(0, customer.getCart().size());
    }
}
