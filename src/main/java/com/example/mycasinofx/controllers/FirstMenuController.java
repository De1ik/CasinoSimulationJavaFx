package com.example.mycasinofx.controllers;

import com.example.mycasinofx.Model.FxModels.SceneSwitch;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstMenuController {

    private Stage stage;

    @FXML
    private AnchorPane firstAnchor, mainPaneNotChange, greenPain;
    private final Player player = Player.getPlayer();
    private final SwitchPage switchPage = new SwitchPage();

    @FXML
    public void goListGames() throws IOException {
        switchPage.goMainMenu(firstAnchor);
    }

    @FXML
    public void exitCasino(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Casino");
        alert.setHeaderText("You want to exit Casino");
        alert.setContentText("Are you sure?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }
    }



    public AnchorPane getMainAnchor(){
        return mainPaneNotChange;
    }

    public AnchorPane getGreenPain(){
        return greenPain;
    }

}