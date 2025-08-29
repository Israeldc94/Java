// src/main/java/.../controller/CommanderCardController.java
package com.example.capstone_mtg_collection.controller;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.service.CommanderCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commander-cards") // <-- distinct base path, no conflicts
@Tag(name = "Commander Cards", description = "Manage commander slots (1–2) per deck")
public class CommanderCardController {

    private final CommanderCardService service;

    public CommanderCardController(CommanderCardService service) {
        this.service = service;
    }

    // OPTIONAL helper: list commanders for a deck via this controller
    @Operation(
            summary = "List commanders for a deck",
            description = "Returns the commander cards currently assigned to the deck (typically 1–2 for Commander)."
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
    @GetMapping("/deck/{deckId}")
    public ResponseEntity<List<Card>> listByDeck(@PathVariable int deckId) {
        return ResponseEntity.ok(service.findCommanderCards(deckId));
    }

    // OPTIONAL: set BOTH commanders at once (mirrors your DeckController POST if you want it here instead)
    @Operation(
            summary = "Set commanders (bulk)",
            description = "Replaces the deck's commanders with the provided card IDs (0–2 items)."
    )
    @ApiResponse(responseCode = "204", description = "Saved")
    @ApiResponse(responseCode = "400", description = "Invalid deck or card IDs")
    @PostMapping("/deck/{deckId}")
    public ResponseEntity<Void> setCommanders(
            @PathVariable int deckId,
            @RequestBody IdList body
    ) {
        boolean ok = service.setCommanders(deckId, body.cardIds);
        return ok ? ResponseEntity.noContent().build()
                : ResponseEntity.badRequest().build();
    }

    // OPTIONAL: upsert a single slot (1 or 2)
    @Operation(
            summary = "Set a single commander slot",
            description = "Upserts a specific commander slot (1 or 2) to the given card ID."
    )
    @ApiResponse(responseCode = "204", description = "Saved")
    @ApiResponse(responseCode = "400", description = "Invalid deck, slot, or card")
    @PutMapping("/deck/{deckId}/{slot}")
    public ResponseEntity<Void> setCommanderSlot(
            @PathVariable int deckId,
            @PathVariable int slot,
            @RequestParam int cardId
    ) {
        boolean ok = service.upsert(deckId, slot, cardId) > 0;
        return ok ? ResponseEntity.noContent().build()
                : ResponseEntity.badRequest().build();
    }

    // OPTIONAL: clear one slot
    @Operation(
            summary = "Clear a commander slot",
            description = "Clears the specified commander slot (1 or 2)."
    )
    @ApiResponse(responseCode = "204", description = "Cleared")
    @ApiResponse(responseCode = "404", description = "Deck or slot not found / already empty")
    @DeleteMapping("/deck/{deckId}/{slot}")
    public ResponseEntity<Void> clearSlot(@PathVariable int deckId, @PathVariable int slot) {
        int rows = service.clearSlot(deckId, slot);
        return rows > 0 ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // OPTIONAL: clear both slots
    @Operation(
            summary = "Clear all commander slots",
            description = "Removes all commanders from the deck (both slots)."
    )
    @ApiResponse(responseCode = "204", description = "Cleared")
    @DeleteMapping("/deck/{deckId}")
    public ResponseEntity<Void> clearAll(@PathVariable int deckId) {
        service.clearAll(deckId);
        return ResponseEntity.noContent().build();
    }

    // tiny DTO
    @Schema(description = "Batch list of commander card IDs (0–2)")
    public static class IdList {
        public List<Integer> cardIds;
    }
}
