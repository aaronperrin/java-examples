package com.afpx.exercises.cards.simple;

import java.util.Objects;

/**
 * A Card is used in a deck of cards primarily for playing games like Poker or Solitaire. Each card has a suit and
 * a rank. The suit represents the category of the card (i.e. Heart, Club, etc.) The rank represents the value
 * of the card (i.e. Ace, One, etc.)
 */
public class Card {
    /**
     * These variables are public final for convenience. May be refactored if property methods are necessary.
     */
    public final Rank rank;
    public final Suit suit;

    /**
     * Create a new card object using a specified rank and suit
     * @param rank the rank
     * @param suit the suit
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Ensures that two card objects with the same rank and suit are equal
     * @param o the object to compare this object against
     * @return true if the object is a Card and equals this card
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", suit=" + suit +
                '}';
    }
}
