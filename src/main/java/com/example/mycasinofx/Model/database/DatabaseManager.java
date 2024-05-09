package com.example.mycasinofx.Model.database;

import com.example.mycasinofx.Model.database.constants.ConstUserTable;
import com.example.mycasinofx.Model.Player;

import java.sql.*;
import java.util.function.Consumer;



/**
 * The DatabaseManager class manages database operations and extends the Config class for database configuration.
 * It implements the Data interface to provide methods for asynchronous database interactions.
 */
public class DatabaseManager extends Config implements Data {
    /**
     * Represent the database connection.
     */
    private Connection dbConnection;


    /**
     * Establishes a database connection.
     * @return The database connection.
     * @throws ClassNotFoundException If the database driver class is not found.
     * @throws SQLException If an SQL exception occurs.
     */
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, DB_USER, DB_PASS);

        return dbConnection;
    }


    /**
     * Asynchronously registers a new user.
     * @param name The name of the user.
     * @param password The password of the user.
     * @param email The email of the user.
     * @param age The age of the user.
     * @param onSuccess The action to be executed on success (log the name of the function).
     * @param onError The consumer to handle errors (log text of the error).
     */
    @Override
    public void registerUserAsync(String name, String password, String email, int age, final Action onSuccess, final Consumer<Exception> onError) {
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                String insert = "INSERT INTO " + ConstUserTable.USER_TABLE +
                        "(" + ConstUserTable.NAME + "," +
                        ConstUserTable.PASSWORD + "," +
                        ConstUserTable.EMAIL + "," +
                        ConstUserTable.AGE + ")" +
                        "VALUES(?, ?, ?, ?)";

                PreparedStatement query = null;
                try {
                    query = getDbConnection().prepareStatement(insert);
                    String methodNameInner = new Object() {}.getClass().getEnclosingMethod().getName();
                    onSuccess.perform(methodName + "/" + methodNameInner);
                } catch (SQLException | ClassNotFoundException e) {
                    onError.accept(e);
                }
                try {
                    query.setString(1, name);
                    query.setString(2, password);
                    query.setString(3, email);
                    query.setString(4, String.valueOf(age));
                    query.executeUpdate();
                    String methodNameInner = new Object() {}.getClass().getEnclosingMethod().getName();
                    onSuccess.perform(methodName + "/" + methodNameInner);
                } catch (SQLException e) {
                    onError.accept(e);
                }
            }
        });
        thread.start();
    }

    /**
     * Logs in a user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @param onSuccess The action to be executed on success (log the name of the function).
     * @param onError The consumer to handle errors (log text of the error).
     */
    @Override
    public void loginUserDB(String email, String password, final Action onSuccess, final Consumer<Exception> onError) {
        String query = "SELECT * FROM " + ConstUserTable.USER_TABLE +
                " WHERE " + ConstUserTable.EMAIL + " = ? AND " + ConstUserTable.PASSWORD + " = ?";

        try (Connection connection = getDbConnection();
             PreparedStatement executable_statement = connection.prepareStatement(query)) {

            executable_statement.setString(1, email);
            executable_statement.setString(2, password);

            try (ResultSet rs = executable_statement.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt(ConstUserTable.USERS_ID);
                    String userEmail = rs.getString(ConstUserTable.EMAIL);
                    String userPassword = rs.getString(ConstUserTable.PASSWORD);
                    String userName = rs.getString(ConstUserTable.NAME);
                    int playerAge = rs.getInt(ConstUserTable.AGE);
                    double balance = rs.getDouble(ConstUserTable.BALANCE);

                    Player player = Player.getPlayer();
                    player.setAllData(userId, userName, userPassword, userEmail, playerAge, balance);
                    String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
                    onSuccess.perform(methodName);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            onError.accept(e);
        }
    }


    /**
     * Checks if the provided email is valid.
     * @param email The email to be checked.
     * @return 1 if the email is valid, 0 if not, -1 if an error occurs.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    @Override
    public int checkValidEmail(String email) throws SQLException, ClassNotFoundException {
        String checkEmail = "SELECT COUNT(*) AS email_count FROM " + ConstUserTable.USER_TABLE +
                " WHERE " + ConstUserTable.EMAIL + " = '" + email + "'";

        PreparedStatement query = getDbConnection().prepareStatement(checkEmail);

        try (ResultSet rs = query.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("email_count");
                if (count > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return -1;
    }

    /**
     * Asynchronously checks if the provided password matches the email.
     * @param password The password to be checked.
     * @param email The email associated with the password.
     * @return 1 if the password is valid, 0 if not, -1 if an error occurs.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    @Override
    public int checkValidPassword(String password, String email) throws SQLException, ClassNotFoundException {
        String checkPassword = "SELECT COUNT(*) AS password_count FROM " + ConstUserTable.USER_TABLE +
                " WHERE " + ConstUserTable.PASSWORD + " = '" + password + "'" +
                " AND " + ConstUserTable.EMAIL + " = '" + email + "'";

        PreparedStatement query = getDbConnection().prepareStatement(checkPassword);

        try (ResultSet rs = query.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("password_count");
                if (count > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return -1;
    }


    /**
     * Asynchronously votes for a new user.
     * @param setPlayerId The ID of the player.
     * @param setCategory The category to be set.
     * @param tableName The name of the table.
     * @param usersId The ID of the user.
     * @param category The category of the game.
     * @param onSuccess The action to be executed on success (log the name of the function).
     * @param onError The consumer to handle errors (log text of the error).
     */
    @Override
    public void voteNewUserAsync(int setPlayerId, String setCategory, String tableName, String usersId, String category, final Action onSuccess, final Consumer<Exception> onError) {
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String insert = "INSERT INTO " + tableName +
                        "(" + usersId + "," +
                        category + ")" +
                        "VALUES(?, ?)";

                PreparedStatement query = null;
                try {
                    query = getDbConnection().prepareStatement(insert);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }


                try {
                    query.setInt(1, setPlayerId);
                    query.setString(2, setCategory);
                    query.executeUpdate();
                    String methodNameInner = new Object() {}.getClass().getEnclosingMethod().getName();
                    onSuccess.perform(methodName + "/" + methodNameInner);
                } catch (SQLException e) {
                    onError.accept(e);
                }

            }
        });
        thread.start();
    }


    /**
     * Checks if a new user has already voted.
     * @param setPlayerId The ID of the player.
     * @param tableName The name of the table.
     * @param usersId The ID of the user.
     * @return {@code true} if the new user has voted, {@code false} otherwise.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    @Override
    public boolean voteCheckNewUser(int setPlayerId, String tableName, String usersId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + tableName +
                " WHERE " + usersId + " = ?";

        try (Connection connection = getDbConnection();
             PreparedStatement executable_statement = connection.prepareStatement(query)) {

            executable_statement.setInt(1, setPlayerId);

            try (ResultSet rs = executable_statement.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
                return false;
            }
        }
    }


    /**
     * Updates the vote of a user.
     * @param setCategory The category to be set.
     * @param tableName The name of the table.
     * @param category The category of the code.
     * @param userId The ID of the user.
     * @param onSuccess The action to be executed on success (log the name of the function).
     * @param onError The consumer to handle errors (log text of the error).
     */
    @Override
    public void updateVoteUser(String setCategory, String tableName, String category, int userId, final Action onSuccess, final Consumer<Exception> onError) {
        String insert = "UPDATE " + tableName + " SET " + category + " = ?" + " WHERE " + " idusers " + " = ?";

        try (Connection connection = getDbConnection();
             PreparedStatement query = connection.prepareStatement(insert)) {

            query.setString(1, setCategory);
            query.setInt(2, userId);
            query.executeUpdate();
            String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
            onSuccess.perform(methodName);
        } catch (SQLException | ClassNotFoundException e) {
            onError.accept(e);
        }
    }


    /**
     * Retrieves the number of votes for a specific category in a table.
     * @param setCategory The category to be selected.
     * @param tableName The name of the table.
     * @param category The category of the user.
     * @return The number of votes for the specified category of the game.
     * @throws SQLException If an SQL exception occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    @Override
    public int selectNumberVotes(String setCategory, String tableName, String category) throws SQLException, ClassNotFoundException {
        String query;
        if (setCategory.isEmpty()) {
            query = "SELECT COUNT(*) FROM " + tableName;
        } else {
            query = "SELECT COUNT(*) FROM " + tableName +
                    " WHERE " + category + " = ?";
        }
        try (Connection connection = getDbConnection();
             PreparedStatement executable_statement = connection.prepareStatement(query)) {

            if (!setCategory.isEmpty()) {
                executable_statement.setString(1, setCategory);
            }

            try (ResultSet rs = executable_statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            }
        }
    }

    /**
     * Asynchronously updates the balance of a user.
     * @param id The ID of the user.
     * @param newBalance The new balance to be set.
     * @param onSuccess The action to be executed on success (log the name of the function).
     * @param onError The consumer to handle errors (log text of the error).
     */
    @Override
    public void updateBalanceAsync(int id, double newBalance, final Action onSuccess, final Consumer<Exception> onError) {
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (Connection connection = getDbConnection()) {
                    String updateQuery = "UPDATE " + ConstUserTable.USER_TABLE +
                            " SET " + ConstUserTable.BALANCE + " = ?" +
                            " WHERE " + ConstUserTable.USERS_ID + " = ?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setDouble(1, newBalance);
                        statement.setInt(2, id);
                        statement.executeUpdate();
                        String methodNameInner = new Object() {}.getClass().getEnclosingMethod().getName();
                        onSuccess.perform(methodName + "/" + methodNameInner);
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    onError.accept(e);
                }
            }
        });
        thread.start();
    }


}