package com.example.mycasinofx.Model.games.Roulette;

/**
 * This interface represents a lambda expression for checking the type of stake.
 */
public interface CheckTypeOfStake {
    /**
     * Before leaving the game, there is a check of what bets the user has made, which will return the money for unplayed bets.
     * @param res The boolean result indicating the type of stake.
     */
    void checking(boolean res);
}
