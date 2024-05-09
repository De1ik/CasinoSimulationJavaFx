package com.example.mycasinofx.Model.games.Roulette;



import com.example.mycasinofx.Model.games.GameSetUpInterface;
import com.example.mycasinofx.Model.games.Games;

import java.util.HashMap;


/**
 * Represents the setup for the game of Roulette.
 * Extends the abstract class Games and implements the GameSetUpInterface.
 */
public class RouletteSetUp extends Games implements GameSetUpInterface {
    /**
     * HashMap to store the mapping of numbers to colours on the roulette wheel.
     */
    private static HashMap<Integer, String> colours;

    /**
     * Constructor for RouletteSetUp.
     * Initializes the HashMap of colours and performs game setup.
     */
    public RouletteSetUp(){
        colours = new HashMap<>();
        gameSetUp();
    }

    /**
     * Sets the colours for the roulette wheel.
     */
    public void setColours(){
        colours.put(0, "Green");
        colours.put(1, "Black");
        colours.put(2, "Red");
        colours.put(3, "Black");
        colours.put(4, "Red");
        colours.put(5, "Black");
        colours.put(6, "Red");
        colours.put(7, "Red");
        colours.put(8, "Black");
        colours.put(9, "Red");
        colours.put(10, "Black");
        colours.put(11, "Red");
        colours.put(12, "Black");
        colours.put(13, "Black");
        colours.put(14, "Red");
        colours.put(15, "Black");
        colours.put(16, "Red");
        colours.put(17, "Black");
        colours.put(18, "Red");
        colours.put(19, "Red");
        colours.put(20, "Black");
        colours.put(21, "Red");
        colours.put(22, "Black");
        colours.put(23, "Red");
        colours.put(24, "Black");
        colours.put(25, "Black");
        colours.put(26, "Red");
        colours.put(27, "Black");
        colours.put(28, "Red");
        colours.put(29, "Black");
        colours.put(30, "Red");
        colours.put(31, "Red");
        colours.put(32, "Black");
        colours.put(33, "Red");
        colours.put(34, "Black");
        colours.put(35, "Red");
        colours.put(36, "Black");
    }

    /**
     * Gets the colour for the specified number on the roulette wheel.
     * @param number The number on the roulette wheel.
     * @return The colour corresponding to the number.
     */
    public static String getColours(int number){
        return colours.get(number);
    }

    /**
     * Overrides the gameSetUp method to perform setup specific to Roulette.
     * Sets the colours for the roulette wheel.
     * @return null.
     */
    @Override
    public Object gameSetUp() {
        setColours();
        return null;
    }
}
