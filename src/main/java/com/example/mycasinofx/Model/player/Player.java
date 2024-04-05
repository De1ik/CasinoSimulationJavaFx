package com.example.mycasinofx.Model.player;

import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.Data;
import com.example.mycasinofx.Model.database.DatabaseManager;

import java.sql.SQLException;

public class Player {
    private String username;
    private String passwordHash;
    private String email;
    private int age;
    private int user_id;
    private double profit;
    private final DatabaseManager databaseManager = new DatabaseManager();

    private final DAOPattern data = new DAOPattern();


    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    private void setUserId(int userId) {
        this.user_id = userId;
    }

    public int getUserId() {
        return user_id;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void updateDBBalance(){
        DAOPattern.updateBalance(this.user_id, this.balance);
    }

    public void setAllData(int user_id, String name, String password, String email, int age, double balance){
        setUserId(user_id);
        setUsername(name);
        setPasswordHash(password);
        setEmail(email);
        setAge(age);
        this.balance = balance;
    }


    private double balance;
    private double currentStake;

    private static Player playerInstance = null;

    private Player(){
//        setBalance(50000);
        setCurrentStake(5);
    }

    public static Player getPlayer(){
        if (playerInstance == null){
            playerInstance = new Player();
        }
        return playerInstance;
    }

    public double getCurrentStake() {
        return currentStake;
    }

    public void setCurrentStake(double currentStake) {
        this.currentStake = currentStake;
    }

    public boolean tryDoStake(){
        return balance - currentStake >= 0;
    }



//    public void UserManager(DatabaseManager databaseManager) {
//        this.databaseManager = databaseManager;
//    }
//    public void registerUser(Player player) {
//        // Логика регистрации нового пользователя в базе данных
//        databaseManager.saveUser(player);
//    }
//
//    public Player registerUser(String username, String password) {
//        // Логика аутентификации пользователя в базе данных
//        return databaseManager.getUserByUsernameAndPassword(username, password);
//    }
}
