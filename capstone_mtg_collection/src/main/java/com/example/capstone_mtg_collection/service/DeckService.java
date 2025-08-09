package com.example.capstone_mtg_collection.service;

import com.example.capstone_mtg_collection.model.Deck;
import com.example.capstone_mtg_collection.repository.DeckRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

    private final DeckRepository repo;

    public DeckService(DeckRepository repository) {
        this.repo = repository;
    }


    public List<Deck> findAll() {
        return repo.findAll();
    }

    public Deck findById(int id) {
        return repo.findById(id);
    }

    public boolean add(Deck deck) {
        return repo.insert(deck) > 0;
    }

    public boolean update(Deck deck) {
        return repo.update(deck) > 0;
    }

    public boolean deleteById(int id) {
        return repo.deleteById(id) > 0;
    }
}
