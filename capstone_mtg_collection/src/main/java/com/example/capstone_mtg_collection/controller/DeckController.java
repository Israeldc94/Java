package com.example.capstone_mtg_collection.controller;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.model.Deck;
import com.example.capstone_mtg_collection.model.DeckCard;
import com.example.capstone_mtg_collection.service.DeckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/decks")
public class DeckController {

    private final DeckService service;

    public DeckController(DeckService service) {
        this.service = service;
    }

    // ---------- Basic CRUD ----------

    @GetMapping
    public List<Deck> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> findById(@PathVariable int id) {
        Deck deck = service.findById(id);
        return deck != null ? ResponseEntity.ok(deck) : ResponseEntity.notFound().build();
    }

    /** Create deck and return its id as JSON so the FE can continue the workflow. */
    @PostMapping
    public ResponseEntity<Map<String, Integer>> add(@RequestBody Deck deck) {
        // Service should insert and return new deck_id (0 if failure)
        int deckId = service.addAndReturnId(deck);
        if (deckId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("deckId", deckId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Deck deck) {
        deck.setDeckId(id);
        boolean ok = service.update(deck);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        boolean ok = service.deleteById(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // ---------- Deck contents (commanders & cards) ----------

    /** Add 1–2 commanders to a deck (Commander format). Body: { "cardIds": [1,2] } */
    @PostMapping("/{deckId}/commanders")
    public ResponseEntity<Void> addCommanders(
            @PathVariable int deckId,
            @RequestBody IdList body
    ) {
        boolean ok = service.addCommanders(deckId, body.getCardIds());
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    /** Add many cards to a deck. Body: { "cardIds": [10,11,12,...] } */
    @PostMapping("/{deckId}/cards")
    public ResponseEntity<Void> addCards(
            @PathVariable int deckId,
            @RequestBody IdList body
    ) {
        boolean ok = service.addCards(deckId, body.getCardIds());
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    // ---------- Tiny DTO for { "cardIds": [...] } ----------
    public static class IdList {
        private List<Integer> cardIds;
        public List<Integer> getCardIds() { return cardIds; }
        public void setCardIds(List<Integer> cardIds) { this.cardIds = cardIds; }
    }

    // DeckController.java
    @GetMapping("/{id}/commanders")
    public ResponseEntity<List<Card>> getCommanders(@PathVariable int id) {
        List<Card> cards = service.findCommanderCards(id); // new
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<List<Card>> getDeckCards(@PathVariable int id) {
        List<Card> cards = service.findDeckCards(id); // new
        return ResponseEntity.ok(cards);
    }

}
