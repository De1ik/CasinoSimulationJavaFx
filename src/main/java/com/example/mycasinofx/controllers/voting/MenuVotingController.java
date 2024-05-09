package com.example.mycasinofx.controllers.voting;

import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.constants.ConstBestGameVotingTable;
import com.example.mycasinofx.Model.database.constants.ConstNewGameVotingTable;
import com.example.mycasinofx.Model.Player;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller class for the menu voting scene.
 */
public class MenuVotingController {

    /**
     * The AnchorPane for the menu voting scene.
     */
    @FXML
    private AnchorPane menuVoting;

    /**
     * Instance of SwitchPage for handling scene navigation.
     */
    private SwitchPage switchPage;

    /**
     * Instance of the Player class representing the current player.
     */
    private Player player;

    /**
     * Initializes the controller.
     * Retrieves the current player and initializes the SwitchPage instance.
     */
    public void initialize(){
        player = Player.getPlayer();
        switchPage = new SwitchPage();
    }

    /**
     * Handles the action to navigate to the voting page for a new game.
     * If the player is not logged in, redirects to the login page.
     * If the player has already voted for a new game, navigates to the voting result page.
     * Otherwise, navigates to the voting page for a new game.
     * @throws IOException if an error occurs during scene switching
     * @throws SQLException if an SQL exception occurs
     * @throws ClassNotFoundException if a required class is not found
     */
    public void goNewGame() throws IOException, SQLException, ClassNotFoundException {
        if (player.getUsername() == null) {
            switchPage.goLogin(menuVoting);
        }
        else{
            if (DAOPattern.voteCheckNewUser(player.getUserId(),
                    ConstNewGameVotingTable.NEW_GAME_TABLE,
                    ConstNewGameVotingTable.USERS_ID
            )){
                switchPage.goVotingNewGameResult(menuVoting);
            }
            else {
                switchPage.goVotingNewGamePageChange(menuVoting);
            }
        }
    }

    /**
     * Handles the action to navigate to the voting page for the best game.
     * If the player is not logged in, redirects to the login page.
     * If the player has already voted for the best game, navigates to the voting result page.
     * Otherwise, navigates to the voting page for the best game.
     * @throws IOException if an error occurs during scene switching
     * @throws SQLException if an SQL exception occurs
     * @throws ClassNotFoundException if a required class is not found
     */
    public void goBestGame() throws IOException, SQLException, ClassNotFoundException {
        if (player.getUsername() == null) {
            switchPage.goLogin(menuVoting);
        }
        else{
            if (DAOPattern.voteCheckNewUser(player.getUserId(),
                    ConstBestGameVotingTable.BEST_GAME_TABLE,
                    ConstBestGameVotingTable.USERS_ID
                    )){
                switchPage.goVotingBestGameResult(menuVoting);
            }
            else {
                switchPage.goVotingBestGamePageChange(menuVoting);
            }
        }
    }

    /**
     * Handles the action to navigate back to the main menu.
     * @throws IOException if an error occurs during scene switching
     */
    public void goMainMenu() throws IOException {
        switchPage.goMainMenu(menuVoting);
    }
}
