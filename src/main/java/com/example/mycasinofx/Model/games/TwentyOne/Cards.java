package com.example.mycasinofx.Model.games.TwentyOne;

import java.util.*;

/**
 * Represents a deck of cards.
 * Implements the CardsMainMethod interface and the Iterator interface.
 */
public class Cards implements CardsMainMethod, Iterator<String[]> {

    /**
     * HashMap to store the card deck, with each card represented by its index and an array containing its suit and value.
     */
    private final HashMap<Integer, String[]> cardDeck;

    /**
     * List to store the random sequence of card indexes.
     */
    private final List<Integer> randomSequence;

    /**
     * Index to keep track of the current position in the card deck.
     */
    private int index = 0;

    /**
     * Constructor for the Cards class.
     * Initializes the card deck and sets up the random sequence.
     */
    public Cards(){
        cardDeck = new HashMap<>();
        randomSequence = new ArrayList<>();
        set_up();
        setRandomSequence();
    }

    /**
     * Sets up the initial card deck with all possible combinations of suits and values.
     */
    private void set_up(){
        int i = 0;
        for (String s : CARD_SUIT) {
            for (String cardValue : CARD_VALUES) {
                cardDeck.put(i, new String[] {s, cardValue} );
                i++;
            }
        }
    }

    /**
     * Initializes the random sequence of card indexes.
     */
    private void setRandomSequence(){
        for (int i = 0; i < CARD_AMOUNT; i++){
            randomSequence.add(i);
        }
    }

    /**
     * Shuffles the random sequence of card indexes.
     */
    @Override
    public void shuffleRandomSequence(){
        index = 0;
        Collections.shuffle(randomSequence);
    }

    /**
     * Checks if there are more cards in the deck.
     * @return true if there are more cards, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return index < CARD_AMOUNT;
    }


    /**
     * Retrieves the next card from the deck.
     * @return The suit and value of the next card as an array of strings.
     * @throws IndexOutOfBoundsException if there are no more cards in the deck.
     */
    @Override
    public String[] next() {
        if (hasNext()) {
            return cardDeck.get(randomSequence.get(index++));
        } else {
            throw new IndexOutOfBoundsException("No more cards in the deck.");
        }
    }
}
