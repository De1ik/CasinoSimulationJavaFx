package com.example.mycasinofx.Model.observeImplementation;

import java.util.ArrayList;

/**
 * Manages a list of observers and provides methods to subscribe, unsubscribe, and inform them.
 */
public class ObserverArray {
    /** The list of observers. */
    private ArrayList<Observer> followers;

    /**
     * Constructs an ObserverArray with an empty list of followers.
     */
    public ObserverArray() {
        this.followers = new ArrayList<>();
    }

    /**
     * Subscribes an observer to the list.
     * @param follower The observer to subscribe.
     */
    public void subscribe(Observer follower) {
        this.followers.add(follower);
    }

    /**
     * Unsubscribes an observer from the list.
     * @param follower The observer to unsubscribe.
     */
    public void unsubscribe(Observer follower) {
        this.followers.remove(follower);
    }

    /**
     * Gets the list of observers.
     * @return The list of observers.
     */
    public ArrayList<Observer> getFollowers() {
        return this.followers;
    }

    /**
     * Informs all observers in the list with a given number.
     * @param number The number to inform all observers about.
     */
    public void informAll(double number) {
        for (Observer follower : this.getFollowers()) {
            follower.inform(number);
        }
    }
}
