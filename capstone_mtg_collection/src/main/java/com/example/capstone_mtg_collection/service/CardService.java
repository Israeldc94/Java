package com.example.capstone_mtg_collection.service;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepository repo;

    public CardService(CardRepository repo) {
        this.repo = repo;
    }

    public List<Card> getAll() {
        return repo.findAll();
    }

    public Card getById(int id) {
        return repo.findById(id);
    }

    public int add(Card card) {
        sanitize(card);
        return repo.insert(card);
    }

    public int update(int id, Card card) {
        card.setCardId(id); // ensure correct ID for update
        sanitize(card);
        return repo.update(card);
    }

    public int deleteById(int id) {
        return repo.deleteById(id);
    }

    // Optional cleanup/validation method
    private void sanitize(Card card) {
        if (card.getName() != null) {
            card.setName(card.getName().trim());
        }
        if (card.getManaCost() != null) {
            card.setManaCost(card.getManaCost().trim());
        }
        if (card.getColor() != null) {
            card.setColor(card.getColor().trim());
        }
        if (card.getTypeLine() != null) {
            card.setTypeLine(card.getTypeLine().trim());
        }
        if (card.getImageUrl() != null) {
            card.setImageUrl(card.getImageUrl().trim());
        }
    }

    public List<Card> searchByName(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword);
    }
}
