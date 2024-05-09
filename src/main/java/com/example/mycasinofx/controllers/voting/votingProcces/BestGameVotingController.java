package com.example.mycasinofx.controllers.voting.votingProcces;

import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.constants.ConstBestGameVotingTable;
import com.example.mycasinofx.Model.Player;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller class for the best game voting scene.
 */
public class BestGameVotingController {

    /**
     * The AnchorPane for the best game voting scene.
     */
    @FXML
    private AnchorPane bestGameVoting;

    /**
     * ToggleGroup for radio buttons representing voting options.
     */
    private ToggleGroup toggleGroup;

    /**
     * RadioButton for voting option "Roulette".
     */
    @FXML
    private RadioButton radioRoulette;

    /**
     * RadioButton for voting option "Twenty One".
     */
    @FXML
    private RadioButton radioTwentyOne;

    /**
     * RadioButton for voting option "Slot".
     */
    @FXML
    private RadioButton radioSlot;

    /**
     * RadioButton for displaying the selected voting result.
     */
    @FXML
    private RadioButton resultVoting;

    /**
     * Label for displaying warnings or messages to the user.
     */
    @FXML
    private Label warningLabel;

    /**
     * Instance of SwitchPage for handling scene navigation.
     */
    private SwitchPage switchPage;

    /**
     * Initializes the controller.
     * Sets up the ToggleGroup for radio buttons and initializes SwitchPage instance.
     */
    public void initialize(){
        switchPage = new SwitchPage();
        toggleGroup = new ToggleGroup();
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

    /**
     * Handles the action of submitting the vote.
     * Retrieves the current player and checks if a voting option is selected.
     * If a vote is submitted successfully, navigates to the voting result page.
     * Displays a warning message if an error occurs during voting.
     * @throws IOException if an error occurs during scene switching
     */
    public void vote() throws IOException{
        Player player = Player.getPlayer();
        if (resultVoting == null){
            warningLabel.setText("Choose the variant");
        }
        else{
            try{
                if (DAOPattern.voteCheckNewUser(player.getUserId(),
                        ConstBestGameVotingTable.BEST_GAME_TABLE,
                        ConstBestGameVotingTable.USERS_ID
                )){
                    DAOPattern.updateVoteUser(resultVoting.getId(),
                            ConstBestGameVotingTable.BEST_GAME_TABLE,
                            ConstBestGameVotingTable.NAME, player.getUserId());
                }
                else{
                    DAOPattern.voteNewUser(player.getUserId(),
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

    /**
     * Handles the action of navigating back to the main voting page.
     * @throws IOException if an error occurs during scene switching
     * @throws SQLException if an SQL exception occurs
     * @throws ClassNotFoundException if a required class is not found
     */
    public void goVoting() throws IOException, SQLException, ClassNotFoundException {
        switchPage.goVotingPageChange(bestGameVoting);
    }
}
