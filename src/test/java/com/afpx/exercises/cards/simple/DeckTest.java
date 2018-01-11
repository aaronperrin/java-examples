package com.afpx.exercises.cards.simple;

import org.junit.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class DeckTest {

    /**
     * A new deck should have 52 cards
     */
    @Test
    public void newDeckHas52Cards() throws Exception {
        Deck d = new Deck();
        for(int i = 0; i < 52; i++) {
            assertTrue("A deck should have 52 cards", d.dealOneCard().isPresent());
        }
    }

    /**
     * A shuffled deck should have 52 cards
     */
    @Test
    public void shuffledDeckHas52Cards() throws Exception {
        Deck d = new Deck();
        d.shuffle();
        for(int i = 0; i < 52; i++) {
            assertTrue("A deck should have 52 cards", d.dealOneCard().isPresent());
        }
    }

    /**
     * After a deck is consumed, all further deals should not return a card
     */
    @Test
    public void noCardIsDealtAfter52() throws Exception {
        Deck d = new Deck();
        for(int i = 0; i < 52; i++) {
            d.dealOneCard();
        }
        assertFalse("No card should be returned when all cards have been dealt", d.dealOneCard().isPresent());
    }

    /**
     * After deck is used and then reshuffled, 52 cards are in shuffled deck
     */
    @Test
    public void deckHas52CardsAfterReshuffle() throws Exception {
        // Start with a new deck, shuffle it, and deal all cards.
        Deck d = new Deck();
        for(int i = 0; i < 52; i++) {
            d.dealOneCard();
        }
        // Reshuffle, and check for 52 cards
        d.shuffle();
        for(int i = 0; i < 52; i++) {
            assertTrue("A reshuffled deck should have 52 cards", d.dealOneCard().isPresent());
        }
    }

    /**
     * Cards of all rank and suit exist in the deck
     */
    @Test
    public void newDeckHasAllCards() throws Exception {
        Deck d = new Deck();
        // Go through each card in the deck. None of the cards should repeat
        Set<Card> testSet = new HashSet<>();
        Card card = d.dealOneCard().orElse(null);
        int cardCount = 0;
        while(card != null) {
            cardCount++;
            assertTrue("There should not be duplicate cards in the deck", testSet.add(card));
            card = d.dealOneCard().orElse(null);
        }
        assertEquals("52 cards should be present", 52, cardCount);
    }

    /**
     * A new deck is always initialized in the same state. Therefore, two new decks should have same order.
     */
    @Test
    public void twoNewDecksHaveSameCardOrder() throws Exception {
        Deck d1 = new Deck();
        Deck d2 = new Deck();
        for (int i = 0; i < 52; i++) {
            assertTrue(
                    "Two new decks should have same cards and order",
                    d1.dealOneCard().get().equals(d2.dealOneCard().get())
            );
        }
    }

    /**
     * A shuffled deck should have a different order
     */
    @Test
    public void shuffledDeckVariesFromUnshuffled() throws Exception {
        Deck initial = new Deck();
        Deck shuffled = new Deck();
        shuffled.shuffle();

        boolean isSameOrder = true;
        for (int i = 0; i < 52; i++) {
            isSameOrder = shuffled.dealOneCard().get().equals(initial.dealOneCard().get());
            if(!isSameOrder) {
                // break if the decks are verified to be different
                break;
            }
        }

        assertFalse("A shuffled deck should have a different order than an un-shuffled deck", isSameOrder);
    }

    /**
     * Two different shuffled decks should have different order
     */
    @Test
    public void twoShuffledDecksAreDifferent() throws Exception {
        Deck d1 = new Deck();
        Deck d2 = new Deck();
        d1.shuffle();
        d2.shuffle();

        boolean isSameOrder = true;
        for (int i = 0; i < 52; i++) {
            isSameOrder = d1.dealOneCard().get().equals(d2.dealOneCard().get());
            if(!isSameOrder) {
                // break if the decks are verified to be different
                break;
            }
        }

        assertFalse("Two shuffled decks should have different order", isSameOrder);
    }
}