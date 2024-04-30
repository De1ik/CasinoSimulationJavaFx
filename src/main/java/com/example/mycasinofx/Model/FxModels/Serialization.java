package com.example.mycasinofx.Model.UsefulComponents;

import java.io.*;

public class Serialization implements Serializable {
    private double stake;


    public Serialization(double newStake) {
        this.stake = newStake;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double newStake) {
        this.stake = newStake;
    }

    public void save(String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
