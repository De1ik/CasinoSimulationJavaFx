package com.example.mycasinofx.Model.database;

import java.sql.SQLException;
import java.util.function.Consumer;

public class DAOPattern {

    static Data data = new DatabaseManager();

    public static Action onSuccess = (functionName) -> {
        System.out.println("Method '" + functionName + "' was executed correct");
    };
    static Consumer<Exception> onError = e -> {
        System.out.println("Error during executing: " + e.getMessage());
    };



    public static void updateBalance(int id, double newBalance ){
        data.updateBalanceAsync(id, newBalance, onSuccess, onError);
    }

    public static void updateVoteUser(String setCategory, String tableName, String category, int userId){
        data.updateVoteUser(setCategory, tableName, category, userId, onSuccess, onError);
    }

    public static void voteNewUser(int setPlayerId, String setCategory, String tableName, String usersId, String category){
        data.voteNewUser(setPlayerId, setCategory, tableName, usersId, category, onSuccess, onError);
    }


    public static int selectNumberVotes(String setCategory, String tableName, String category) throws SQLException, ClassNotFoundException {
        return data.selectNumberVotes(setCategory, tableName, category);
    }

    public static boolean voteCheckNewUser(int setPlayerId, String tableName, String usersId) throws SQLException, ClassNotFoundException {
        return data.voteCheckNewUser(setPlayerId, tableName, usersId);
    }

    public static int checkValidPassword(String password, String email) throws SQLException, ClassNotFoundException {
        return data.checkValidPassword(password, email);
    }

    public static int checkValidEmail(String email) throws SQLException, ClassNotFoundException {
        return data.checkValidEmail(email);
    }

    public static void loginUserDB(String email, String password){
        data.loginUserDB(email, password, onSuccess, onError);
    }

    public static void registerUser(String name, String password, String email, int age){
        data.registerUserAsync(name, password, email, age, onSuccess, onError);
    }
}
