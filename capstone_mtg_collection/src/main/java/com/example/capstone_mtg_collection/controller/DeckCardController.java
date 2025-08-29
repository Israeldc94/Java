package com.example.capstone_mtg_collection.controller;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.model.DeckCard;
import com.example.capstone_mtg_collection.service.DeckCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deck-cards")
@Tag(name = "Deck Cards", description = "Operations on individual cards within a deck")
public class DeckCardController {

    private final DeckCardService service;

    public DeckCardController(DeckCardService service) {
        this.service = service;
    }

    // GET all cards in a specific deck
    @Operation(
            summary = "List cards in a deck",
            description = "Returns all cards currently in the specified deck. " +
                    "This controller returns 404 if the deck is empty or not found."
    )
    @ApiResponse(
            responseCode = "200", description = "OK",
            content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = Card.class)),
                    examples = @ExampleObject(value = """
            [
              {
                "cardId": 5,
                "name": "Ponder",
                "manaValue": 1,
                "manaCostText": "{U}",
                "typeLine": "Sorcery",
                "color": "Blue",
                "foil": false,
                "quantityOwned": 3,
                "imageUrl": "https://..."
              },
              {
                "cardId": 8,
                "name": "Lightning Bolt",
                "manaValue": 1,
                "manaCostText": "{R}",
                "typeLine": "Instant",
                "color": "Red",
                "foil": false,
                "quantityOwned": 2,
                "imageUrl": "https://..."
              }
            ]
            """)
            )
    )
    @ApiResponse(responseCode = "404", description = "Deck not found or contains no cards")
    @GetMapping("/{deckId}")
    public ResponseEntity<List<Card>> getCardsInDeck(@PathVariable int deckId) {
        List<Card> cards = service.getCardsInDeck(deckId);
        return cards.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(cards);
    }

    // GET a specific card in a specific deck
    @Operation(
            summary = "Get a specific deck-card entry",
            description = "Returns the deck-card relationship (including quantity) for the given deck and card."
    )
    @ApiResponse(
            responseCode = "200", description = "OK",
            content = @Content(
                    schema = @Schema(implementation = DeckCard.class),
                    examples = @ExampleObject(value = """
            {
              "deckId": 42,
              "cardId": 8,
              "quantity": 2
            }
            """)
            )
    )
    @ApiResponse(responseCode = "404", description = "Combination not found")
    @GetMapping("/{deckId}/card/{cardId}")
    public ResponseEntity<DeckCard> getCardInDeck(@PathVariable int deckId, @PathVariable int cardId) {
        DeckCard result = service.getCardInDeck(deckId, cardId);
        return result == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(result);
    }

    // POST: Add a card to a deck
    @Operation(
            summary = "Add a card to a deck",
            description = "Creates or inserts a deck-card entry. Returns 201 on success."
    )
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Invalid payload")
    @ApiResponse(responseCode = "409", description = "Entry already exists (if enforced)")
    @ApiResponse(responseCode = "500", description = "Failed to create deck-card")
    @PostMapping
    public ResponseEntity<Void> addCardToDeck(@RequestBody DeckCard deckCard) {
        boolean success = service.addCardToDeck(deckCard);
        return success
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // PUT: Update quantity of a card in a deck
    @Operation(
            summary = "Update quantity of a deck card",
            description = "Updates the quantity for an existing deck-card entry."
    )
    @ApiResponse(responseCode = "204", description = "Updated, no content")
    @ApiResponse(responseCode = "400", description = "Invalid payload")
    @ApiResponse(responseCode = "404", description = "Entry not found")
    @PutMapping
    public ResponseEntity<Void> updateCardQuantity(@RequestBody DeckCard deckCard) {
        boolean success = service.updateCardQuantityInDeck(deckCard);
        return success
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE: Remove one card from a deck
    @Operation(
            summary = "Remove a single card from a deck",
            description = "Deletes the deck-card entry for the given deck and card."
    )
    @ApiResponse(responseCode = "204", description = "Deleted, no content")
    @ApiResponse(responseCode = "404", description = "Entry not found")
    @DeleteMapping("/{deckId}/card/{cardId}")
    public ResponseEntity<Void> removeCardFromDeck(@PathVariable int deckId, @PathVariable int cardId) {
        boolean success = service.removeCardFromDeck(deckId, cardId);
        return success
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE: Remove all cards from a deck
    @Operation(
            summary = "Remove all cards from a deck",
            description = "Deletes all deck-card entries associated with the deck."
    )
    @ApiResponse(responseCode = "204", description = "Deleted, no content")
    @ApiResponse(responseCode = "404", description = "Deck not found or already empty")
    @DeleteMapping("/{deckId}")
    public ResponseEntity<Void> removeAllCards(@PathVariable int deckId) {
        boolean success = service.removeAllCardsFromDeck(deckId);
        return success
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
