package com.afpx.exercises.cards.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Represents a 'poker-like' deck of cards. That is, it contains a ordered list of cards, initially un-shuffled.
 * Cards may be dealt by sequentially calling dealOneCard.
 */
public class Deck {
    private final List<Card> cards = new ArrayList<>(Rank.values().length * Suit.values().length);
    // Index to the virtual top card of the deck. As cards are dealt, the index is incremented.
    private int topCard = 0;

    /**
     * Creates a new deck of cards. Cards are initially sorted by Rank and Suit.
     */
    public Deck() {
        for(Rank r : Rank.values()) {
            for(Suit s : Suit.values()) {
                cards.add(new Card(r, s));
            }
        }
    }

    /**
     * Shuffles the cards using a simple algorithm. Probably needs more work depending on how this class will be used.
     * TODO: revise algorithm based on consumer requirements (e.g. does it need to be shuffled better?)
     */
    public void shuffle() {
        Random random = new Random();
        for(int i = 0; i < cards.size(); i++) {
            // just swap a card with another random card in the deck
            Card tmp = cards.get(i);
            int randomCardIndex = random.nextInt(cards.size());
            cards.set(i, cards.get(randomCardIndex));
            cards.set(randomCardIndex, tmp);
        }
        // reset the top card index
        topCard = 0;
    }

    /**
     * Returns the current top card of the deck
     * @return the top card, or nothing if all cards have been consumed
     */
    public Optional<Card> dealOneCard() {
        if(topCard >= cards.size()) {
            return Optional.empty();
        }
        return Optional.of(cards.get(topCard++));
    }
}
