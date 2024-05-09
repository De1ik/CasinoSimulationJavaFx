package com.example.mycasinofx.Model.games;


/**
 * This class serves as a generic container for storing results of different types of games.
 * It provides an abstraction layer for accessing and setting game results.
 * Once the game has generated a result, it is stored in this class,
 * then when the user's results need to be compared with the winning results,
 * the game's result is taken from this class.
 *
 * @param <T> The type of result to be stored.
 */
public class ResultGenericClass<T>{

    /**
     * The result stored in this class.
     */
    private T result;
    /**
     * Singleton instance of ResultGenericClass.
     */
    private static ResultGenericClass<?> resultGenericClass = null;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private ResultGenericClass() {
    }

    /**
     * Retrieves the singleton instance of ResultGenericClass.
     * @return The singleton instance.
     */
    public static synchronized ResultGenericClass<?> getResult(){
        if (resultGenericClass == null){
            resultGenericClass = new ResultGenericClass<>();
        }
        return resultGenericClass;
    }

    /**
     * Retrieves the stored result value.
     * @return The stored result value.
     */
    public T getResultValue() {
        return result;
    }

    //used in TwentyOne(checkWinner) / Slots(CheckWinner) / Roulette(checkWinner)
    /**
     * Sets the result value.
     * @param value The result value to be set.
     */
    public void setResult(T value) {
        this.result = value;
    }
}
