package com.example.mycasinofx.controllers.custom_dialog_stake;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.Model.FxModels.Component;
import com.example.mycasinofx.Model.observeImplementation.Observer;
import com.example.mycasinofx.controllers.custom_dialog_stake.DialogController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ResultDialog {


    @FXML
    public static void showGameResultDialog(AnchorPane mainPane, BorderPane borderPane, AnchorPane dialog_window, int currentGameType, int botValue, int playerValue, int result) throws IOException {

        dialog_window.setMouseTransparent(false);

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("view/result_game_dialog/dialog_result.fxml"));
        AnchorPane nextAnchorPane = loader.load();

        DialogControllerResult dialogController = loader.getController();
        dialogController.setMainPane(mainPane);
        dialogController.setFxmlType(currentGameType);
        dialogController.updateBalanceStakeLabel();
        dialogController.setMessageLabel();
        dialogController.update21Result(botValue, playerValue, result);

        borderPane.setDisable(true);

        Component.resize(nextAnchorPane, dialog_window.getPrefWidth(), dialog_window.getPrefHeight());
//
        dialog_window.getChildren().clear();
        dialog_window.getChildren().add(nextAnchorPane);
    }
}
