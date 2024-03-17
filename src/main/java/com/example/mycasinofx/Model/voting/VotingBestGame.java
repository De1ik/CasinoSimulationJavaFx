package com.example.mycasinofx.Model.voting;

import com.example.mycasinofx.Model.database.DatabaseManager;

public class VotingBestGame implements VotingInterface{
    private final DatabaseManager dbManager;

    public VotingBestGame() {
        dbManager = new DatabaseManager();
    }
    @Override
    public void updateVoices() {
//        dbManager.executeQuery("");
        System.out.println("Hello World");
    }

    @Override
    public Object getVoices() {
        System.out.println("Hello World");
        return null;
    }
}
