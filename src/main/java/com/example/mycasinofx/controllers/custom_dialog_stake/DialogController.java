package com.example.mycasinofx.controllers.custom_dialog_stake;

import com.example.mycasinofx.Model.games.Games;
import com.example.mycasinofx.Model.observeImplementation.Observer;
import com.example.mycasinofx.Model.observeImplementation.ObserverArray;
import com.example.mycasinofx.Model.player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DialogController {
    @FXML
    AnchorPane dialog_anchor;
    @FXML
    Label balanceLabel, warningLabel;
    @FXML
    TextField newStakeField;





    private ObserverArray observerArray = new ObserverArray();
    private final Player player = Player.getPlayer();

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







    @FXML
    private void closeDialog() {
        // Получаем Stage, в котором находится AnchorPane
        dialog_anchor.getChildren().clear();
        dialog_anchor.setVisible(false);

        observerArray.informAll(-1);
    }


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

    public void subscribe(Observer follower) {
        this.observerArray.subscribe(follower);
    }


    public void updateBalanceLabel(){
        balanceLabel.setText("Current Balance: " + player.getBalance());
    }

}
