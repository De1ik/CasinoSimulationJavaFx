package com.example.mycasinofx.Model.games.Slots;

import com.example.mycasinofx.Model.games.GameInterface;
import com.example.mycasinofx.Model.games.ResultGenericClass;


import java.util.ArrayList;


/**
 * Represents the Slots game.
 * Extends the SlotsSetUp class and implements the GameInterface.
 */
public class Slots extends SlotsSetUp implements GameInterface {


    /**
     * Number of scrolls for visual effect.
     */
    private final int ROUNDS = 50;
    /**
     * ArrayList representing the starting index for each column.
     */
    private final ArrayList<Integer> startIndex;
    /**
     * ArrayList representing the finishing index for each column.
     */
    private final ArrayList<Integer> finishIndex;
    /**
     * ArrayList containing the general array of symbols for each column.
     */
    private ArrayList<ArrayList<Integer>> generalArray;
    /**
     * Singleton instance of the Slots class.
     */
    public static Slots instanceSlots;
    /**
     * ResultGenericClass instance for storing game results.
     */
    private final ResultGenericClass<Integer> resultGenericClass;

    /**
     * Private constructor for the Slots class.
     * Initializes the starting index and finishing index ArrayLists.
     * Initializes the ResultGenericClass instance.
     */
    private Slots() {
        instanceSlots = null;
        startIndex = new ArrayList<>();
        finishIndex = new ArrayList<>();
        generalArray = null;
        resultGenericClass = (ResultGenericClass<Integer>) ResultGenericClass.getResult();
    }

    /**
     * Retrieves the singleton instance of the Slots class.
     * If the instance does not exist, it creates a new one.
     * @return The singleton instance of the Slots class.
     */
    public static Slots getSlots() {
        if (instanceSlots == null) {
            instanceSlots = new Slots();
        }
        return instanceSlots;
    }


    /**
     * Checks for winning combinations in the slots game.
     * If the general array size is three (the number of columns == 3), it retrieves symbols from each column
     * and checks for winning combinations using WinnerCombinationCheckingFor3 method.
     * Sets the result in the ResultGenericClass instance.
     */
    @Override
    public void checkWinner() {
        if (getGeneralArrayList().size() == 3) {
            int numb1, numb2, numb3;
            numb1 = getGeneralArrayList().get(0).get(finishIndex.get(0));
            numb2 = getGeneralArrayList().get(1).get(finishIndex.get(1));
            numb3 = getGeneralArrayList().get(2).get(finishIndex.get(2));
            resultGenericClass.setResult(WinnerCombination.WinnerCombinationCheckingFor3(numb1, numb2, numb3));
        }
        else resultGenericClass.setResult(-1);
    }


    /**
     * Generates the result of a slots game.
     * Calls the gameSetUp method to set up the slots columns.
     * Generates random starting and finishing indexes for each column.
     */
    @Override
    public void generateResult() {
        try {
            generalArray = (ArrayList<ArrayList<Integer>>) gameSetUp();
            for (int i = 0; i < getAMOUNT_COLUMNS(); i++) {
                int randomStart = (int) (Math.random() * getALL_AMOUNT() + 1);
                this.startIndex.add(randomStart);
                this.finishIndex.add((ROUNDS + randomStart) % getALL_AMOUNT());
            }
        } catch (Exception e){
            System.out.println(e);
        }
        finally {
            return;
        }
    }

    /**
     * Gets the previous index from the general array for a given column and current index.
     * @param array The general array of symbols.
     * @param col The column number.
     * @param curIndex The current index.
     * @return The previous index in the column.
     */
    public int getPrevIndexArray(ArrayList<ArrayList<Integer>> array, int col, int curIndex){
        if (curIndex != 0){
            return array.get(col).get(curIndex - 1);
        }
        return array.get(col).get(array.get(col).size() - 1);
    }

    /**
     * Gets the next index from the general array for a given column and current index.
     * @param array The general array of symbols.
     * @param col The column number.
     * @param curIndex The current index.
     * @return The next index in the column.
     */
    public int getNextIndexArray(ArrayList<ArrayList<Integer>> array, int col, int curIndex){
        if (curIndex != (array.get(col).size()-1)){
            return array.get(col).get(curIndex + 1);
        }
        return array.get(col).get(0);
    }

    /**
     * Gets the general array containing all columns of the slots game.
     * @return The general array.
     */
    public ArrayList<ArrayList<Integer>> getGeneralArray() {
        return generalArray;
    }

    /**
     * Gets the starting index for each column.
     * @return The starting index ArrayList.
     */
    public ArrayList<Integer> getStartIndex() {
        return startIndex;
    }

    /**
     * Gets the finishing index for each column in the slots game.
     * @return The ArrayList containing the finishing index for each column.
     */
    public ArrayList<Integer> getFinishIndex() {
        return finishIndex;
    }

    /**
     * Resets the slots game by clearing all arrays.
     */
    public void reset(){
        generalArray.clear();
        startIndex.clear();
        finishIndex.clear();
    }
}
