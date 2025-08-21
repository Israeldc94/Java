package com.example.capstone_mtg_collection.service;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.model.Deck;
import com.example.capstone_mtg_collection.model.DeckCard;
import com.example.capstone_mtg_collection.repository.CommanderCardRepository;
import com.example.capstone_mtg_collection.repository.DeckCardRepository;
import com.example.capstone_mtg_collection.repository.DeckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DeckService {

    private static final Logger log = LoggerFactory.getLogger(DeckService.class);

    private static final Set<String> ALLOWED_FORMATS = Set.of(
            "Commander", "Modern", "Standard", "Pioneer", "Casual"
    );

    private final DeckRepository deckRepo;
    private final CommanderCardRepository commanderRepo;
    private final DeckCardRepository deckCardRepo;

    public DeckService(
            DeckRepository deckRepo,
            CommanderCardRepository commanderRepo,
            DeckCardRepository deckCardRepo
    ) {
        this.deckRepo = deckRepo;
        this.commanderRepo = commanderRepo;
        this.deckCardRepo = deckCardRepo;
    }

    /* ------------------------- Queries ------------------------- */

    public List<Deck> findAll() {
        return deckRepo.findAll();
    }

    public Deck findById(int id) {
        if (id <= 0) return null;
        return deckRepo.findById(id);
    }

    /* ------------------------- Create / Update / Delete ------------------------- */

    /**
     * Create a deck and return its generated id (0 on failure).
     */
    public int addAndReturnId(Deck deck) {
        try {
            sanitize(deck);
            String error = validate(deck, /*creating=*/true);
            if (error != null) {
                log.warn("Validation failed creating deck: {}", error);
                return 0;
            }
            int id = deckRepo.insertAndReturnId(deck);
            if (id <= 0) {
                log.error("Deck insert returned non-positive id");
            }
            return id;
        } catch (Exception ex) {
            log.error("Failed to create deck", ex);
            return 0;
        }
    }

    public boolean update(Deck deck) {
        try {
            if (deck.getDeckId() <= 0) return false;
            sanitize(deck);
            String error = validate(deck, /*creating=*/false);
            if (error != null) {
                log.warn("Validation failed updating deck {}: {}", deck.getDeckId(), error);
                return false;
            }
            return deckRepo.update(deck) > 0;
        } catch (Exception ex) {
            log.error("Failed to update deck {}", deck.getDeckId(), ex);
            return false;
        }
    }

    public boolean deleteById(int id) {
        try {
            if (id <= 0) return false;
            return deckRepo.deleteById(id) > 0;
        } catch (Exception ex) {
            log.error("Failed to delete deck {}", id, ex);
            return false;
        }
    }

    /* ------------------------- Commanders & Cards ------------------------- */

    /**
     * Replace commanders for a deck. Enforces 1–2 ids.
     * Also checks that the deck format is Commander.
     */
    @Transactional
    public boolean addCommanders(int deckId, List<Integer> cardIds) {
        try {
            if (deckId <= 0) return false;
            Deck deck = deckRepo.findById(deckId);
            if (deck == null) return false;

            // Must be Commander format
            if (!"Commander".equalsIgnoreCase(nullToEmpty(deck.getFormat()))) {
                log.warn("Deck {} is not Commander; refusing to add commanders", deckId);
                return false;
            }

            if (cardIds == null || cardIds.isEmpty() || cardIds.size() > 2) {
                log.warn("Deck {} commanders must be 1–2 ids; got {}", deckId, cardIds);
                return false;
            }

            // De-dup, keep first two
            Set<Integer> unique = new HashSet<>(cardIds);
            if (unique.isEmpty()) return false;

            // Clear existing and re-insert with slots 1..n
            commanderRepo.deleteByDeckId(deckId);
            int slot = 1;
            for (Integer cardId : unique) {
                if (cardId == null || cardId <= 0) continue;
                if (slot > 2) break;
                commanderRepo.insert(deckId, cardId, slot);
                slot++;
            }
            return slot > 1; // at least one inserted
        } catch (Exception ex) {
            log.error("Failed to add commanders to deck {}", deckId, ex);
            return false;
        }
    }

    /**
     * Add many cards to a deck. Empty list is allowed (no-op true).
     */
    @Transactional
    public boolean addCards(int deckId, List<Integer> cardIds) {
        try {
            if (deckId <= 0) return false;
            if (cardIds == null || cardIds.isEmpty()) return true; // nothing to do

            final int quantity = 1; // default quantity for this variant

            final long requested = cardIds.stream()
                    .filter(id -> id != null && id > 0)
                    .count();
            if (requested == 0) return true;

            int ok = 0;
            for (Integer cardId : cardIds) {
                if (cardId == null || cardId <= 0) continue;
                int newId = deckCardRepo.insert(deckId, cardId, quantity);
                if (newId > 0) ok++;
            }
            return ok == requested;
        } catch (Exception ex) {
            log.error("Failed to add cards to deck {}", deckId, ex);
            return false;
        }
    }
    public List<Card> findCommanderCards(int deckId) {
        try {
            return commanderRepo.findCommanderCards(deckId);
        } catch (Exception ex) {
            log.error("findCommanderCards failed for deck {}", deckId, ex);
            return Collections.emptyList();
        }
    }

    public List<Card> findDeckCards(int deckId) {
        try {
            return deckCardRepo.findByDeckId(deckId);
        } catch (Exception ex) {
            log.error("findDeckCards failed for deck {}", deckId, ex);
            return Collections.emptyList();
        }
    }

    /* ------------------------- Helpers ------------------------- */

    private void sanitize(Deck d) {
        if (d == null) return;
        if (d.getName() != null) d.setName(d.getName().trim());
        if (d.getFormat() != null) d.setFormat(d.getFormat().trim());
        // field is called "description" in your model but column is notes — map/alias in repo or mapper
        if (d.getNotes() != null) d.setDescription(d.getNotes().trim());
    }

    /**
     * @return error message or null if OK.
     */
    private String validate(Deck d, boolean creating) {
        if (d == null) return "deck is required";
        if (isBlank(d.getName())) return "name is required";
        if (isBlank(d.getFormat())) return "format is required";
        if (!ALLOWED_FORMATS.contains(d.getFormat())) {
            return "format must be one of " + ALLOWED_FORMATS;
        }
        // Optional: length limits
        if (d.getName().length() > 100) return "name too long";
        if (d.getNotes() != null && d.getNotes().length() > 1000) return "description too long";
        if (!creating && d.getDeckId() <= 0) return "deckId is required for update";
        return null;
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }
}
