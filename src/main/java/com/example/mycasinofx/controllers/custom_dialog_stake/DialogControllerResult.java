package com.example.mycasinofx.controllers.custom_dialog_stake;

import com.example.mycasinofx.Model.Player;
import com.example.mycasinofx.controllers.games.usefulComponent.ResultMessage;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


/**
 * The DialogControllerResult class controls the dialog window displaying the result of a game.
 * It manages updating result information, displaying result messages.
 */
public class DialogControllerResult {

    /**
     * The anchor pane of the dialog window.
     */
    @FXML
    AnchorPane dialog_anchor;

    /**
     * The label displaying the current balance.
     */
    @FXML
    Label balanceLabel;

    /**
     * The label displaying the stake amount.
     */
    @FXML
    Label amountStake;

    /**
     * The label displaying the game result.
     */
    @FXML
    Label resultLabel;

    /**
     * The label displaying the bot's value.
     */
    @FXML
    Label botValue;

    /**
     * The label displaying the player's value.
     */
    @FXML
    Label playerValue;

    /**
     * The main label displaying the overall game result (Win or Lose).
     */
    @FXML
    Label bigLabelResult;

    /**
     * The main pane containing the dialog.
     */
    @FXML
    private AnchorPane mainPane;

    /**
     * An integer indicating the type of game to play again.
     */
    private int playAgainFxml;

    /**
     * Interface for switching pages.
     */
    private PageSwitchInterface pageSwitch;

    /**
     * The player object representing the current player.
     */
    private Player player;

    /**
     * Initializes the controller.
     * Creates a new page switch and get the player instance.
     */
    public void initialize(){
        pageSwitch = new SwitchPage();
        player = Player.getPlayer();

    }

    /**
     * Updates the dialog window with the result of a game of 21.
     * @param botValueAmn The value of the bot's hand.
     * @param playerValueAmn The value of the player's hand.
     * @param result The result of the game (1 for player win, 2 for player lose, 0 for draw).
     */
    public void update21Result(int botValueAmn, int playerValueAmn, int result){
        dialog_anchor.getStyleClass().clear();
        if (result == 1){
            dialog_anchor.getStyleClass().add("result-21-dialog-winner");
            bigLabelResult.setText("You Won");
        }
        else if (result == 2){
            dialog_anchor.getStyleClass().add("result-21-dialog-lose");
            bigLabelResult.setText("You Lose");
        }
        else{
            dialog_anchor.getStyleClass().add("result-21-dialog-draw");
            bigLabelResult.setText("Draw");
        }
        botValue.setText("Bot Value: " + botValueAmn);
        playerValue.setText("Your Value: " + playerValueAmn);
    }

    /**
     * Sets the main pane of the dialog.
     * @param mainPane The main pane to set.
     */
    public void setMainPane(AnchorPane mainPane) {
        this.mainPane = mainPane;
    }

    /**
     * Sets the type of game to play again.
     * @param curGame The type of game to play again.
     */
    public void setFxmlType(int curGame) {
        this.playAgainFxml = curGame;
    }

    /**
     * Handles the action of playing the game again.
     * @throws IOException If an I/O error occurs.
     */
    public void playAgain() throws IOException {
        if (mainPane != null && playAgainFxml != -1){
            switch (playAgainFxml){
                case 1:
                    pageSwitch.goRoulette(mainPane);
                    break;
                case 2:
                    pageSwitch.goSlots(mainPane);
                    break;
                case 3:
                    pageSwitch.goPlay21(mainPane);
                    break;
            }
        }
    }

    /**
     * Handles the action of going back to the main menu.
     * @throws IOException If an I/O error occurs.
     */
    public void goMainMenu() throws IOException {
        if (mainPane != null){
            pageSwitch.goMainMenu(mainPane);
        }
    }



    /**
     * Updates the balance and stake labels with current values.
     */
    public void updateBalanceStakeLabel(){
        balanceLabel.setText("Current Balance: " + player.getBalance());
        amountStake.setText("Current Stake: " + player.getCurrentStake());
    }

    /**
     * Sets the message label based on the game result.
     */
    public void setMessageLabel() {
        ResultMessage.updateMessageLabel(resultLabel, balanceLabel, player);
    }

}
