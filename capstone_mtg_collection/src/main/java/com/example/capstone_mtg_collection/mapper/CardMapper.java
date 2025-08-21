package com.example.capstone_mtg_collection.mapper;

import com.example.capstone_mtg_collection.model.Card;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CardMapper implements RowMapper<Card> {

    @Override
    public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Card(
                rs.getInt("card_id"),
                rs.getString("name"),
                rs.getString("mana_cost_text"),
                rs.getString("mana_value") == null ? null : rs.getObject("mana_value", Integer.class),
                rs.getString("color"),
                rs.getString("type_line"),
                rs.getBoolean("foil"),
                rs.getInt("quantity_owned"),
                rs.getString("image_url")
        );
    }
}