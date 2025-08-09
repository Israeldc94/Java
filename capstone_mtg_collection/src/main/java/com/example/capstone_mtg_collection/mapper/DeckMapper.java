package com.example.capstone_mtg_collection.mapper;

import com.example.capstone_mtg_collection.model.Deck;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DeckMapper implements RowMapper<Deck> {

    @Override
    public Deck mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Deck(
                rs.getInt("deck_id"),
                rs.getString("name"),
                rs.getString("format"),
                rs.getString("notes")
        );
    }
}