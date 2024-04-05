package com.example.mycasinofx.controllers.switchPage;

import com.example.mycasinofx.Model.FxModels.SceneSwitch;
import com.example.mycasinofx.Model.player.Player;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

import java.io.IOException;
import java.sql.SQLException;

public class SwitchPage implements PageSwitchInterface{


    @Override
    public void goMainMenu(AnchorPane anchorPane) throws IOException {
        Player player = Player.getPlayer();
        if (player.getUsername() == null) {
            new SceneSwitch(anchorPane, "view/main_menu/main_menu_not_login.fxml");
        }
        else{
            new SceneSwitch(anchorPane, "view/main_menu/main_menu_login.fxml");
        }
    }
    @Override
    public void goLogin(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/Registration/login.fxml");
    }

    @Override
    public void goRegister(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/Registration/register_page.fxml");
    }

    @Override
    public void goSlots(AnchorPane anchorPane) throws IOException {
        checkLoginUser(anchorPane, "view/Games/slot/slots.fxml");
    }

    @Override
    public void go21(AnchorPane anchorPane) throws IOException {
        checkLoginUser(anchorPane, "view/Games/twenty_one/twenty_one.fxml");
    }

    @Override
    public void goRoulette(AnchorPane anchorPane) throws IOException {
        checkLoginUser(anchorPane, "view/Games/roulette/roulette.fxml");
    }

    @Override
    public void goRouletteResult(AnchorPane anchorPane) throws IOException {
        checkLoginUser(anchorPane, "view/Games/roulette/rouletteResult.fxml");
    }

    @Override
    public void goFullList(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/fullListGames.fxml");
    }

    @Override
    public void goVotingPageChange(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/active_voting/menu_voting.fxml");
    }

    @Override
    public void goVotingBestGamePageChange(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/active_voting/voting_procces/best_game_voting.fxml");
    }

    @Override
    public void goVotingNewGamePageChange(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/active_voting/voting_procces/new_game_voting.fxml");
    }

    @Override
    public void goVotingNewGameResult(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/active_voting/voting_result/new_game_result.fxml");
    }

    @Override
    public void goVotingBestGameResult(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/active_voting/voting_result/best_game_result.fxml");
    }


    @Override
    public void confirmExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Casino");
        alert.setHeaderText("You want to exit Casino");
        alert.setContentText("Are you sure?");

        alert.initModality(Modality.APPLICATION_MODAL);

        if (alert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @Override
    public void checkLoginUser(AnchorPane anchorPane, String path) throws IOException {
        Player player = Player.getPlayer();
        if (player.getUsername() == null) {
            new SceneSwitch(anchorPane, "view/Registration/login.fxml");
        }
        else{
            new SceneSwitch(anchorPane, path);
        }
    }
}
