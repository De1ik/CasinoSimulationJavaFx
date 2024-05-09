package com.example.mycasinofx.controllers.games.usefulComponent;

import com.example.mycasinofx.Model.Player;
import javafx.scene.control.Label;

/**
 * Utility class for updating message and balance labels based on player's result of the game.
 */
public class ResultMessage {

    /**
     * Updates the message and balance labels based on the player's result of the game.
     *
     * @param messageLabel The label to display the result message.
     * @param balanceLabel The label to display the player's balance.
     * @param player The player whose result and balance will be displayed.
     */
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
