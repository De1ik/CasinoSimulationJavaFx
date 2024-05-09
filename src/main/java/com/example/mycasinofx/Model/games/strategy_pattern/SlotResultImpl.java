package com.example.mycasinofx.Model.games.strategy_pattern;

/**
 * Implementation of the GameResultStrategy interface for displaying slot game results.
 */
public class SlotResultImpl implements GameResultStrategy {

    /**
     * Shows the game result for Slot game based on the profit.
     * @param profit The profit from the game.
     */
    @Override
    public void showGameResult(double profit) {
        int res = CheckProfit.checkProfit(profit);
        if (res == 1){
            System.out.println("Slot game was ended with win: " + profit);
        }
        else if (res == 0){
            System.out.println("User save his money in Slot");
        }
        else {
            System.out.println("Slot game was ended with lose: " + profit);
        }
    }
}
