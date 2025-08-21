package com.example.capstone_mtg_collection.service;

import com.example.capstone_mtg_collection.model.Card;
import com.example.capstone_mtg_collection.model.CommanderCard;
import com.example.capstone_mtg_collection.repository.CommanderCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class CommanderCardService {

    private static final Logger log = LoggerFactory.getLogger(CommanderCardService.class);

    private final CommanderCardRepository repo;

    public CommanderCardService(CommanderCardRepository repo) {
        this.repo = repo;
    }

    /* --------------------- Reads --------------------- */

    /** Raw rows (slot + cardId), useful for admin/debug. */
    public List<CommanderCard> getForDeck(int deckId) {
        Assert.isTrue(deckId > 0, "deckId must be > 0");
        return repo.findByDeckId(deckId);
    }

    /** Full Card objects for preview/cards UI. */
    public List<Card> findCommanderCards(int deckId) {
        Assert.isTrue(deckId > 0, "deckId must be > 0");
        return repo.findCommanderCards(deckId);
    }

    /* --------------------- Write helpers --------------------- */

    /**
     * Set both commanders at once (1–2 cards). Clears any previous entries, then
     * inserts cardIds[0] at slot 1 and (if present) cardIds[1] at slot 2.
     */
    public boolean setCommanders(int deckId, List<Integer> cardIds) {
        try {
            Assert.isTrue(deckId > 0, "deckId must be > 0");
            if (cardIds == null || cardIds.isEmpty()) {
                // no commanders sent – clear both
                repo.deleteByDeckId(deckId);
                return true;
            }
            if (cardIds.size() > 2) {
                throw new IllegalArgumentException("Commander format allows at most 2 commanders.");
            }

            // sanitize & dedupe
            Set<Integer> clean = new HashSet<>();
            for (Integer id : cardIds) {
                if (id != null && id > 0) clean.add(id);
            }
            if (clean.isEmpty()) {
                repo.deleteByDeckId(deckId);
                return true;
            }
            if (clean.size() > 2) {
                throw new IllegalArgumentException("Commander format allows at most 2 unique commanders.");
            }

            // clear then insert in slot order
            repo.deleteByDeckId(deckId);

            Integer[] arr = clean.toArray(new Integer[0]);
            // Slot 1
            repo.insert(deckId, arr[0], 1);
            // Slot 2 (optional)
            if (arr.length > 1) {
                repo.insert(deckId, arr[1], 2);
            }
            return true;
        } catch (Exception ex) {
            log.error("Failed to set commanders for deck {}", deckId, ex);
            return false;
        }
    }

    /**
     * Upsert a single slot (1 or 2).
     * @return number of affected rows
     */
    public int upsert(int deckId, int slot, int cardId) {
        Assert.isTrue(deckId > 0, "deckId must be > 0");
        Assert.isTrue(slot == 1 || slot == 2, "slot must be 1 or 2");
        Assert.isTrue(cardId > 0, "cardId must be > 0");

        CommanderCard existing = repo.findByDeckIdAndSlot(deckId, slot);
        if (existing == null) {
            return repo.insert(deckId, cardId, slot);
        } else {
            if (!Objects.equals(existing.getCardId(), cardId)) {
                existing.setCardId(cardId);
                return repo.update(existing);
            }
            return 0; // no change needed
        }
    }

    public int clearSlot(int deckId, int slot) {
        Assert.isTrue(deckId > 0, "deckId must be > 0");
        Assert.isTrue(slot == 1 || slot == 2, "slot must be 1 or 2");
        return repo.deleteByDeckIdAndSlot(deckId, slot);
    }

    public int clearAll(int deckId) {
        Assert.isTrue(deckId > 0, "deckId must be > 0");
        return repo.deleteByDeckId(deckId);
    }
}
