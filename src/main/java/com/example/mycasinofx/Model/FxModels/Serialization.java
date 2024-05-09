package com.example.mycasinofx.Model.FxModels;

import java.io.*;


/**
 * The Serialization class provides functionality for serializing and deserializing objects.
 * This class is used to save and load stake values for the application.
 * When the user logs back into his app, the size of is last bet will automatically load.
 */
public class Serialization implements Serializable {
    /**
     * Represent the amount of the last stake.
     */
    private double stake;

    /**
     * Constructs a Serialization object with the specified stake value.
     * @param newStake The stake value to be stored.
     */
    public Serialization(double newStake) {
        this.stake = newStake;
    }

    /**
     * Retrieves the stake value stored in the Serialization object.
     * @return The stake value.
     */
    public double getStake() {
        return stake;
    }

    /**
     * Sets the stake value for the Serialization object.
     * @param newStake The new stake value to be set.
     */
    public void setStake(double newStake) {
        this.stake = newStake;
    }

    /**
     * Saves the Serialization object to a file.
     * @param filename The name of the file to which the object will be saved.
     */
    public void save(String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a Serialization object from a file.
     * @param filename The name of the file from which the object will be loaded.
     * @return The loaded Serialization object, or null if loading fails.
     */
    public static Serialization load(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Serialization) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
