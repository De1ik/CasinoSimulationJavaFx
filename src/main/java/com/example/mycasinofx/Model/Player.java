package com.example.mycasinofx.Model;

import com.example.mycasinofx.Model.FxModels.Serialization;
import com.example.mycasinofx.Model.database.DAOPattern;


/**
 * The Player class represents a player.
 * It holds information about the player's ID, profit, balance, current stake, and additional user information.
 */
public class Player {

    /**
     * Represents a user id.
     */
    private int user_id;

    /**
     * Represents a user profit.
     */
    private double profit;

    /**
     * Represents a user balance.
     */
    private double balance;

    /**
     * Represents a user currentStake.
     */
    private double currentStake;

    /**
     * Represents the instance of the player.
     */
    private static Player playerInstance = null;

    /**
     * Inner class representing additional user information.
     */
    public AdditionalUserInfo inner;


    /**
     * Constructs a Player object.
     * Initializes the current stake with a default value of 5, and loads the last user stake from a file if available (Serialization).
     * Creates an instance of the AdditionalUserInfo inner class.
     */
    private Player(){
        setCurrentStake(5);
        Serialization loadedUserSettings = Serialization.load("user_last_stake.ser");
        if (loadedUserSettings != null) {
            System.out.println("Last user stake was download from 'user_last_stake.ser'");
            setCurrentStake(loadedUserSettings.getStake());
        }
        inner = new AdditionalUserInfo();
    }


    /**
     * Returns the singleton instance of the Player class.
     * If the instance is null, creates a new instance of Player.
     * @return The singleton instance of the Player class.
     */
    public static Player getPlayer(){
        if (playerInstance == null){
            playerInstance = new Player();
        }
        return playerInstance;
    }



    /**
     * The AdditionalUserInfo inner class holds additional information about the player,
     * that is not used frequently during the running program.
     */
    private static class AdditionalUserInfo {
        /**
         * Represents a user age.
         */
        private int age;

        /**
         * Represents a user passwordHash.
         */
        private String passwordHash;

        /**
         * Represents a user email.
         */
        private String email;

        /**
         * Represents a username.
         */
        private String username;

        /**
         * Retrieves the age of the player.
         * @return The age of the player.
         */
        public int getAge() {
            return this.age;
        }

        /**
         * Sets the age of the player.
         * @param age The age of the player to be set.
         */
        public void setAge(int age) {
            this.age = age;
        }

        /**
         * Retrieves the password hash of the player.
         * @return The password hash of the player.
         */
        public String getPasswordHash() {
            return passwordHash;
        }

        /**
         * Sets the password hash of the player.
         * @param passwordHash The password hash of the player to be set.
         */
        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }


        /**
         * Retrieves the email of the player.
         * @return The email of the player.
         */
        public String getEmail() {
            return email;
        }

        /**
         * Sets the email of the player.
         * @param email The email of the player to be set.
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * Retrieves the username of the player.
         * @return The username of the player.
         */
        public String getUsername() {
            return username;
        }


        /**
         * Sets the username of the player.
         * @param username The username of the player to be set.
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * Sets the user information for the player.
         * @param name The name of the player.
         * @param password The password of the player.
         * @param email The email of the player.
         * @param age The age of the player.
         */
        public void setUserInfo(String name, String password, String email, int age){
            setAge(age);
            setEmail(email);
            setPasswordHash(password);
            setUsername(name);
        }

    }

    /**
     * Sets all data for the player, including user ID, name, password, email, age, and balance.
     * @param user_id The user ID of the player.
     * @param name The name of the player.
     * @param password The password of the player.
     * @param email The email of the player.
     * @param age The age of the player.
     * @param balance The balance of the player.
     */
    public void setAllData(int user_id, String name, String password, String email, int age, double balance){
        setUserId(user_id);
        setBalance(balance);
        inner.setUserInfo(name, password, email, age);
    }

    /**
     * Retrieves the current stake of the player.
     * @return The current stake of the player.
     */
    public double getCurrentStake() {
        return currentStake;
    }

    /**
     * Sets the current stake of the player.
     * @param currentStake The current stake of the player to be set.
     */
    public void setCurrentStake(double currentStake) {
        this.currentStake = currentStake;
    }

    /**
     * Checks if the player can afford to stake the current amount.
     * @return true if the player's balance is sufficient to cover the current stake, otherwise false.
     */
    public boolean tryDoStake(){
        return balance - currentStake >= 0;
    }

    /**
     * Retrieves the username of the player.
     * @return The username of the player
     */
    public String getUsername() {
        return inner.getUsername();
    }

    /**
     * Sets the user ID of the player.
     * @param userId The user ID of the player to be set.
     */
    private void setUserId(int userId) {
        this.user_id = userId;
    }

    /**
     * Retrieves the user ID of the player.
     * @return The user ID of the player.
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * Retrieves the profit of the player.
     * @return The profit of the player.
     */
    public double getProfit() {
        return profit;
    }

    /**
     * Sets the profit of the player.
     * @param profit The profit of the player to be set.
     */
    public void setProfit(double profit) {
        this.profit = profit;
    }

    /**
     * Retrieves the balance of the player.
     * @return The balance of the player.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the player.
     * @param balance The balance of the player to be set.
     */
    public void setBalance(double balance){
        this.balance = balance;
    }

    /**
     * Updates the balance of the player in the database.
     */
    public void updateDBBalance(){
        DAOPattern.updateBalance(this.user_id, this.balance);
    }

}
