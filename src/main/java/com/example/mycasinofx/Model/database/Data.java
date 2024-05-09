package com.example.mycasinofx.Model.database;

import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * The Data interface defines methods for data operations related to the database.
 */
public interface Data {

    /**
     * Asynchronously updates the balance of a user.
     * @param id The ID of the user.
     * @param newBalance The new balance to be set.
     * @param onSuccess The action to be executed on success (log the name of the function).
     * @param onError The consumer to handle errors (log text of the error).
     */
    void updateBalanceAsync(int id, double newBalance, final Action onSuccess, final Consumer<Exception> onError);

    /**
     * Retrieves the number of votes for a specific category in a table.
     * @param setCategory The category to be selected.
     * @param tableName The name of the table.
     * @param category The category of the game.
     * @return The number of votes for the specified category.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    int selectNumberVotes(String setCategory, String tableName, String category) throws SQLException, ClassNotFoundException;

    /**
     * Updates the vote of a user.
     * @param setCategory The category of game to be set.
     * @param tableName The name of the table.
     * @param category The category of the game.
     * @param idUser The ID of the user.
     * @param onSuccess The action to be executed on success (log the name of the function).
     * @param onError The consumer to handle errors (log text of the error).
     */
    void updateVoteUser(String setCategory, String tableName, String category, int idUser, final Action onSuccess, final Consumer<Exception> onError);

    /**
     * Checks whether this user has already voted or not.
     * @param setPlayerId The ID of the player.
     * @param tableName The name of the table.
     * @param usersId The ID of the user.
     * @return {@code true} if the user has already voted or {@code false} otherwise.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    boolean voteCheckNewUser(int setPlayerId, String tableName, String usersId) throws SQLException, ClassNotFoundException;

    /**
     * Asynchronously votes for a new user.
     * @param setPlayerId The ID of the player.
     * @param setCategory The category to be set.
     * @param tableName The name of the table.
     * @param usersId The ID of the user.
     * @param category The category of the user.
     * @param onSuccess The action to be executed on success (log the name of the function).
     * @param onError The consumer to handle errors (log text of the error).
     */
    void voteNewUserAsync(int setPlayerId, String setCategory, String tableName, String usersId, String category, final Action onSuccess, final Consumer<Exception> onError);

    /**
     * Checks if the provided password matches the email.
     * @param password The password to be checked.
     * @param email The email associated with the password.
     * @return 1 if the password is valid, 0 if not, -1 if an error occurs.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    int checkValidPassword(String password, String email) throws SQLException, ClassNotFoundException;

    /**
     * Checks if the email is valid.
     * @param email The email to be checked.
     * @return 1 if the email is valid, 0 if not, -1 if an error occurs.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    int checkValidEmail(String email) throws SQLException, ClassNotFoundException;

    /**
     * Logs in a user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @param onSuccess The action to be executed on success (log the name of the function).
     * @param onError The consumer to handle errors (log text of the error).
     */
    void loginUserDB(String email, String password, final Action onSuccess, final Consumer<Exception> onError);

    /**
     * Asynchronously registers a new user.
     * @param name The name of the user.
     * @param password The password of the user.
     * @param email The email of the user.
     * @param age The age of the user.
     * @param onSuccess The action to be executed on success (log the name of the function).
     * @param onError The consumer to handle errors (log text of the error).
     */
    void registerUserAsync(String name, String password, String email, int age, final Action onSuccess, final Consumer<Exception> onError);
}
