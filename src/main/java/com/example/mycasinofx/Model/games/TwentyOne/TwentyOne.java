package com.example.mycasinofx.Model.games.TwentyOne;


import com.example.mycasinofx.Model.games.GameInterface;
import com.example.mycasinofx.Model.games.Games;
import com.example.mycasinofx.Model.games.ResultGenericClass;
import com.example.mycasinofx.Model.Player;

import java.util.ArrayList;

/**
 * Represents a Twenty-One (Blackjack) game.
 * Extends the Games class and implements the GameInterface.
 */
public class TwentyOne extends Games implements GameInterface {

    /**
     * Instance of the Cards class for managing the deck of cards.
     */
    private final Cards cards;

    /**
     * ArrayList to store the player's cards.
     */
    private final ArrayList<String[]> playerCards;

    /**
     * ArrayList to store the bot's cards.
     */
    private final ArrayList<String[]> botCards;

    /**
     * Total value of the bot's cards.
     */
    private int botValue;

    /**
     * Total value of the player's cards.
     */
    private int playerValue;

    /**
     * Instance of the ResultGenericClass to store game results.
     */
    private final ResultGenericClass<Integer> resultGenericClass;


    /**
     * Constructor for the TwentyOne class.
     * Initializes the Cards instance, player's and bot's card lists, and resultGenericClass.
     */
    public TwentyOne() {
        playerCards = new ArrayList<>();
        botCards = new ArrayList<>();
        cards = new Cards();
        botValue = 0;
        playerValue = 0;
        resultGenericClass = (ResultGenericClass<Integer>) ResultGenericClass.getResult();
    }

    /**
     * Starts a new game of Twenty-One.
     * Resets the game state, shuffles the card deck, and deals cards to the player and bot.
     */
    public void play_game() {
        reset();
        cards.shuffleRandomSequence();
        getCardToHand(true);
        getCardToHand(true);
        getCardToHand(false);
        getCardToHand(false);
    }

    /**
     * Deals a card to either the player or the bot.
     * @param player True if dealing to the player, False if dealing to the bot.
     */
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

    /**
     * Attempts to deal a card to the bot.
     * Determines whether the bot should draw a card based on its current total value.
     * @return True if the bot drew a card, False otherwise.
     */
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

    /**
     * Retrieves the player's cards.
     * @return The ArrayList containing the player's cards.
     */
    public ArrayList<String[]> getPlayerCards() {
        return playerCards;
    }

    /**
     * Retrieves the bot's cards.
     * @return The ArrayList containing the bot's cards.
     */
    public ArrayList<String[]> getBotCards() {
        return botCards;
    }

    /**
     * Retrieves the total value of the bot's cards.
     * @return The total value of the bot's cards.
     */
    public int getBotValue() {
        return botValue;
    }

    /**
     * Retrieves the total value of the player's cards.
     * @return The total value of the player's cards.
     */
    public int getPlayerValue() {
        return playerValue;
    }

    /**
     * Checks the winner of the game based on the total values of the player's and bot's cards.
     * Updates player's balance and profit accordingly.
     */
    @Override
    public void checkWinner(){
        Player player = Player.getPlayer();
        if (getPlayerValue() > getBotValue() || getBotValue() > 21){
            player.setBalance(player.getBalance() + player.getCurrentStake() * 2);
            player.setProfit(player.getCurrentStake() * 2 - player.getCurrentStake());
            resultGenericClass.setResult(1);
        }
        else if(getPlayerValue() < getBotValue()){
            player.setProfit(-(player.getCurrentStake() * 2));
            resultGenericClass.setResult(2);
        }
        else{
            player.setProfit(0);
            player.setBalance(player.getBalance() + player.getCurrentStake());
            resultGenericClass.setResult(0);
        }
    }

    /**
     * Generates the result of the game.
     * This method is overridden from the GameInterface.
     */
    @Override
    public void generateResult() {
    }

    /**
     * Resets the game state by clearing the player's and bot's card lists, and resetting their values.
     */
    public void reset() {
        playerCards.clear();
        botCards.clear();
        botValue = 0;
        playerValue = 0;
    }
}
