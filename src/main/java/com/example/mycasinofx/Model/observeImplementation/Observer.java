package com.example.mycasinofx.Model.observeImplementation;

/**
 * Observer class for executing actions based on changes.
 * Used to update the user's bid after changing it in the popup window.
 */
public class Observer {

    /**
     * Functional interface for defining actions to execute.
     */
    @FunctionalInterface
    public static interface ActionExecute{
        /**
         * Executes an action with a given number.
         * The number represents the user's rate.
         * @param number The number to execute the action with.
         */
        void execute (double number);
    }

    /**
     * The action to be executed by the observer.
     */
    private ActionExecute function;

    /**
     * Constructs an Observer with the specified action.
     * @param function The action to be executed.
     */
    public Observer(ActionExecute function){
        this.function = function;
    }

    /**
     * Informs the observer about a change and executes the associated action.
     * @param newAmount The new amount to inform the observer about.
     */
    public void inform(double newAmount){
        function.execute(newAmount);
    }


}
