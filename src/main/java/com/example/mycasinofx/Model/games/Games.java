package com.example.mycasinofx.Model.games;


/**
 * This abstract class represents common properties and methods for various games.
 */
public abstract class Games{
    /** The minimum stake allowed for the game. */
    final private static double MINIMUM_STAKE = 5;
    /** The maximum stake allowed for the game. */
    final private static double MAXIMUM_STAKE = 50000;
    /** The multiplier for a 2x win. */
    final private static int MULTIPLIER2x = 2;
    /** The multiplier for a 36x win. */
    final private static int MULTIPLIER36x = 36;


    /**
     * Retrieves the maximum stake allowed for the game.
     * @return The maximum stake allowed.
     */
    public static double getMaximumStake(){
        return MAXIMUM_STAKE;
    }

    /**
     * Retrieves the minimum stake allowed for the game.
     * @return The minimum stake allowed.
     */
    public static double getMinimumStake(){
        return MINIMUM_STAKE;
    }

    /**
     * Retrieves the multiplier for a 2x win.
     * @return The multiplier for a 2x win.
     */
    public static int getMULTIPLIER2x() {
        return MULTIPLIER2x;
    }

    /**
     * Retrieves the multiplier for a 36x win.
     * @return The multiplier for a 36x win.
     */
    public static int getMULTIPLIER36x() {
        return MULTIPLIER36x;
    }
}
