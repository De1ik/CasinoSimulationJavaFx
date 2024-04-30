package com.example.mycasinofx.Model.player;

import com.example.mycasinofx.Model.FxModels.Serialization;
import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.Data;
import com.example.mycasinofx.Model.database.DatabaseManager;

import java.sql.SQLException;

public class Player {

    private int user_id;
    private double profit;

    private double balance;
    private double currentStake;

    private static Player playerInstance = null;
    //INNER CLASS
    public AdditionalUserInfo inner;

    private Player(){
        setCurrentStake(5);
        Serialization loadedUserSettings = Serialization.load("user_last_stake.ser");
        if (loadedUserSettings != null) {
            System.out.println("lAst Stake: " + loadedUserSettings.getStake());
            setCurrentStake(loadedUserSettings.getStake());
        }
        inner = new AdditionalUserInfo();
    }

    public static Player getPlayer(){
        if (playerInstance == null){
            playerInstance = new Player();
        }
        return playerInstance;
    }



    //INNER CLASS
    private static class AdditionalUserInfo {
        private int age;
        private String passwordHash;
        private String email;
        private String username;

        public int getAge() {
            return this.age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPasswordHash() {
            return passwordHash;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setUserInfo(String name, String password, String email, int age){
            setAge(age);
            setEmail(email);
            setPasswordHash(password);
            setUsername(name);
        }

    }


    public void setAllData(int user_id, String name, String password, String email, int age, double balance){
        setUserId(user_id);
        setBalance(balance);
        inner.setUserInfo(name, password, email, age);
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

    public String getUsername() {
        return inner.getUsername();
    }

    private void setUserId(int userId) {
        this.user_id = userId;
    }

    public int getUserId() {
        return user_id;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
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

}
