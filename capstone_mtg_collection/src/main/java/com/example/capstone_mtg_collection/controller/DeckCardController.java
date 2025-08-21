package com.example.capstone_mtg_collection.controller;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.model.DeckCard;
import com.example.capstone_mtg_collection.service.DeckCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deck-cards")
public class DeckCardController {

    private final DeckCardService service;

    public DeckCardController(DeckCardService service) {
        this.service = service;
    }

    // GET all cards in a specific deck
    @GetMapping("/{deckId}")
    public ResponseEntity<List<Card>> getCardsInDeck(@PathVariable int deckId) {
        List<Card> cards = service.getCardsInDeck(deckId);
        return cards.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(cards);
    }

    // GET a specific card in a specific deck
    @GetMapping("/{deckId}/card/{cardId}")
    public ResponseEntity<DeckCard> getCardInDeck(@PathVariable int deckId, @PathVariable int cardId) {
        DeckCard result = service.getCardInDeck(deckId, cardId);
        return result == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(result);
    }

    // POST: Add a card to a deck
    @PostMapping
    public ResponseEntity<Void> addCardToDeck(@RequestBody DeckCard deckCard) {
        boolean success = service.addCardToDeck(deckCard);
        return success
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // PUT: Update quantity of a card in a deck
    @PutMapping
    public ResponseEntity<Void> updateCardQuantity(@RequestBody DeckCard deckCard) {
        boolean success = service.updateCardQuantityInDeck(deckCard);
        return success
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE: Remove one card from a deck
    @DeleteMapping("/{deckId}/card/{cardId}")
    public ResponseEntity<Void> removeCardFromDeck(@PathVariable int deckId, @PathVariable int cardId) {
        boolean success = service.removeCardFromDeck(deckId, cardId);
        return success
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE: Remove all cards from a deck
    @DeleteMapping("/{deckId}")
    public ResponseEntity<Void> removeAllCards(@PathVariable int deckId) {
        boolean success = service.removeAllCardsFromDeck(deckId);
        return success
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
