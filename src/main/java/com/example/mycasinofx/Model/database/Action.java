package com.example.mycasinofx.Model.database;

/**
 * The Action functional interface represents an action that can be performed.
 * Allows to pass the function name in String format, for further output.
 */
@FunctionalInterface
interface Action {
    /**
     * Performs the action with the specified function name.
     * @param functionName The name of the function to be performed.
     */
    void perform(String functionName);
}