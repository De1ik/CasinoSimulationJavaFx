package com.example.mycasinofx.Model.games.strategy_pattern;

/**
 * Provides a method to check the profit based on the difference in balance.
 */
public class CheckProfit {
    /**
     * Checks the profit based on the difference in balance.
     * @param differBalance The difference in balance.
     * @return 0 if the difference is 0, 1 if the difference is positive, -1 if the difference is negative.
     */
    static public int checkProfit(double differBalance){
        if (differBalance == 0) return 0;
        else if (differBalance > 0) return 1;
        else return -1;
    }

}
