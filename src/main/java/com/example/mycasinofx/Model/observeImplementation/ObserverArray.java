package com.example.mycasinofx.Model.observeImplementation;

import java.util.ArrayList;

public class ObserverArray {
    private ArrayList<Observer> followers;

    public ObserverArray() {
        this.followers = new ArrayList<>();
    }

    public void subscribe(Observer follower) {
        this.followers.add(follower);
    }

    public void unsubscribe(Observer follower) {
        this.followers.remove(follower);
    }

    public ArrayList<Observer> getFollowers() {
        return this.followers;
    }

    public void informAll(double number) {
        for (Observer follower : this.getFollowers()) {
            follower.inform(number);
        }
    }
}
