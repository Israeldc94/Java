package com.example.capstone_mtg_collection.repository;

import com.example.capstone_mtg_collection.model.Card;
import java.util.List;

public interface CardRepository {

    List<Card> findAll();

    Card findById(int id);

    int insert(Card card);

    int update(Card card);

    int deleteById(int id);

    public List<Card> findByNameContainingIgnoreCase(String keyword);
}
