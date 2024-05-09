package com.example.mycasinofx.Model.games.strategy_pattern;

/**
 * Implementation of the GameResultStrategy interface for displaying roulette game results.
 */
public class RouletteResultImpl implements GameResultStrategy {

    /**
     * Shows the game result for Roulette game based on the profit.
     * @param profit The profit from the game.
     */
    @Override
    public void showGameResult(double profit) {
        int res = CheckProfit.checkProfit(profit);
        if (res == 1){
            System.out.println("User won in Roulette Game " + profit);
        }
        else if (res == 0){
            System.out.println("User save his money in Roulette");
        }
        else {
            System.out.println("User lose in Roulette Game " + profit);
        }
    }
}


