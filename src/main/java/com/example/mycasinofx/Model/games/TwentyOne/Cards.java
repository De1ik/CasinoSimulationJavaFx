package com.example.mycasinofx.Model.games.TwentyOne;

import java.util.*;


public class Cards implements CardsMainMethod, Iterator<String[]> {

    private HashMap<Integer, String[]> cardDeck;
    private List<Integer> randomSequence;
    private int index = 0;

    public Cards(){
        cardDeck = new HashMap<>();
        randomSequence = new ArrayList<>();;
        set_up();
        setRandomSequence();
    }

    private void set_up(){
        int i = 0;
        for (String s : CARD_SUIT) {
            for (String cardValue : CARD_VALUES) {
                cardDeck.put(i, new String[] {s, cardValue} );
                i++;
            }
        }
    }

    private void setRandomSequence(){
        for (int i = 0; i < CARD_AMOUNT; i++){
            randomSequence.add(i);
        }
    }

    public void shuffleRandomSequence(){
        index = 0;
        Collections.shuffle(randomSequence);
    }


    @Override
    public boolean hasNext() {
        return index < CARD_AMOUNT;
    }

    @Override
    public String[] next() {
        if (hasNext()) {
            return cardDeck.get(randomSequence.get(index++));
        } else {
            throw new IndexOutOfBoundsException("No more cards in the deck.");
        }
    }

    public int getValueCard(String type){
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
}
