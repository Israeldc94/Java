package org.example.data.impl.Order;

import org.example.model.Item;
import org.example.model.ItemCategory;
import org.example.model.OrderItem;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderItemMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();

        orderItem.setOrderItemID(rs.getInt("OrderItemID"));
        orderItem.setOrderID(rs.getInt("OrderID"));
        orderItem.setItemID(rs.getInt("ItemID"));
        orderItem.setQuantity(rs.getInt("Quantity"));
        orderItem.setPrice(rs.getBigDecimal("Price"));

        // Item
        Item item = new Item();
        item.setItemID(rs.getInt("ItemID"));
        item.setItemCategoryID(rs.getInt("ItemCategoryID"));
        item.setItemName(rs.getString("ItemName"));
        item.setItemDescription(rs.getString("ItemDescription"));
        item.setStartDate(rs.getDate("StartDate").toLocalDate());
        item.setEndDate(rs.getDate("EndDate") != null ? rs.getDate("EndDate").toLocalDate() : null);
        item.setUnitPrice(rs.getBigDecimal("UnitPrice"));

        // ItemCategory
        ItemCategory category = new ItemCategory();
        category.setItemCategoryID(rs.getInt("ItemCategoryID"));
        category.setItemCategoryName(rs.getString("ItemCategoryName"));

        item.setItemCategory(category);
        orderItem.setItem(item);

        return orderItem;
    }
}
