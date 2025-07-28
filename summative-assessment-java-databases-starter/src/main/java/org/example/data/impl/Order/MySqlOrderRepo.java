package org.example.data.impl.Order;

import com.mysql.cj.protocol.Resultset;
import org.example.data.OrderRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.Item.ItemCategoryMapper;
import org.example.data.impl.Item.ItemMapper;
import org.example.data.impl.Payment.PaymentMapper;
import org.example.data.impl.Payment.PaymentTypeMapper;
import org.example.model.Item;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MySqlOrderRepo implements OrderRepo {
    private final JdbcTemplate jdbcTemplate;
    private final OrderItemMapper orderItemMapper = new OrderItemMapper();
    private final ItemMapper itemMapper = new ItemMapper();
    private final ItemCategoryMapper itemCategoryMapper = new ItemCategoryMapper();
    private final PaymentMapper paymentMapper = new PaymentMapper();
    private final PaymentTypeMapper paymentTypeMapper = new PaymentTypeMapper();

    @Autowired
    public MySqlOrderRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public Order getOrderById(int id) throws RecordNotFoundException, InternalErrorException {
        try {
            return jdbcTemplate.execute("{CALL get_order_with_details(?)}",
                    (CallableStatementCallback<Order>) cs -> {
                        cs.setInt(1, id);

                        boolean hasResults = cs.execute();

                        Order order = null;

                        // 1st Result Set - Order + Server
                        if (hasResults) {
                            try (ResultSet rs = cs.getResultSet()) {
                                if (rs.next()) {
                                    order = OrderMapper.mapOrder(rs);
                                    order.setServer(OrderMapper.mapServer(rs));
                                }
                            }
                        }

                        // 2nd Result Set - OrderItems + Items + Categories
                        List<OrderItem> items = new ArrayList<>();
                        if (cs.getMoreResults()) {
                            try (ResultSet rs = cs.getResultSet()) {
                                while (rs.next()) {
                                    OrderItem orderItem = orderItemMapper.mapRow(rs, 1);
                                    Item item = itemMapper.mapRow(rs, 1);
                                    item.setItemCategory(itemCategoryMapper.mapRow(rs, 1));
                                    orderItem.setItem(item);
                                    items.add(orderItem);
                                }
                            }
                        }

                        // 3rd Result Set - Payments + PaymentTypes
                        List<Payment> payments = new ArrayList<>();
                        if (cs.getMoreResults()) {
                            try (ResultSet rs = cs.getResultSet()) {
                                while (rs.next()) {
                                    Payment payment = paymentMapper.mapRow(rs, 1);
                                    payment.setPaymentType(paymentTypeMapper.mapRow(rs, 1));
                                    payments.add(payment);
                                }
                            }
                        }

                        if (order != null) {
                            order.setItems(items);
                            order.setPayments(payments);
                        }

                        return order;
                    });

        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalErrorException();
        }
    }


    @Override
    public List<Order> getAllOrders() throws InternalErrorException, RecordNotFoundException {
        try {
            String sql = """
            SELECT o.OrderID, o.ServerID, o.OrderDate, o.SubTotal, o.Tax, o.Tip, o.Total,
                   s.FirstName, s.LastName, s.HireDate, s.TermDate
            FROM `Order` o
            JOIN Server s ON o.ServerID = s.ServerID
            """;

            List<Order> orders = jdbcTemplate.query(sql, (rs, rowNum) -> {
                Order order = OrderMapper.mapOrder(rs);
                order.setServer(OrderMapper.mapServer(rs));
                return order;
            });

            if (orders.isEmpty()) {
                throw new RecordNotFoundException();
            }

            return orders;

        } catch (Exception ex) {
            throw new InternalErrorException();
        }
    }

    @Override
    public Order addOrder(Order order) throws InternalErrorException {
        try {
            String sql = """
                    INSERT INTO `Order` (ServerID, OrderDate, SubTotal, Tax, Tip, Total)
                    VALUES(?, ?, ?, ?, ?, ?)
                    """;

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, order.getServerID());
                ps.setObject(2, order.getOrderDate());
                ps.setBigDecimal(3, order.getSubTotal());
                ps.setBigDecimal(4, order.getTax());
                ps.setBigDecimal(5, order.getTip());
                ps.setBigDecimal(6, order.getTotal());
                return ps;
            }, keyHolder);

            int newOrderID = keyHolder.getKey().intValue();
            order.setOrderID(newOrderID);

            return order;
        } catch (Exception ex) {
            throw new InternalErrorException();
        }
    }

    @Override
    public void updateOrder(Order order) throws InternalErrorException {
        try {
            String sql = """
                    UPDATE `Order` SET serverID = ?, OrderDate = ?, SubTotal = ?, Tax = ?, Tip = ?, Total = ?
                    WHERE OrderID = ?
                    """;

            jdbcTemplate.update(sql,
                    order.getServerID(),
                    order.getOrderDate(),
                    order.getSubTotal(),
                    order.getTax(),
                    order.getTip(),
                    order.getTotal(),
                    order.getOrderID());
        } catch (Exception ex) {
            throw new InternalErrorException();

        }
    }

    @Override
    @Transactional
    public Order deleteOrder(int id) throws InternalErrorException {
        try {
            Order deleted = getOrderById(id); // Get it first so we can return it after deletion

            final String deletePaymentsSql = "DELETE FROM Payment WHERE OrderID = ?";
            final String deleteOrderItemsSql = "DELETE FROM OrderItem WHERE OrderID = ?";
            final String deleteOrderSql = "DELETE FROM `Order` WHERE OrderID = ?"; // Backticks stay

            jdbcTemplate.update(deletePaymentsSql, id);
            jdbcTemplate.update(deleteOrderItemsSql, id);
            jdbcTemplate.update(deleteOrderSql, id);

            return deleted;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalErrorException();
        }
    }
}
