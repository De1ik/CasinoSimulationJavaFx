package com.example.mycasinofx.Model.games.Roulette;

import com.example.mycasinofx.Model.games.GameInterface;
import com.example.mycasinofx.Model.games.GameSetUpInterface;
import com.example.mycasinofx.Model.games.ResultGenericClass;
import com.example.mycasinofx.Model.Player;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents the logic of the Roulette game.
 */
public class Roulette extends RouletteSetUp implements GameInterface {
    /** Flag indicating whether the game has started. */
    private boolean isGamed;

    /** Flag indicating whether an even stake has been set. */
    private boolean evenStakeSet;

    /** Flag indicating whether an odd stake has been set. */
    private boolean oddStakeSet;

    /** Flag indicating whether a red stake has been set. */
    private boolean redStakeSet;

    /** Flag indicating whether a black stake has been set. */
    private boolean blackStakeSet;

    /** Flag indicating whether a green stake has been set. */
    private boolean greenStakeSet;

    /** Flag indicating whether an exact number stake has been set. */
    private boolean exactNumber;

    /** The result number of the game. */
    private int resultNumber;

    /** Singleton instance of Roulette. */
    private static Roulette instanceRoulette;

    /** Array to store current stakes. */
    private final String[] curStakes;

    /** List to store exact numbers. */
    private ArrayList<Integer> exactNumberArray;

    /** Interface for setting up the game. */
    private GameSetUpInterface gameSetUpInterface;

    /** The player participating in the game. */
    private Player player;

    /** The generic class for storing game results. */
    private ResultGenericClass<Integer> resultGenericClass;

    /**
     * Private constructor to enforce singleton pattern and setting data .
     */
    private Roulette() {
        gameSetUpInterface = new RouletteSetUp();
        instanceRoulette = null;
        isGamed = false;
        evenStakeSet = false;
        curStakes = new String[6];
        gameSetUpInterface.gameSetUp();
        resetCurStakes();
        player = Player.getPlayer();
        exactNumberArray = new ArrayList<>();
        resultGenericClass = (ResultGenericClass<Integer>) ResultGenericClass.getResult();

    }


    /**
     * Retrieves the singleton instance of Roulette.
     * @return The singleton instance of Roulette.
     */
    public static Roulette getRoulette() {
        if (instanceRoulette == null) {
            instanceRoulette = new Roulette();
        }
        return instanceRoulette;
    }

    /**
     * Overrides the method to check the winner of the roulette game.
     * It checks various conditions such as color, exact number, and even/odd bets
     * to determine if the player wins and updates the player's balance accordingly.
     */
    @Override
    public void checkWinner()  {

        int curGeneration = resultGenericClass.getResultValue();

        //check the colours
        String color = RouletteSetUp.getColours(curGeneration);
        if (isRedStakeSet() && color.equals("Red")) {
            player.setBalance(player.getBalance() + getMULTIPLIER2x() * player.getCurrentStake());
        }
        if (isBlackStakeSet() && color.equals("Black")) {
            player.setBalance(player.getBalance() + getMULTIPLIER2x() * player.getCurrentStake());
        }
        if (isGreenStakeSet() && color.equals("Green")) {
            player.setBalance(player.getBalance() + getMULTIPLIER36x() * player.getCurrentStake());
        }

        //check if some of exact number won
        if (checkInExactNumberArray(curGeneration)) {
            player.setBalance(player.getBalance() + getMULTIPLIER36x() * player.getCurrentStake());
        }

        //check even / odd
        if (isEvenStakeSet() && (curGeneration % 2 == 0)) {
            player.setBalance(player.getBalance() + player.getCurrentStake() * getMULTIPLIER2x());
        }
        if (isOddStakeSet() && (curGeneration % 2 == 1)) {
            player.setBalance(player.getBalance() + player.getCurrentStake() * getMULTIPLIER2x());
        }
    }

    /**
     * Overrides the method to generate the result of the roulette game.
     * Sets the game status to true, generates a random result number,
     * and sets the result in the resultGenericClass.
     */
    @Override
    public void generateResult() {
        this.setGamed(true);
        this.resultNumber = (int) (Math.random() * 37);
        resultGenericClass.setResult((int) (Math.random() * 37));
    }


    /**
     * Gets the current stake at the specified index.
     * @param index The index of the stake.
     * @return The current stake at the specified index.
     */
    public String getCurStakes(int index) {
        return curStakes[index];
    }

    /**
     * Sets the current stake at the specified index.
     * @param curStakes The stake to set.
     * @param index     The index at which to set the stake.
     */
    public void setCurStakes(String curStakes, int index) {
        this.curStakes[index] = curStakes;
    }

    /**
     * Resets all current stakes to null.
     */
    public void resetCurStakes() {
        Arrays.fill(this.curStakes, null);
    }

    /**
     * Checks if the exact number array is empty.
     * @return true if the exact number array is empty, otherwise false.
     */
    public boolean isEmptyExactNumberArray() {
        return exactNumberArray.isEmpty();
    }


    /**
     * Checks if a provided number (index) in the exact number array.
     * @param number The number to check.
     * @return true if the number is present, otherwise false.
     */
    public boolean checkInExactNumberArray(int number) {
        return exactNumberArray.contains(number);
    }

