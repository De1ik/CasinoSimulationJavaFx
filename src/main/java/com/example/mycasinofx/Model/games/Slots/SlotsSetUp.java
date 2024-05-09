package com.example.mycasinofx.Model.games.Slots;


import com.example.mycasinofx.Model.games.GameSetUpInterface;
import com.example.mycasinofx.Model.games.Games;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the setup for the Slots game.
 * Extends the abstract class Games and implements the GameSetUpInterface.
 */
public class SlotsSetUp extends Games implements GameSetUpInterface {

    /**
     * Total amount of symbols in the array of all symbols.
     */
    private final int ALL_AMOUNT = 60;

    /**
     * Number of columns in the slots game.
     */
    private final int AMOUNT_COLUMNS = 3;

    /**
     * Number of elements 1 in the total array.
     */
    private final int AMOUNT_1 = 2; //1000
    /**
     * Number of elements 2 in the total array.
     */
    private final int AMOUNT_2 = 3; //500
    /**
     * Number of elements 3 in the total array.
     */
    private final int AMOUNT_3 = 6; //100
    /**
     * Number of elements 4 in the total array.
     */
    private final int AMOUNT_4 = 11; //25
    /**
     * Number of elements 5 in the total array.
     */
    private final int AMOUNT_5 = 38; //3

    /**
     * ArrayList representing the first column of the slots game.
     */
    private final ArrayList<Integer> column1;
    /**
     * ArrayList representing the second column of the slots game.
     */
    private final ArrayList<Integer> column2;
    /**
     * ArrayList representing the third column of the slots game.
     */
    private final ArrayList<Integer> column3;
    /**
     * ArrayList containing all columns of the slots game.
     */
    private final ArrayList<ArrayList<Integer>> generalArrayList;

    /**
     * Constructor for SlotsSetUp.
     * Initializes the setup for the slots game.
     */
    public SlotsSetUp() {
        column1 = new ArrayList<>();
        column2 = new ArrayList<>();
        column3 = new ArrayList<>();
        generalArrayList = new ArrayList<>();
    }

    /**
     * Sets the columns of the slots game.
     * Clears existing columns and generates new ones based on the predefined amounts of symbols.
     */
    public void setColumns() {


        column1.clear();
        column2.clear();
        column3.clear();


        generalArrayList.add(column1);
        generalArrayList.add(column2);
        generalArrayList.add(column3);


        for (int col = 0; col < AMOUNT_COLUMNS; col++) {

            for (int row = 0; row < AMOUNT_1; row++) {
                generalArrayList.get(col).add(1);
            }

            for (int row = 0; row < AMOUNT_2; row++) {
                generalArrayList.get(col).add(2);
            }

            for (int row = 0; row < AMOUNT_3; row++) {
                generalArrayList.get(col).add(3);
            }

            for (int row = 0; row < AMOUNT_4; row++) {
                generalArrayList.get(col).add(4);
            }

            for (int row = 0; row < AMOUNT_5; row++) {
                generalArrayList.get(col).add(5);
            }
        }

    }

    /**
     * Shuffles the sequence of symbols in each column of the slots game.
     * @return The shuffled columns.
     */
    public ArrayList<ArrayList<Integer>> shuffleSequence() {
        for (int col = 0; col < AMOUNT_COLUMNS; col++) {
            Collections.shuffle(generalArrayList.get(col));
        }
        return generalArrayList;
    }

    /**
     * Overrides the gameSetUp method to perform setup specific to Slots.
     * Sets the columns and shuffles the symbols.
     * @return The shuffled columns.
     */
    @Override
    public Object gameSetUp() {
        setColumns();


        return shuffleSequence();
    }

    /**
     * Resets the setup of the slots game.
     * Clears all columns and the general array list.
     */
    public void reset(){
        column1.clear();
        column2.clear();
        column3.clear();
        generalArrayList.get(0).clear();
        generalArrayList.get(1).clear();
        generalArrayList.get(2).clear();
        generalArrayList.clear();
    }

    /**
     * Gets the total amount of symbols in the game.
     * @return The total amount of symbols.
     */
    public int getALL_AMOUNT() {
        return ALL_AMOUNT;
    }

    /**
     * Gets the number of columns in the slots game.
     * @return The number of columns.
     */
    public int getAMOUNT_COLUMNS() {
        return AMOUNT_COLUMNS;
    }


    /**
     * Gets the general array list containing all columns of the slots game.
     * @return The general array list.
     */
    public ArrayList<ArrayList<Integer>> getGeneralArrayList() {
        return generalArrayList;
    }
}
