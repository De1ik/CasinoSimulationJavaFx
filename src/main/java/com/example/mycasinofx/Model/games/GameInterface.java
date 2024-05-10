package com.example.mycasinofx.Model.games;

import java.sql.SQLException;

/**
 * This interface represents a generic game
 * interface providing methods for checking
 * the winner and generating results.
 */
public interface GameInterface {
    /**
     * Checks for the winner of the game.
     * @throws SQLException            If a database access error occurs.
     * @throws ClassNotFoundException If the driver class cannot be found.
     */
    void checkWinner() throws SQLException, ClassNotFoundException;

    /**
     * Generates the result of the game.
     */
    void generateResult();
}
