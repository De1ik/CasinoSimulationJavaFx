package com.example.mycasinofx.Model.database;

import com.example.mycasinofx.Model.database.constants.ConstUserTable;
import com.example.mycasinofx.Model.player.Player;

import java.sql.*;
import java.util.function.Consumer;

public class DatabaseManager extends Config implements Data {
    Connection dbConnection;


    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, DB_USER, DB_PASS);

        return dbConnection;
    }

    @Override
    public void registerUserAsync(String name, String password, String email, int age, final Runnable onSuccess, final Consumer<Exception> onError) {
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
                    onSuccess.run();
                } catch (SQLException | ClassNotFoundException e) {
                    onError.accept(e);
                }
                try {
                    query.setString(1, name);
                    query.setString(2, password);
                    query.setString(3, email);
                    query.setString(4, String.valueOf(age));
                    query.executeUpdate();
                    onSuccess.run();
                } catch (SQLException e) {
                    onError.accept(e);
                }
            }
        });
        thread.start();
    }

    @Override
    public void loginUserDB(String email, String password, final Runnable onSuccess, final Consumer<Exception> onError) {
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
                    onSuccess.run();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            onError.accept(e);
        }
    }

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

    @Override
    public void voteNewUser(int setPlayerId, String setCategory, String tableName, String usersId, String category, final Runnable onSuccess, final Consumer<Exception> onError) {
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
                    onSuccess.run();
                } catch (SQLException e) {
                    onError.accept(e);
                }

            }
        });
        thread.start();
    }


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



    //ПО СУТИ МОЖНО ЗАЮЗАТЬ КАКОЙ ТО ПАТЕРН, ЧТОБ ЗНАТЬ КАКАЯ ТАБЛИЦА ПРИШЛА
    @Override
    public void updateVoteUser(String setCategory, String tableName, String category, int userId, final Runnable onSuccess, final Consumer<Exception> onError) {
        String insert = "UPDATE " + tableName + " SET " + category + " = ?" + " WHERE " + " idusers " + " = ?";

        try (Connection connection = getDbConnection();
             PreparedStatement query = connection.prepareStatement(insert)) {

            query.setString(1, setCategory);
            query.setInt(2, userId);
            query.executeUpdate();
            onSuccess.run();
        } catch (SQLException | ClassNotFoundException e) {
            onError.accept(e);
        }
    }


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


    @Override
    public void updateBalanceAsync(int id, double newBalance, final Runnable onSuccess, final Consumer<Exception> onError) {
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
                        onSuccess.run();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    onError.accept(e);
                }
            }
        });
        thread.start();
    }


}