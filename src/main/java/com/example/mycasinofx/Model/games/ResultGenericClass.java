package com.example.mycasinofx.Model.games;

public class ResultGenericClass<T>{
    /*
    This class on the stage can receive different types of data depending on the type of game.
    Each game when it generates a result saves it to this class, when the user has played, the result is taken from this class.
    This class is used as an additional layer to increase abstraction.
     */
    private T result;
    private static ResultGenericClass<?> resultGenericClass = null;


    private ResultGenericClass(){
    }

    public static synchronized ResultGenericClass<?> getResult(){
        if (resultGenericClass == null){
            resultGenericClass = new ResultGenericClass<>();
        }
        return resultGenericClass;
    }


    public T getResultValue() {
        return result;
    }

    //used in TwentyOne(checkWinner) / Slots(CheckWinner) / Roulette(checkWinner)
    public void setResult(T value) {
        this.result = value;
    }
}
