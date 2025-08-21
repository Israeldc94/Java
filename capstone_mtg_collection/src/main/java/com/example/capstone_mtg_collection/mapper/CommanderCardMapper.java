package com.example.capstone_mtg_collection.mapper;

import com.example.capstone_mtg_collection.model.CommanderCard;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommanderCardMapper implements RowMapper<CommanderCard> {
    @Override
    public CommanderCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CommanderCard(
                rs.getInt("commander_card_id"),
                rs.getInt("deck_id"),
                rs.getInt("slot"),
                rs.getInt("card_id")
        );
    }
}

