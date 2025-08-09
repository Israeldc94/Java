package com.example.capstone_mtg_collection.repository;

import com.example.capstone_mtg_collection.model.Deck;

import java.util.List;

public interface DeckRepository {

    List<Deck> findAll();

    Deck findById(int id);

    int insert(Deck deck);

    int update(Deck deck);

    int deleteById(int id);
}
