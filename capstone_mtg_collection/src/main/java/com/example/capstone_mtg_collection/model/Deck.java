package com.example.capstone_mtg_collection.model;

public class Deck {
    private int deckId;
    private String name;
    private String format;
    private String notes;

    public Deck() {
    }

    public Deck(int deckId, String name, String format, String notes) {
        this.deckId = deckId;
        this.name = name;
        this.format = format;
        this.notes = notes;
    }

    public int getDeckId() { return deckId; }

    public void setDeckId(int deckId) { this.deckId = deckId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getFormat() { return format; }

    public void setFormat(String format) { this.format = format; }

    public String getNotes() { return notes; }

    public void setDescription(String notes) { this.notes = notes; }
}