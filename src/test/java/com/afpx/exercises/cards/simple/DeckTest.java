package com.afpx.exercises.cards.simple;

import org.junit.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class DeckTest {
    @Test
    public void newDeckHasTopCard() throws Exception {
        Deck d = new Deck();
        Optional<Card> topCard = d.dealOneCard();
        assertTrue("Top card of deck should exist", topCard.isPresent());
    }

    @Test
    public void newDeckHasAllCards() throws Exception {
        Deck d = new Deck();
        Set<Card> testSet = new HashSet<>();
        Card topCard = d.dealOneCard().orElse(null);
        int cardCount = 0;
        while(topCard != null) {
            cardCount++;
            assertTrue("Top card should not have been encountered yet", testSet.add(topCard));
            topCard = d.dealOneCard().orElse(null);
        }
        assertEquals("52 cards should have been present", 52, cardCount);
    }

    @Test
    public void twoNewDecksHaveSameCardOrder() throws Exception {
        Deck d1 = new Deck();
        Deck d2 = new Deck();
        for (int i = 0; i < 52; i++) {
            assertTrue("Two new decks should have same cards and order",
                    d1.dealOneCard().get().equals(d2.dealOneCard().get()));
        }
    }

    @Test
    public void shuffledDeckHasDifferentCardOrder() throws Exception {
        Deck initial = new Deck();
        Deck shuffled = new Deck();
        shuffled.shuffle();

        boolean isSameOrder = true;
        for (int i = 0; i < 52; i++) {
            isSameOrder = shuffled.dealOneCard().get().equals(initial.dealOneCard().get());
            if(!isSameOrder) {
                break;
            }
        }

        assertFalse("A shuffled deck should have a different order than an un-shuffled deck", isSameOrder);
    }
}