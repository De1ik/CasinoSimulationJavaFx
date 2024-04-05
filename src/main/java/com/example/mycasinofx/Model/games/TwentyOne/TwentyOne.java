package com.example.mycasinofx.Model.games.TwentyOne;

import com.example.mycasinofx.Model.games.Games;

import java.util.ArrayList;

public class TwentyOne extends Games {

    private Cards cards;
    private ArrayList<String[]> playerCards = new ArrayList<>();
    private ArrayList<String[]> botCards = new ArrayList<>();

    private int botValue;
    private int playerValue;

    public TwentyOne() {
        cards = new Cards();
        botValue = 0;
        playerValue = 0;
    }


    public void play_game() {
        reset();
        cards.shuffleRandomSequence();
        getCardToHand(true);
        getCardToHand(true);
        getCardToHand(false);
        getCardToHand(false);
    }


    public void getCardToHand(boolean player) {
        String[] curCard = cards.next();
        int value = cards.getValueCard(curCard[1]);
        if (player) {
            playerValue += value;
            playerCards.add(new String[]{curCard[0], curCard[1], String.valueOf(value)});
        } else {
            botValue += value;
            botCards.add(new String[]{curCard[0], curCard[1], String.valueOf(value)});
        }
    }

    public boolean tryGetBotCard() {

        if (botValue >= 19 && botValue <= 21) {
            return false;
        }

        String[] curCard = cards.next();
        int value = cards.getValueCard(curCard[1]);

        if (botValue + value <= 21) {
            botValue += value;
            botCards.add(new String[]{curCard[0], curCard[1], String.valueOf(value)});
            return true;
        } else {
            int randomInt = (int) (Math.random() * 100);
            if (randomInt < 23) {
                botValue += value;
                botCards.add(new String[]{curCard[0], curCard[1], String.valueOf(value)});
                return true;
            }
            return false;
        }

    }

    public ArrayList<String[]> getPlayerCards() {
        return playerCards;
    }

    public ArrayList<String[]> getBotCards() {
        return botCards;
    }

    public int getBotValue() {
        return botValue;
    }

    public int getPlayerValue() {
        return playerValue;
    }

    public void reset() {
        playerCards.clear();
        botCards.clear();
        botValue = 0;
        playerValue = 0;
    }

    @Override
    public Object checkWinner() {
        return null;
    }

    @Override
    public Object generateResult() {
        return null;
    }
}
