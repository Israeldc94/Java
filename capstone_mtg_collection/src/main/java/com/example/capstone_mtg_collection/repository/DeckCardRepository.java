package com.example.capstone_mtg_collection.repository;

import com.example.capstone_mtg_collection.model.DeckCard;

import java.util.List;

public interface DeckCardRepository {

    List<DeckCard> findByDeckId(int deckId);

    DeckCard findByDeckIdAndCardId(int deckId, int cardId);

    boolean addCardToDeck(DeckCard deckCard);

    boolean updateCardQuantityInDeck(DeckCard deckCard);

    boolean removeCardFromDeck(int deckId, int cardId);

    boolean removeAllCardsFromDeck(int deckId);
}