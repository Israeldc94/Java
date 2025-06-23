package org.example;
import org.example.Mainapp.CartService;
import org.example.Mainapp.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {
    private CartService cart;
    Item Orange = new Item("Orange", 2.00);
    Item Juice = new Item("Juice",  3.50);


    @BeforeEach
    public void setUp(){
        cart = new CartService();
    }

    @Test
    public void testAddItem() {
        cart.addItem(Orange, 3);
        assertEquals(3, cart.getCart().get(Orange));
    }

    @Test
    public void testRemoveItem(){
        cart.addItem(Orange, 5);
        cart.removeItem(Orange, 2);
        assertEquals(3, cart.getCart().get(Orange));
    }

    @Test
    public void testRemoveItemAll(){
        cart.addItem(Orange, 3);
        cart.removeItem(Orange, 3);
        assertFalse(cart.getCart().containsKey(Orange));
    }
    @Test
    public void testGetTotal() {
        cart.addItem(Orange, 3);
        cart.addItem(Juice, 2);
        assertEquals(13.00, cart.getTotal());
    }
}
