package com.example.mycasinofx.Model.database;

import com.example.mycasinofx.Model.database.constants.ConstBestGameVotingTable;
import com.example.mycasinofx.Model.database.constants.ConstUserTable;
import com.example.mycasinofx.Model.player.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Objects;

public class DatabaseManager extends Config implements Data {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void registerUser(String name, String password, String email, int age) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + ConstUserTable.USER_TABLE +
                "(" + ConstUserTable.NAME + "," +
                ConstUserTable.PASSWORD + "," +
                ConstUserTable.EMAIL + "," +
                ConstUserTable.AGE  + ")" +
                "VALUES(?, ?, ?, ?)";

        PreparedStatement query = getDbConnection().prepareStatement(insert);
        query.setString(1, name);
        query.setString(2, password);
        query.setString(3, email);
        query.setString(4, String.valueOf(age));

        query.executeUpdate();
    }

    public void loginUserDB(String email, String password) throws SQLException, ClassNotFoundException {
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
                }
            }
        }
    }

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

    public int checkValidPassword(String password) throws SQLException, ClassNotFoundException {
        String checkPassword = "SELECT COUNT(*) AS password_count FROM " + ConstUserTable.USER_TABLE +
                " WHERE " + ConstUserTable.PASSWORD + " = '" + password + "'";

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

    public void voteNewUser(int setPlayerId, String setCategory, String tableName, String usersId, String category) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + tableName +
                "(" + usersId + "," +
                category + ")" +
                "VALUES(?, ?)";

        PreparedStatement query = getDbConnection().prepareStatement(insert);
        query.setInt(1, setPlayerId);
        query.setString(2, setCategory);


        query.executeUpdate();
    }


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

    public void updateVoteUser(String setCategory, String tableName, String category) throws SQLException, ClassNotFoundException {
        String insert = "UPDATE " + tableName +" SET " + category + " = ?";

        PreparedStatement query = getDbConnection().prepareStatement(insert);

        query.setString(1, setCategory);

        query.executeUpdate();
    }

    public int selectNumberVotes(String setCategory, String tableName, String category) throws SQLException, ClassNotFoundException {
        String query;
        if (setCategory.isEmpty()){
            query = "SELECT COUNT(*) FROM " + tableName;
        }
        else {
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
    public void connect() {
        System.out.println("Hello World");
    }

    @Override
    public void executeQuery(String query) {
        System.out.println("Hello World");
    }

    @Override
    public void close() {
        System.out.println("Hello World");
    }

    @Override
    public String getData() {
        return null;
    }
//    private Connection connection;
//
//    public DatabaseManager(String url, String username, String password) throws SQLException {
//        // Установка соединения с базой данных
//        connection = DriverManager.getConnection(url, username, password);
//    }
//
//    public void executeUpdate(String sql) throws SQLException {
//        // Выполнение SQL-запроса обновления (INSERT, UPDATE, DELETE)
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(sql);
//        statement.close();
//    }
//
//    // Другие методы для выполнения запросов, получения данных и т.д.
//
//    public void close() throws SQLException {
//        // Закрытие соединения с базой данных
//        connection.close();
//    }
//
//    public void saveUser(Player player) {
//    }
//
//    public Player getUserByUsernameAndPassword(String username, String password) {
//        return Player.getPlayer();
//    }
}