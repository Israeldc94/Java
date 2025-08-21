package com.example.capstone_mtg_collection.service;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.model.DeckCard;
import com.example.capstone_mtg_collection.repository.DeckCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckCardService {

    private final DeckCardRepository repository;

    public DeckCardService(DeckCardRepository repository) {
        this.repository = repository;
    }

    public List<Card> getCardsInDeck(int deckId) {
        return repository.findByDeckId(deckId);
    }

    public DeckCard getCardInDeck(int deckId, int cardId) {
        return repository.findByDeckIdAndCardId(deckId, cardId);
    }

    public boolean addCardToDeck(DeckCard deckCard) {
        return repository.addCardToDeck(deckCard);
    }

    public boolean updateCardQuantityInDeck(DeckCard deckCard) {
        return repository.updateCardQuantityInDeck(deckCard);
    }

    public boolean removeCardFromDeck(int deckId, int cardId) {
        return repository.removeCardFromDeck(deckId, cardId);
    }

    public boolean removeAllCardsFromDeck(int deckId) {
        return repository.removeAllCardsFromDeck(deckId);
    }
}