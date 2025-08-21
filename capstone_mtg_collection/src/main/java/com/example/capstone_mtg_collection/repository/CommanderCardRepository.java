package com.example.capstone_mtg_collection.repository;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.model.CommanderCard;
import java.util.List;

public interface CommanderCardRepository {
    List<CommanderCard> findByDeckId(int deckId);     // return slots 1..2
    CommanderCard findByDeckIdAndSlot(int deckId, int slot);
    int insert(int deckID, int cardID, int slot); // expects slot = 1 or 2
    int insertCom(CommanderCard cc);
    int update(CommanderCard cc);
    int deleteByDeckId(int deckId);
    int deleteByDeckIdAndSlot(int deckId, int slot);
    List<Card> findCommanderCards(int deckId);
}
