package com.example.mycasinofx.controllers.custom_dialog_stake;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.Model.FxModels.Component;
import com.example.mycasinofx.Model.observeImplementation.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Utility class for setting custom stakes in dialog window.
 */
public class SetStakeCustomDialog {

    /**
     * Executes the stake setting dialog.
     * @param borderPane The border pane containing the main content.
     * @param dialog_window The anchor pane representing the dialog window.
     * @param warningsLabel The label for displaying warning messages.
     * @param amountStake The label for displaying the stake amount.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public static void doStake(BorderPane borderPane, AnchorPane dialog_window, Label warningsLabel, Label amountStake) throws IOException {

        dialog_window.setMouseTransparent(false);

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("view/do_stake_dialog/dialog_window.fxml"));
        AnchorPane nextAnchorPane = loader.load();

        DialogController dialogController = loader.getController();
        dialogController.updateBalanceLabel();
        dialogController.subscribe(
                new Observer((double number) -> {
                    borderPane.setDisable(false);
                    dialog_window.setMouseTransparent(true);
                    if (number > -1){
                        warningsLabel.setText("The Stake Was Changed");
                        amountStake.setText("Stake: " + number);
                    }
                }));

        borderPane.setDisable(true);

        Component.resize(nextAnchorPane, dialog_window.getPrefWidth(), dialog_window.getPrefHeight());

        dialog_window.getChildren().clear();
        dialog_window.getChildren().add(nextAnchorPane);
    }
}
