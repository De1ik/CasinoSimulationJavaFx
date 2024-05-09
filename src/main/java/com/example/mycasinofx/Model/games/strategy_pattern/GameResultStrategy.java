package com.example.mycasinofx.Model.games.strategy_pattern;


/**
 * Interface for defining strategies to show game results.
 */
public interface GameResultStrategy {

    /**
     * Shows the game result based on the profit.
     * @param profit The profit from the game.
     */
    public void showGameResult(double profit);
}
