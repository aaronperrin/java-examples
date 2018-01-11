/**
 * Provides classes to implement a basic Poker deck.
 *
 * Some assumptions:
 *
 * First, although one 'Deck' class could have been sufficient to implement all functionality, it is
 * assumed that the consumer of this package will want to have more granular types. Therefore, separate classes for
 * Card, Rank, and Suit have been provided.
 *
 * Also, this implementation is not thread-safe. For example, unpredictable behavior will result if a card is dealt
 * while in process of a shuffle.
 *
 * Finally, the requirements mention the deck to be a 'poker-like' deck, but in order to avoid 'gold-plating',
 * very little additional functionality has been provided. And, this deck shouldn't be used in a
 * real poker game.
 */
package com.afpx.exercises.cards.simple;