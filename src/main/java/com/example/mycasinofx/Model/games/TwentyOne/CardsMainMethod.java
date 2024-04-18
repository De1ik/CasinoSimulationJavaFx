package com.example.mycasinofx.Model.games.TwentyOne;

import java.util.ArrayList;
import java.util.Arrays;

public interface CardsMainMethod {
    int CARD_AMOUNT = 36;
    String[] CARD_SUIT = {"hearts", "diamonds", "spades", "clubs"};
    String[] CARD_VALUES = {"6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
    ArrayList<String> CARD_NUMBERS = new ArrayList<>(Arrays.asList("6", "7", "8", "9", "10"));
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

    void shuffleRandomSequence();

}
