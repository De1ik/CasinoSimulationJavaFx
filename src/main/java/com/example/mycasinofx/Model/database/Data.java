package com.example.mycasinofx.Model.database;

import java.sql.SQLException;
import java.util.function.Consumer;

public interface Data {


    void updateBalanceAsync(int id, double newBalance, final Runnable onSuccess, final Consumer<Exception> onError);
    int selectNumberVotes(String setCategory, String tableName, String category) throws SQLException, ClassNotFoundException;
    void updateVoteUser(String setCategory, String tableName, String category, int idUser, final Runnable onSuccess, final Consumer<Exception> onError);
    boolean voteCheckNewUser(int setPlayerId, String tableName, String usersId) throws SQLException, ClassNotFoundException;
    void voteNewUser(int setPlayerId, String setCategory, String tableName, String usersId, String category, final Runnable onSuccess, final Consumer<Exception> onError);

    int checkValidPassword(String password, String email) throws SQLException, ClassNotFoundException;
    int checkValidEmail(String email) throws SQLException, ClassNotFoundException;
    void loginUserDB(String email, String password, final Runnable onSuccess, final Consumer<Exception> onError);

    void registerUserAsync(String name, String password, String email, int age, final Runnable onSuccess, final Consumer<Exception> onError);

}
