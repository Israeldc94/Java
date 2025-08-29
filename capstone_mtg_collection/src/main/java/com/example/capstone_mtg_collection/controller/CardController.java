package com.example.capstone_mtg_collection.controller;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin

@Tag(name = "Cards", description = "Manage card collection")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }


    @Operation(
            summary = "List all cards in collection",
            description = "Returns the entire collection. Client-side search/sort/pagination is applied in the UI."
    )
    @ApiResponse(
            responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Card.class)))
    )
    @GetMapping
    public List<Card> getAllCards() {
        return service.getAll();
    }

    @Operation(summary = "Create a card")
    @ApiResponse(responseCode = "201", description = "Created",
        content = @Content(schema = @Schema(implementation = Card.class)))
    @PostMapping
    public ResponseEntity<Void> addCard(@RequestBody Card card) {
        service.add(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Get a single card",
            description = "Fetch a card by its identifier."
    )
    @ApiResponse(
            responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = Card.class))
    )
    @ApiResponse(responseCode = "404", description = "Card not found.")
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCard(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }


    @Operation(
            summary = "Update a card",
            description = "Replaces all updatable fields of a card."
    )
    @ApiResponse(responseCode = "204", description = "Updated, no content.")
    @ApiResponse(responseCode = "400", description = "Invalid payload.")
    @ApiResponse(responseCode = "404", description = "Card not found.")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCard(@PathVariable int id, @RequestBody Card card) {
        service.update(id, card);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete a card",
            description = "Removes a card from the collection."
    )
    @ApiResponse(responseCode = "204", description = "Deleted, no content.")
    @ApiResponse(responseCode = "404", description = "Card not found.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Search cards by name",
            description = "Case-insensitive contains search over the `name` field."
    )
    @ApiResponse(
            responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Card.class)))
    )
    @ApiResponse(responseCode = "204", description = "No matches found.")
    @GetMapping("/search")
    public ResponseEntity<List<Card>> searchCards(@RequestParam String name) {
        List<Card> matches = service.searchByName(name);
        return matches.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(matches);
    }
}
