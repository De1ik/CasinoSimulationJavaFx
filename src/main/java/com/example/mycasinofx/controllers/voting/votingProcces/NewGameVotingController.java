package com.example.mycasinofx.controllers.voting.votingProcces;

import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.DatabaseManager;
import com.example.mycasinofx.Model.database.constants.ConstBestGameVotingTable;
import com.example.mycasinofx.Model.database.constants.ConstNewGameVotingTable;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class NewGameVotingController {

    @FXML
    AnchorPane newGameVoting;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    RadioButton radioBackarat, radioPoker, radioDurak, radioOther, resultVoting;
    @FXML
    Label warningLabel;
    @FXML
    TextField otherTextField;

    private final DatabaseManager databaseManager = new DatabaseManager();
    private final SwitchPage switchPage = new SwitchPage();


    public void initialize(){
        radioBackarat.setToggleGroup(toggleGroup);
        radioPoker.setToggleGroup(toggleGroup);
        radioDurak.setToggleGroup(toggleGroup);
        radioOther.setToggleGroup(toggleGroup);
        warningLabel.setText("");

        otherTextField.setVisible(false);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (toggleGroup.getSelectedToggle() != null) {
                resultVoting = (RadioButton) toggleGroup.getSelectedToggle();

                if (resultVoting.getId().equals("radioOther")){
                    otherTextField.setVisible(true);
                }
                else{
                    otherTextField.setVisible(false);
                }
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
                String resultToDb = resultVoting.getId();
                if (resultToDb.equals(radioOther.getId())){
                    resultToDb = otherTextField.getText().trim();
                }
                else {
                    resultToDb = resultVoting.getId();
                }

                if (DAOPattern.voteCheckNewUser(player.getUserId(),
                        ConstNewGameVotingTable.NEW_GAME_TABLE,
                        ConstNewGameVotingTable.USERS_ID
                )){
                    DAOPattern.updateVoteUser(resultToDb,
                            ConstNewGameVotingTable.NEW_GAME_TABLE,
                            ConstNewGameVotingTable.NAME, player.getUserId());
                }
                else{
                    DAOPattern.voteNewUser(player.getUserId(),
                            resultVoting.getId(),
                            ConstNewGameVotingTable.NEW_GAME_TABLE,
                            ConstNewGameVotingTable.USERS_ID,
                            ConstNewGameVotingTable.NAME);
                }
                switchPage.goVotingNewGameResult(newGameVoting);
            } catch (SQLException | ClassNotFoundException e){
                warningLabel.setText("Oops...Something went wrong");
            }
        }
    }


    public void goVoting() throws IOException {
        switchPage.goVotingPageChange(newGameVoting);
    }
}
