package com.example.capstone_mtg_collection.controller;

import com.example.capstone_mtg_collection.model.Deck;
import com.example.capstone_mtg_collection.service.DeckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
public class DeckController {

    private final DeckService service;

    public DeckController(DeckService service) {
        this.service = service;
    }

    @GetMapping
    public List<Deck> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> findById(@PathVariable int id) {
        Deck deck = service.findById(id);
        return (deck != null) ? ResponseEntity.ok(deck) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Deck deck) {
        boolean success = service.add(deck);
        return success ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Deck deck) {
        deck.setDeckId(id);
        boolean success = service.update(deck);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        boolean success = service.deleteById(id);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
