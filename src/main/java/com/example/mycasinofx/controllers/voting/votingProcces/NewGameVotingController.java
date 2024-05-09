package com.example.mycasinofx.controllers.voting.votingProcces;

import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.constants.ConstNewGameVotingTable;
import com.example.mycasinofx.Model.Player;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller class for the new game voting scene.
 */
public class NewGameVotingController {

    /**
     * The AnchorPane for the new game voting scene.
     */
    @FXML
    private AnchorPane newGameVoting;

    /**
     * ToggleGroup for radio buttons representing voting options.
     */
    private ToggleGroup toggleGroup;

    /**
     * RadioButton for voting option "Backarat".
     */
    @FXML
    private RadioButton radioBackarat;

    /**
     * RadioButton for voting option "Poker".
     */
    @FXML
    private RadioButton radioPoker;

    /**
     * RadioButton for voting option "Durak".
     */
    @FXML
    private RadioButton radioDurak;

    /**
     * RadioButton for voting option "Other".
     */
    @FXML
    private RadioButton radioOther;

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
     * TextField for entering an alternative voting option.
     */
    @FXML
    private TextField otherTextField;

    /**
     * Instance of SwitchPage for handling scene navigation.
     */
    private SwitchPage switchPage;

    /**
     * Initializes the controller.
     * Sets up the ToggleGroup for radio buttons, hides the otherTextField,
     * and initializes SwitchPage instance.
     */
    public void initialize(){
        switchPage = new SwitchPage();
        toggleGroup = new ToggleGroup();
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

    /**
     * Handles the action of submitting the vote.
     * Retrieves the current player and checks if a voting option is selected.
     * If a vote is submitted successfully, navigates to the voting result page.
     * Displays a warning message if an error occurs during voting.
     * @throws IOException if an error occurs during scene switching
     */
    public void vote() throws IOException{
        Player player = Player.getPlayer();
        int res = 1;
        if (resultVoting == null){
            warningLabel.setText("Choose the variant");
        }
        else{
            try{
                String resultToDb = resultVoting.getId();
                if (resultToDb.equals(radioOther.getId())){
                    resultToDb = otherTextField.getText().trim();
                    if (resultToDb.isEmpty()){
                        warningLabel.setText("Write what you want to add");
                        res = -1;
                    }
                }
                else{
                    resultToDb = resultVoting.getId();
                }

                if (res != -1) {
                    if (DAOPattern.voteCheckNewUser(player.getUserId(),
                            ConstNewGameVotingTable.NEW_GAME_TABLE,
                            ConstNewGameVotingTable.USERS_ID
                    )) {
                        DAOPattern.updateVoteUser(resultToDb,
                                ConstNewGameVotingTable.NEW_GAME_TABLE,
                                ConstNewGameVotingTable.NAME, player.getUserId());
                    } else {
                        DAOPattern.voteNewUser(player.getUserId(),
                                resultVoting.getId(),
                                ConstNewGameVotingTable.NEW_GAME_TABLE,
                                ConstNewGameVotingTable.USERS_ID,
                                ConstNewGameVotingTable.NAME);
                    }
                    switchPage.goVotingNewGameResult(newGameVoting);
                }
            } catch (SQLException | ClassNotFoundException e){
                warningLabel.setText("Oops...Something went wrong");
            }
        }
    }

    /**
     * Handles the action of navigating back to the main voting page.
     * @throws IOException if an error occurs during scene switching
     */
    public void goVoting() throws IOException {
        switchPage.goVotingPageChange(newGameVoting);
    }
}