    /**
     * Adds a number (index) to the exact number array.
     * @param number The number to add.
     */
    public void addExactNumberArray(int number) {
        exactNumberArray.add(number);
    }

    /**
     * Removes a number (index) from the exact number array.
     * @param number The number to remove.
     */
    public void removeExactNumberArray(int number) {
        try {
            exactNumberArray.remove((Integer) number);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Clears all numbers from the exact number array.
     */
    public void clearExactNumberArray() {
        exactNumberArray.clear();
    }

    /**
     * Generates a string containing all numbers in the exact number array.
     * @return A string containing all selected numbers.
     */
    public String getStringExactNumber() {
        String res = "";
        for (Integer value : exactNumberArray) {
            res += " " + value + " ";
        }
        return res;
    }


    /**
     * Returns the stake to the player before the game ends, if the game has not started.
     * The stake is returned based on the types of bets made by the player.
     * If the player bet on even, odd, red, black, or green, and the game has not started,
     * the corresponding stake amount is returned to the player's balance.
     * Additionally, if the player bet on exact numbers and the game has not started,
     * the total stake amount for exact numbers is returned to the player's balance.
     */
    public void returnStakeBeforeEnd() {
        if (!isGamed()) {

            CheckTypeOfStake lambda_checking = (boolean res) -> {
                if (res){
                    player.setBalance(player.getBalance() + player.getCurrentStake());
                }
            };

            lambda_checking.checking(isEvenStakeSet());
            lambda_checking.checking(isOddStakeSet());
            lambda_checking.checking(isRedStakeSet());
            lambda_checking.checking(isBlackStakeSet());
            lambda_checking.checking(isGreenStakeSet());

            if (!isEmptyExactNumberArray()) {
                double amount = exactNumberArray.size() * player.getCurrentStake();
                player.setBalance(player.getBalance() + amount);
            }
        }
        setGamed(false);
    }

    /**
     * Checks if the player can start the game based on whether they placed at least one stake.
     * @return true if the player can start the game, otherwise false.
     */
    public boolean checkStartGame() {
        return evenStakeSet || oddStakeSet || redStakeSet || blackStakeSet || greenStakeSet || exactNumber;
    }

    /**
     * Resets all boolean stake flags to false.
     */
    public void resetBooleanStake() {
        evenStakeSet = false;
        redStakeSet = false;
        blackStakeSet = false;
        greenStakeSet = false;
        oddStakeSet = false;
        exactNumber = false;
    }

    //----------------------------------------Getters/Setters-------------------------------------------------
    /**
     * Checks if the game has started.
     * @return true if the game has started, otherwise false.
     */
    public boolean isGamed() {
        return isGamed;
    }

    /**
     * Sets the status of the game.
     * @param gamed The status of the game.
     */
    public void setGamed(boolean gamed) {
        isGamed = gamed;
    }

    /**
     * Checks if the player bet on even numbers.
     * @return true if the player bet on even numbers, otherwise false.
     */
    public boolean isEvenStakeSet() {
        return evenStakeSet;
    }

    /**
     * Sets player bet on even numbers.
     * @param evenStakeSet The flag indicating if the player bet on even numbers.
     */
    public void setEvenStakeSet(boolean evenStakeSet) {
        this.evenStakeSet = evenStakeSet;
    }


    /**
     * Checks if the player bet on odd numbers.
     * @return true if the player bet on odd numbers, otherwise false.
     */
    public boolean isOddStakeSet() {
        return oddStakeSet;
    }

    /**
     * Sets the player bet on odd numbers.
     * @param oddStakeSet The flag indicating if the player bet on odd numbers.
     */
    public void setOddStakeSet(boolean oddStakeSet) {
        this.oddStakeSet = oddStakeSet;
    }

    /**
     * Checks if the player bet on red.
     * @return true if the player bet on red, otherwise false.
     */
    public boolean isRedStakeSet() {
        return redStakeSet;
    }

    /**
     * Sets the player bet on red.
     * @param redStakeSet The flag indicating if the player bet on red.
     */
    public void setRedStakeSet(boolean redStakeSet) {
        this.redStakeSet = redStakeSet;
    }

    /**
     * Checks if the player bet on black.
     * @return true if the player bet on black, otherwise false.
     */
    public boolean isBlackStakeSet() {
        return blackStakeSet;
    }

    /**
     * Sets the player bet on black.
     * @param blackStakeSet The flag indicating if the player bet on black.
     */
    public void setBlackStakeSet(boolean blackStakeSet) {
        this.blackStakeSet = blackStakeSet;
    }

    /**
     * Checks if the player bet on green.
     * @return true if the player bet on green, otherwise false.
     */
    public boolean isGreenStakeSet() {
        return greenStakeSet;
    }

    /**
     * Sets the player bet on green.
     * @param greenStakeSet The flag indicating if the player bet on green.
     */
    public void setGreenStakeSet(boolean greenStakeSet) {
        this.greenStakeSet = greenStakeSet;
    }

    /**
     * Checks if the player bet on exact numbers.
     * @return true if the player bet on exact numbers, otherwise false.
     */
    public boolean isExactNumber() {
        return exactNumber;
    }

    /**
     * Sets the player bet on exact numbers.
     * @param exactNumber The flag indicating if the player bet on exact numbers.
     */
    public void setExactNumber(boolean exactNumber) {
        this.exactNumber = exactNumber;
    }
}
