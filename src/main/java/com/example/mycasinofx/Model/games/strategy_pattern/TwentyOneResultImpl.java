package com.example.mycasinofx.Model.games.strategy_pattern;

/**
 * Implementation of the GameResultStrategy interface for displaying 21 game results.
 */
public class TwentyOneResultImpl implements GameResultStrategy {

    /**
     * Shows the game result for 21 game based on the profit.
     * @param profit The profit from the game.
     */
    @Override
    public void showGameResult(double profit) {
        int res = CheckProfit.checkProfit(profit);
        if (res == 1){
            System.out.println("Player win from dealer in 21 " + profit);
        }
        else if (res == 0){
            System.out.println("User save his money in 21");
        }
        else {
            System.out.println("Dealer get from player in 21: " + profit);
        }
    }
}
