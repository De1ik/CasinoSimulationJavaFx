package com.example.mycasinofx.controllers.games.voting;

import com.example.mycasinofx.Model.voting.VotingBestGame;
import com.example.mycasinofx.Model.voting.VotingNewGame;

public class VotingController {
    private VotingBestGame votingBestGame;
    private VotingNewGame votingNewGame;

    public VotingController(){
        votingBestGame = new VotingBestGame();
        votingNewGame = new VotingNewGame();
    }

    public void getNumberVoicesBestGame(){
        votingBestGame.updateVoices();
    }

    public void getNumberVoicesNewGame(){
        votingNewGame.updateVoices();
    }
}
