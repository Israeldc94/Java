// src/main/java/.../controller/CommanderCardController.java
package com.example.capstone_mtg_collection.controller;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.service.CommanderCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commander-cards") // <-- distinct base path, no conflicts
public class CommanderCardController {

    private final CommanderCardService service;

    public CommanderCardController(CommanderCardService service) {
        this.service = service;
    }

    // OPTIONAL helper: list commanders for a deck via this controller
    // FE can still use DeckController's GET /api/decks/{id}/commanders; keep one or both.
    @GetMapping("/deck/{deckId}")
    public ResponseEntity<List<Card>> listByDeck(@PathVariable int deckId) {
        return ResponseEntity.ok(service.findCommanderCards(deckId));
    }

    // OPTIONAL: set BOTH commanders at once (mirrors your DeckController POST if you want it here instead)
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
    @DeleteMapping("/deck/{deckId}/{slot}")
    public ResponseEntity<Void> clearSlot(@PathVariable int deckId, @PathVariable int slot) {
        int rows = service.clearSlot(deckId, slot);
        return rows > 0 ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // OPTIONAL: clear both slots
    @DeleteMapping("/deck/{deckId}")
    public ResponseEntity<Void> clearAll(@PathVariable int deckId) {
        service.clearAll(deckId);
        return ResponseEntity.noContent().build();
    }

    // tiny DTO
    public static class IdList {
        public List<Integer> cardIds;
    }
}
