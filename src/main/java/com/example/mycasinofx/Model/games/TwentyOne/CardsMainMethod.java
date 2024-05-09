package com.example.mycasinofx.Model.games.TwentyOne;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Interface for defining methods related to a standard deck of cards.
 */
public interface CardsMainMethod {
    /**
     * The total number of cards in the deck.
     */
    int CARD_AMOUNT = 36;

    /**
     * The suits of the cards in the deck.
     */
    String[] CARD_SUIT = {"hearts", "diamonds", "spades", "clubs"};

    /**
     * The values of the cards in the deck.
     */
    String[] CARD_VALUES = {"6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

    /**
     * The list of numeric card values in the deck.
     */
    ArrayList<String> CARD_NUMBERS = new ArrayList<>(Arrays.asList("6", "7", "8", "9", "10"));

    /**
     * Gets the numerical value of a card based on its type.
     * @param type The type of the card.
     * @return The numerical value of the card.
     */
    default int getValueCard(String type){
        if (CARD_NUMBERS.contains(type)){
            return Integer.parseInt(type);
        }

        switch (type){
            case "jack":
                return 3;
            case "queen":
                return 4;
            case "king":
                return 5;
            case "ace":
                return 1;
        }

        return 0;
    }

    /**
     * Shuffles the sequence of cards randomly.
     */
    void shuffleRandomSequence();

}
