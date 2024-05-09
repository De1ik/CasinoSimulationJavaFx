package com.example.mycasinofx.controllers.custom_dialog_stake;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.Model.FxModels.Component;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Utility class for showing game result dialogs.
 */
public class ResultDialog {


    /**
     * Shows the game result dialog window.
     *
     * @param mainPane        The main pane of the application.
     * @param borderPane      The border pane containing the main content.
     * @param dialog_window   The anchor pane representing the dialog window.
     * @param currentGameType The type of the current game.
     * @param botValue        The value of the bot's hand.
     * @param playerValue     The value of the player's hand.
     * @param result          The result of the game (1 for player win, 2 for player lose, 0 for draw).
     * @throws IOException If an I/O error occurs.
     */
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
