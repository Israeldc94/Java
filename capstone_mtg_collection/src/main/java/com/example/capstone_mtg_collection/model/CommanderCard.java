package com.example.capstone_mtg_collection.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Commander cards for Commander decks")
public class CommanderCard {
    private int commanderCardId;
    private int deckId;
    private int slot;      // 1 = primary, 2 = secondary (partner)
    private int cardId;

    public CommanderCard() {}

    public CommanderCard(int commanderCardId, int deckId, int slot, int cardId) {
        this.commanderCardId = commanderCardId;
        this.deckId = deckId;
        this.slot = slot;
        this.cardId = cardId;
    }

    // getters & setters
    public int getCommanderCardId() { return commanderCardId; }

    public void setCommanderCardId(int commanderCardId) { this.commanderCardId = commanderCardId; }

    public int getDeckId() { return deckId; }

    public void setDeckId(int deckId) { this.deckId = deckId; }

    public int getSlot() { return slot; }

    public void setSlot(int slot) { this.slot = slot; }

    public int getCardId() { return cardId; }

    public void setCardId(int cardId) { this.cardId = cardId; }
}
