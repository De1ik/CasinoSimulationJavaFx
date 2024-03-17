package com.example.mycasinofx.controllers.voting.votingProcces;

import com.example.mycasinofx.Model.FxModels.SceneSwitch;
import com.example.mycasinofx.Model.database.DatabaseManager;
import com.example.mycasinofx.Model.database.constants.ConstBestGameVotingTable;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class BestGameVotingController {

    @FXML
    AnchorPane bestGameVoting;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    RadioButton radioRoulette, radioTwentyOne, radioSlot, resultVoting;
    @FXML
    Label warningLabel;
    private final DatabaseManager databaseManager = new DatabaseManager();
    private final SwitchPage switchPage = new SwitchPage();


    public void initialize(){
        radioRoulette.setToggleGroup(toggleGroup);
        radioTwentyOne.setToggleGroup(toggleGroup);
        radioSlot.setToggleGroup(toggleGroup);
        warningLabel.setText("");

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (toggleGroup.getSelectedToggle() != null) {
                resultVoting = (RadioButton) toggleGroup.getSelectedToggle();
            }
        });
    }


    public void vote() throws IOException{
        Player player = Player.getPlayer();
        if (resultVoting == null){
            warningLabel.setText("Choose the variant");
        }
        else{
            try{
                if (databaseManager.voteCheckNewUser(player.getUserId(),
                        ConstBestGameVotingTable.BEST_GAME_TABLE,
                        ConstBestGameVotingTable.USERS_ID
                )){
                    databaseManager.updateVoteUser(resultVoting.getId(),
                            ConstBestGameVotingTable.BEST_GAME_TABLE,
                            ConstBestGameVotingTable.NAME);
                }
                else{
                    databaseManager.voteNewUser(player.getUserId(),
                            resultVoting.getId(),
                            ConstBestGameVotingTable.BEST_GAME_TABLE,
                            ConstBestGameVotingTable.USERS_ID,
                            ConstBestGameVotingTable.NAME);
                }
                switchPage.goVotingBestGameResult(bestGameVoting);
//                new SceneSwitch(bestGameVoting, "view/active_voting/voting_result/best_game_result.fxml");
            } catch (SQLException | ClassNotFoundException e){
                warningLabel.setText("Oops...Something went wrong");
            }
        }
    }


    public void goVoting() throws IOException {
        switchPage.goVotingPageChange(bestGameVoting);
    }
}
