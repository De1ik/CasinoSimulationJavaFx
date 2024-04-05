package com.example.mycasinofx.controllers.games.usefulComponent;

import com.example.mycasinofx.Model.player.Player;
import javafx.scene.control.Label;

public class ResultMessage {

    public static void updateMessageLabel(Label messageLabel, Label balanceLabel, Player player) {
        balanceLabel.setText("" + player.getBalance());
        if (player.getProfit() > 0) {
            messageLabel.setText("You won: " + player.getProfit());
            messageLabel.setStyle("-fx-border-color: #1a5709; -fx-text-fill: #1a5709");
            balanceLabel.setStyle("-fx-border-color: #1a5709; -fx-text-fill: #1a5709");
        } else if (player.getProfit() < 0) {
            messageLabel.setText("You lose: " + (player.getProfit() * (-1)));
            messageLabel.setStyle("-fx-border-color: #750808;  -fx-text-fill: #750808");
            balanceLabel.setStyle("-fx-border-color: #750808;  -fx-text-fill: #750808");
        } else {
            messageLabel.setText("You're left with the same balance");
            messageLabel.setStyle("-fx-border-color: #ffffff; -fx-text-fill: white");
            balanceLabel.setStyle("-fx-border-color: #ffffff; -fx-text-fill: white");
        }
    }
}
