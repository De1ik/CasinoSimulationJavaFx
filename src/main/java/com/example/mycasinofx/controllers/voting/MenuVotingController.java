package com.example.mycasinofx.controllers.voting;

import com.example.mycasinofx.Model.FxModels.SceneSwitch;
import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.DatabaseManager;
import com.example.mycasinofx.Model.database.constants.ConstBestGameVotingTable;
import com.example.mycasinofx.Model.database.constants.ConstNewGameVotingTable;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class MenuVotingController {

    @FXML
    AnchorPane menuVoting;
    private final SwitchPage switchPage = new SwitchPage();
    private final Player player = Player.getPlayer();
    private final DatabaseManager databaseManager = new DatabaseManager();


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

    public void goMainMenu() throws IOException {
        switchPage.goMainMenu(menuVoting);
    }
}
