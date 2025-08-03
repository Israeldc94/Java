package org.example.service;

import org.example.data.OrderRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.Order.MySqlOrderRepo;
import org.example.data.impl.Order.OrderMapper;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlOrderRepoTest {

    private OrderRepo repo;
    private OrderMapper orderMapper;

    @BeforeEach
    void setup() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/SimpleBistroTest");
        ds.setUsername("root");
        ds.setPassword("Cerseiwatch304!");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.execute("CALL set_known_good_state()");

        orderMapper = new OrderMapper();
        repo = new MySqlOrderRepo(jdbcTemplate);

    }

    @Test
    void shouldGetOrderByIdWithFullDetails() throws Exception {
        Order order = repo.getOrderById(1);
        assertNotNull(order);

        assertEquals(1, order.getOrderID());

        assertNotNull(order.getServer());
        assertNotNull(order.getItems());
        assertFalse(order.getItems().isEmpty());

        assertNotNull(order.getPayments());
        assertFalse(order.getPayments().isEmpty());

        assertTrue(order.getItems().stream().allMatch(i -> i.getItem() != null));
        assertTrue(order.getItems().stream().allMatch(i -> i.getItem().getItemCategory() != null));

        assertTrue(order.getPayments().stream().allMatch(p -> p.getPaymentType() != null));
    }

    @Test
    void shouldGetAllOrders() throws Exception {
        List<Order> orders = repo.getAllOrders();

        assertNotNull(orders);
        assertFalse(orders.isEmpty());

        for (Order order : orders) {
            assertNotNull(order.getOrderID());
            assertNotNull(order.getServer());
            assertNotNull(order.getOrderDate());
            assertNotNull(order.getTotal());
        }
    }

    @Test
    void shouldAddOrder() throws Exception {
        Order order = new Order();
        order.setServerID(1);
        order.setOrderDate(LocalDateTime.now());
        order.setSubTotal(new BigDecimal("30.00"));
        order.setTax(new BigDecimal("2.40"));
        order.setTip(new BigDecimal("5.00"));
        order.setTotal(new BigDecimal("37.40"));

        Order result = repo.addOrder(order);

        assertNotNull(result);
        assertTrue(result.getOrderID() > 0);
        assertEquals(order.getServerID(), result.getServerID());
        assertEquals(order.getSubTotal(), result.getSubTotal());
        assertEquals(order.getTax(), result.getTax());
        assertEquals(order.getTip(), result.getTip());
        assertEquals(order.getTotal(), result.getTotal());
    }

    @Test
    void shouldUpdateOrder() throws Exception {

        Order original = repo.getOrderById(1);
        assertNotNull(original);


        original.setTip(new BigDecimal("10.00"));
        original.setSubTotal(new BigDecimal("50.00"));
        original.setTax(new BigDecimal("4.50"));
        original.setTotal(new BigDecimal("64.50"));


        repo.updateOrder(original);


        Order updated = repo.getOrderById(1);
        assertNotNull(updated);

        assertEquals(new BigDecimal("10.00"), updated.getTip());
        assertEquals(new BigDecimal("50.00"), updated.getSubTotal());
        assertEquals(new BigDecimal("4.50"), updated.getTax());
        assertEquals(new BigDecimal("64.50"), updated.getTotal());
    }

    @Test
    void shouldDeleteOrder() throws InternalErrorException, RecordNotFoundException {
        int id = 500;

        List<OrderItem> items = new ArrayList<>();
        OrderItem item = new OrderItem();
        item.setOrderID(id);
        item.setItemID(1);
        item.setQuantity(2);
        item.setPrice(new BigDecimal("15.00"));
        items.add(item);

        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();
        payment.setOrderID(id);
        payment.setPaymentTypeID(1);
        payment.setAmount(new BigDecimal("37.40"));
        payments.add(payment);

        Order order = new Order();

        order.setOrderID(id);
        order.setServerID(1);
        order.setOrderDate(LocalDateTime.now());
        order.setSubTotal(new BigDecimal("30.00"));
        order.setTax(new BigDecimal("2.40"));
        order.setTip(new BigDecimal("5.00"));
        order.setTotal(new BigDecimal("37.40"));
        order.setPayments(payments);
        order.setItems(items);

        repo.addOrder(order);

        Order result = repo.addOrder(order);
        assertNotNull(result);


        List<Order> remainingOrders = repo.getAllOrders();
        boolean stillExists = remainingOrders.stream()
                .anyMatch(o -> o.getOrderID() == id);
        assertFalse(stillExists, "Order should be deleted but still exists.");


        assertNull(order.getPayments(), "Payments for the deleted order should also be removed.");


        assertNull(order.getItems(), "OrderItems for the deleted order should also be removed.");
    }

}
