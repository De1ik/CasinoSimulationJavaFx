package com.example.mycasinofx.Model.games;

public abstract class Games implements GameInterface{
    final private static double MINIMUM_STAKE = 5;
    final private static double MAXIMUM_STAKE = 50000;
    final private static int MULTIPLIER2x = 2;
    final private static int MULTIPLIER3x = 3;
    final private static int MULTIPLIER5x = 5;
    final private static int MULTIPLIER36x = 36;



    public static double getMaximumStake(){
        return MAXIMUM_STAKE;
    }

    public static double getMinimumStake(){
        return MINIMUM_STAKE;
    }

    public static int getMULTIPLIER2x() {
        return MULTIPLIER2x;
    }

    public static int getMULTIPLIER36x() {
        return MULTIPLIER36x;
    }
}
