package com.example.mycasinofx.controllers.custom_dialog_stake;

import com.example.mycasinofx.Model.games.Games;
import com.example.mycasinofx.Model.observeImplementation.Observer;
import com.example.mycasinofx.Model.observeImplementation.ObserverArray;
import com.example.mycasinofx.Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


/**
 * The DialogController class controls the dialog window for setting stakes in the game.
 * It manages user input validation, stake setting, and updating balance information.
 */
public class DialogController {

    /**
     * The anchor pane of the dialog window.
     */
    @FXML
    AnchorPane dialog_anchor;

    /**
     * The label displaying the current balance and warning messages.
     */
    @FXML
    Label balanceLabel, warningLabel;

    /**
     * The text field for entering a new stake.
     */
    @FXML
    TextField newStakeField;




    /**
     * The array to manage observers.
     */
    private ObserverArray observerArray = new ObserverArray();

    /**
     * The player object representing the current player.
     */
    private final Player player = Player.getPlayer();

    /**
     * Initializes the controller.
     * Sets up a listener for the new stake field to ensure valid input.
     */
    public void initialize(){
        if (newStakeField != null) {
            newStakeField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*\\.?\\d{0,2}")) {
                    newStakeField.setText(oldValue);
                }
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    newStakeField.setText(newValue.replaceAll("[^\\d.]", ""));
                } else if (newValue.isEmpty()) {
                    newStakeField.setStyle("-fx-border-color: red;");
                } else if (Double.parseDouble(newValue) < Games.getMinimumStake() || Double.parseDouble(newValue) > Games.getMaximumStake() || Double.parseDouble(newValue) > player.getBalance()) {
                    newStakeField.setStyle("-fx-border-color: red;");
                } else {
                    newStakeField.setStyle("");
                    newStakeField.setStyle("-fx-border-color: #1ddc06;");
                }
            });
        }
    }






    /**
     * Closes the dialog window and informs observers.
     */
    @FXML
    private void closeDialog() {
        // Получаем Stage, в котором находится AnchorPane
        dialog_anchor.getChildren().clear();
        dialog_anchor.setVisible(false);

        observerArray.informAll(-1);
    }


    /**
     * Sets the stake according to the value entered in the new stake field.
     * If the entered value is invalid, displays warning messages.
     */
    @FXML
    private void setStake() {
        String stake = newStakeField.getText();

        if (stake == null || stake.isEmpty()) {
            warningLabel.setText("Enter Amount");
        } else {
            double newStake = Double.parseDouble(stake);
            if (newStake < Games.getMinimumStake()) {
                warningLabel.setText("The minimum stake is " + Games.getMinimumStake());
            } else if (newStake > Games.getMaximumStake()) {
                warningLabel.setText("The maximum Stake is " + Games.getMaximumStake());
            } else if (newStake > player.getBalance()) {
                warningLabel.setText("Insufficient balance");
            } else {
                player.setCurrentStake(newStake);

                dialog_anchor.getChildren().clear();
                dialog_anchor.setVisible(false);

                observerArray.informAll(newStake);
            }
        }
    }


    /**
     * Subscribes a follower to receive updates.
     * @param follower The follower to subscribe.
     */
    public void subscribe(Observer follower) {
        this.observerArray.subscribe(follower);
    }

    /**
     * Updates the balance label with the current player balance.
     */
    public void updateBalanceLabel(){
        balanceLabel.setText("Current Balance: " + player.getBalance());
    }

}
