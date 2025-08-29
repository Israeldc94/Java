package com.example.capstone_mtg_collection.controller;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.model.Deck;
import com.example.capstone_mtg_collection.model.DeckCard;
import com.example.capstone_mtg_collection.service.DeckService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/decks")
@Tag(name = "Decks", description = "Manage decks and their contents (cards & commanders)")
public class DeckController {

    private final DeckService service;

    public DeckController(DeckService service) {
        this.service = service;
    }

    // ---------- Basic CRUD ----------

    @Operation(
            summary = "List all decks",
            description = "Returns all deck metadata. Cards/commanders are fetched via nested endpoints."
    )
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Deck.class))))
    @GetMapping
    public List<Deck> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Get a deck by ID")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = Deck.class)))
    @ApiResponse(responseCode = "404", description = "Deck not found")
    @GetMapping("/{id}")
    public ResponseEntity<Deck> findById(@PathVariable int id) {
        Deck deck = service.findById(id);
        return deck != null ? ResponseEntity.ok(deck) : ResponseEntity.notFound().build();
    }

    /** Create deck and return its id as JSON so the FE can continue the workflow. */
    @Operation(
            summary = "Create a deck",
            description = "Creates a new deck. Returns the new deck ID."
    )
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(schema = @Schema(implementation = Deck.class),
                    examples = @ExampleObject(value = "{\"deckId\":101}")))
    @ApiResponse(responseCode = "400", description = "Invalid payload")
    @PostMapping
    public ResponseEntity<Map<String, Integer>> add(@RequestBody Deck deck) {
        // Service should insert and return new deck_id (0 if failure)
        int deckId = service.addAndReturnId(deck);
        if (deckId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("deckId", deckId));
    }

    @Operation(summary = "Update a deck")
    @ApiResponse(responseCode = "204", description = "Updated, no content")
    @ApiResponse(responseCode = "404", description = "Deck not found")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Deck deck) {
        deck.setDeckId(id);
        boolean ok = service.update(deck);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    @Operation(summary = "Delete a deck")
    @ApiResponse(responseCode = "204", description = "Deleted, no content")
    @ApiResponse(responseCode = "404", description = "Deck not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        boolean ok = service.deleteById(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // ---------- Deck contents (commanders & cards) ----------

    /** Add 1–2 commanders to a deck (Commander format). Body: { "cardIds": [1,2] } */
    @Operation(
            summary = "Set commanders for a deck",
            description = "Replaces commanders with the given card IDs. Typically 1–2 for Commander."
    )
    @ApiResponse(responseCode = "204", description = "Saved")
    @ApiResponse(responseCode = "400", description = "Invalid deck or card IDs")
    @PostMapping("/{deckId}/commanders")
    public ResponseEntity<Void> addCommanders(
            @PathVariable int deckId,
            @RequestBody IdList body
    ) {
        boolean ok = service.addCommanders(deckId, body.getCardIds());
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    /** Add many cards to a deck. Body: { "cardIds": [10,11,12,...] } */
    @Operation(summary = "Add cards to a deck (batch)")
    @ApiResponse(responseCode = "204", description = "Added")
    @ApiResponse(responseCode = "400", description = "Invalid deck or card IDs")
    @PostMapping("/{deckId}/cards")
    public ResponseEntity<Void> addCards(
            @PathVariable int deckId,
            @RequestBody IdList body
    ) {
        boolean ok = service.addCards(deckId, body.getCardIds());
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    // ---------- Tiny DTO for { "cardIds": [...] } ----------
    @Schema(description = "Batch list of card IDs")
    public static class IdList {
        private List<Integer> cardIds;
        public List<Integer> getCardIds() { return cardIds; }
        public void setCardIds(List<Integer> cardIds) { this.cardIds = cardIds; }
    }

    @Operation(
            summary = "Get commanders in a deck",
            description = "Returns the commander cards for the given deck. Typically 1–2 cards for Commander format."
    )
    @ApiResponse(
            responseCode = "200", description = "OK",
            content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = Card.class)),
                    examples = @ExampleObject(value = """
        [
          {
            "cardId": 1001,
            "name": "Niv-Mizzet, Parun",
            "manaValue": 6,
            "manaCostText": "{U}{U}{U}{R}{R}{R}",
            "typeLine": "Legendary Creature — Dragon Wizard",
            "color": "UR",
            "foil": false,
            "quantityOwned": 1,
            "imageUrl": "https://..."
          }
        ]
        """)
            )
    )
    @ApiResponse(responseCode = "404", description = "Deck not found")
    @GetMapping("/{id}/commanders")
    public ResponseEntity<List<Card>> getCommanders(@PathVariable int id) {
        List<Card> cards = service.findCommanderCards(id); // new
        return ResponseEntity.ok(cards);
    }

    @Operation(
            summary = "Get all non-commander cards in a deck",
            description = "Returns the mainboard cards currently in the deck (excludes commanders)."
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
    @ApiResponse(responseCode = "404", description = "Deck not found")
    @GetMapping("/{id}/cards")
    public ResponseEntity<List<Card>> getDeckCards(@PathVariable int id) {
        List<Card> cards = service.findDeckCards(id); // new
        return ResponseEntity.ok(cards);
    }

}
