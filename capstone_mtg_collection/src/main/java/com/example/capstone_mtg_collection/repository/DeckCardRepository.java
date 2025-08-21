package com.example.capstone_mtg_collection.repository;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.model.DeckCard;

import java.util.List;

public interface DeckCardRepository {

    List<Card> findByDeckId(int deckId);

    DeckCard findByDeckIdAndCardId(int deckId, int cardId);

    boolean addCardToDeck(DeckCard deckCard);

    boolean updateCardQuantityInDeck(DeckCard deckCard);

    boolean removeCardFromDeck(int deckId, int cardId);

    boolean removeAllCardsFromDeck(int deckId);

    int insert(int deckId, int cardId, int quantity);
}