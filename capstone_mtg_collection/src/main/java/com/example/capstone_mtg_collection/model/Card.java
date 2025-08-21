package com.example.capstone_mtg_collection.model;


public class Card {
    private int cardId;
    private String name;
    private String manaCostText;
    private Integer manaValue;
    private String color;
    private String typeLine;
    private boolean foil;
    private int quantityOwned;
    private String imageUrl;

    public Card(int cardId, String name, String manaCostText, Integer manaValue, String color, String typeLine, boolean foil, int quantityOwned, String imageUrl) {
        this.cardId = cardId;
        this.name = name;
        this.manaCostText = manaCostText;
        this.manaValue = manaValue;
        this.color = color;
        this.typeLine = typeLine;
        this.foil = foil;
        this.quantityOwned = quantityOwned;
        this.imageUrl = imageUrl;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManaCostText() {
        return manaCostText;
    }

    public void setManaCostText(String manaCostText) {
        this.manaCostText = manaCostText;
    }

    public Integer getManaValue() { return manaValue; }

    public void setManaValue() {
        this.manaValue = manaValue;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTypeLine() {
        return typeLine;
    }

    public void setTypeLine(String typeLine) {
        this.typeLine = typeLine;
    }

    public boolean isFoil() {
        return foil;
    }

    public void setFoil(boolean foil) {
        this.foil = foil;
    }

    public int getQuantityOwned() {
        return quantityOwned;
    }

    public void setQuantityOwned(int quantityOwned) {
        this.quantityOwned = quantityOwned;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}