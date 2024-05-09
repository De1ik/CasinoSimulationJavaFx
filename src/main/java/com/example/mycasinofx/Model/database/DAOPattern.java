package com.example.mycasinofx.Model.database;

import java.sql.SQLException;
import java.util.function.Consumer;


/**
 * The DAOPattern class provides methods for interacting with the database using the DAO (Data Access Object) pattern.
 * It contains static methods for updating balances, voting, selecting data, checking passwords and emails, logging in users,
 * and registering new users asynchronously.
 */
public class DAOPattern {

    /**
     * The data manager responsible for database operations.
     */
    static Data data = new DatabaseManager();

    /**
     * Action to be executed on success.
     * Used for logging successfully performed actions related to the database.
     */
    public static Action onSuccess = (functionName) -> {
        System.out.println("Method '" + functionName + "' was executed correctly");
    };

    /**
     * Consumer to handle errors.
     * * If an error occurs while interacting with the database, it will display the error text.
     */
    static Consumer<Exception> onError = e -> {
        System.out.println("Error during execution: " + e.getMessage());
    };

    /**
     * Updates the balance of a user asynchronously.
     * @param id The ID of the user.
     * @param newBalance The new balance to be set.
     */
    public static void updateBalance(int id, double newBalance ){
        data.updateBalanceAsync(id, newBalance, onSuccess, onError);
    }

    /**
     * Updates the vote of a user asynchronously.
     * @param setCategory The category to be set.
     * @param tableName The name of the table.
     * @param category The category of the user.
     * @param userId The ID of the user.
     */
    public static void updateVoteUser(String setCategory, String tableName, String category, int userId){
        data.updateVoteUser(setCategory, tableName, category, userId, onSuccess, onError);
    }

    /**
     * Votes for a new user asynchronously.
     * @param setPlayerId The ID of the player.
     * @param setCategory The category to be set.
     * @param tableName The name of the table.
     * @param usersId The ID of the user.
     * @param category The category of the user.
     */
    public static void voteNewUser(int setPlayerId, String setCategory, String tableName, String usersId, String category){
        data.voteNewUserAsync(setPlayerId, setCategory, tableName, usersId, category, onSuccess, onError);
    }


    /**
     * Selects the number of votes for a specific game category in a table.
     * @param setCategory The category of the game to be selected.
     * @param tableName The name of the table.
     * @param category The name of the column in DB of category of the game.
     * @return The number of votes for the specified category.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    public static int selectNumberVotes(String setCategory, String tableName, String category) throws SQLException, ClassNotFoundException {
        return data.selectNumberVotes(setCategory, tableName, category);
    }

    /**
     * Checks whether this user has already voted or not.
     * @param setPlayerId The ID of the player.
     * @param tableName The name of the table.
     * @param usersId The ID of the user.
     * @return {@code true} if the user has already voted, {@code false} otherwise.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    public static boolean voteCheckNewUser(int setPlayerId, String tableName, String usersId) throws SQLException, ClassNotFoundException {
        return data.voteCheckNewUser(setPlayerId, tableName, usersId);
    }

    /**
     * Checks if the provided password matches the email.
     * @param password The password to be checked.
     * @param email The email associated with the password.
     * @return 1 if the password is valid, 0 if not, -1 if an error occurs.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    public static int checkValidPassword(String password, String email) throws SQLException, ClassNotFoundException {
        return data.checkValidPassword(password, email);
    }

    /**
     * Checks if the email is valid.
     * @param email The email to be checked.
     * @return 1 if the email is valid, 0 if not, -1 if an error occurs.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    public static int checkValidEmail(String email) throws SQLException, ClassNotFoundException {
        return data.checkValidEmail(email);
    }

    /**
     * Logs in a user.
     * @param email The email of the user.
     * @param password The password of the user.
     */
    public static void loginUserDB(String email, String password){
        data.loginUserDB(email, password, onSuccess, onError);
    }

    /**
     * Registers a new user asynchronously.
     * @param name The name of the user.
     * @param password The password of the user.
     * @param email The email of the user.
     * @param age The age of the user.
     */
    public static void registerUser(String name, String password, String email, int age){
        data.registerUserAsync(name, password, email, age, onSuccess, onError);
    }
}
