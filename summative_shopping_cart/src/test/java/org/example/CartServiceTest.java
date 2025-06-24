package org.example;
import org.example.Mainapp.CartService;
import org.example.Mainapp.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {
    private CartService cart;
    Item orange = new Item("Orange", 2.00);
    Item juice = new Item("Juice",  3.50);




    @BeforeEach
    public void setUp(){
        cart = new CartService();
    }

    @Test
    public void testAddItem() {
        cart.addItem(orange, 3);
        assertEquals(3, cart.getCart().get(orange));
    }

    @Test
    public void testRemoveItem(){
        cart.addItem(orange, 5);
        cart.removeItem(orange, 2);
        assertEquals(3, cart.getCart().get(orange));
    }

    @Test
    public void testRemoveItemAll(){
        cart.addItem(orange, 3);
        cart.removeItem(orange, 4);
        assertFalse(cart.getCart().containsKey(orange));
    }
    @Test
    public void testGetTotal() {
        cart.addItem(orange, 3);
        cart.addItem(juice, 2);
        assertEquals(13.00, cart.getTotal());
    }
}
