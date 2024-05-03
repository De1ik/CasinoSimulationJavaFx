package com.example.mycasinofx.controllers.games.usefulComponent;

import com.example.mycasinofx.Model.player.Player;
import javafx.scene.control.Label;

public class ResultMessage {

    public static void updateMessageLabel(Label messageLabel, Label balanceLabel, Player player) {
        balanceLabel.setText("" + player.getBalance());
        if (player.getProfit() > 0) {
            messageLabel.setText("You won: " + player.getProfit());
            messageLabel.setStyle("-fx-border-color: #34ff00; -fx-text-fill: #ffffff");
            balanceLabel.setStyle("-fx-border-color: #38ff00; -fx-text-fill: #ffffff");
        } else if (player.getProfit() < 0) {
            messageLabel.setText("You lose: " + (player.getProfit() * (-1)));
            messageLabel.setStyle("-fx-border-color: #ff0000;  -fx-text-fill: #ffffff");
            balanceLabel.setStyle("-fx-border-color: #ff0000;  -fx-text-fill: #ffffff");
        } else {
            messageLabel.setText("You're left with the same balance");
            messageLabel.setStyle("-fx-border-color: #ffffff; -fx-text-fill: white");
            balanceLabel.setStyle("-fx-border-color: #ffffff; -fx-text-fill: white");
        }
    }
}
