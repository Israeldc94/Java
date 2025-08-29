package com.example.capstone_mtg_collection.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "model for join table in database to manage cards in a deck (many to many)")
public class DeckCard {
    private int deckId;
    private int cardId;
    private int quantity;


    public DeckCard(int deckId, int cardId, int quantity) {
        this.deckId = deckId;
        this.cardId = cardId;
        this.quantity = quantity;
    }

    public int getDeckId() { return deckId; }

    public void setDeckId(int deckId) { this.deckId = deckId; }

    public int getCardId() { return cardId; }

    public void setCardId(int cardId) { this.cardId = cardId; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}