package com.example.mycasinofx.controllers.mainMenu;


import com.example.mycasinofx.Model.Player;
import com.example.mycasinofx.controllers.custom_dialog_stake.SetStakeCustomDialog;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * Controller class for the main menu and for users who have registered. .
 */
public class MainMenuLoginController {
    /**
     * The anchor pane for the main menu.
     */
    @FXML
    private AnchorPane mainMenuLogin;

    /**
     * The anchor pane for the dialog window.
     */
    @FXML
    private AnchorPane dialogWindow;

    /**
     * The label displaying the current balance.
     */
    @FXML
    private Label setBalanceViewLabel;

    /**
     * The label displaying the current stake.
     */
    @FXML
    private Label stakeLabelView;

    /**
     * The label for displaying stake-related error or success messages.
     */
    @FXML
    private Label stakeErrorSuccess;

    /**
     * The button for exiting the application.
     */
    @FXML
    private Button exitButton;

    /**
     * The border pane containing the main content.
     */
    @FXML
    private BorderPane borderPane;

    /**
     * The player object representing the current player.
     */
    private Player player;

    /**
     * Interface for switching pages.
     */
    private PageSwitchInterface pageSwitch;

    /**
     * Initializes the controller.
     */
    public void initialize() {
        player = Player.getPlayer();
        pageSwitch = new SwitchPage();
        exitButton.setOnAction(e -> pageSwitch.confirmExit());
        dialogWindow.setMouseTransparent(true);
    }

    /**
     * Navigates to the roulette game page.
     * @throws IOException If an I/O error occurs.
     */
    public void goRoulette() throws IOException{
        pageSwitch.goRoulette(mainMenuLogin);
    }

    /**
     * Navigates to the slots game page.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void goSlots() throws IOException{
        pageSwitch.goSlots(mainMenuLogin);
    }

    /**
     * Navigates to the 21 game page.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void goPlay21() throws IOException{
        pageSwitch.goPlay21(mainMenuLogin);
    }

    /**
     * Navigates to the voting page.
     * @throws IOException If an I/O error occurs.
     */
    public void goVoting() throws IOException {
        pageSwitch.goVotingPageChange(mainMenuLogin);
    }

    /**
     * Updates the balance view label with the current player balance.
     */
    @FXML
    public void setBalanceViewLabel(){
        Platform.runLater(() -> setBalanceViewLabel.setText("Balance: " + player.getBalance()));
    }

    /**
     * Updates the stake view label with the current player stake.
     */
    @FXML
    public void setStakeViewLabel(){
        Platform.runLater(() -> stakeLabelView.setText("Stake: " + player.getCurrentStake()));
    }

    /**
     * Sets the stake error or success message.
     * @param result The message to display.
     * @param success Whether it's a success message or not.
     */
    @FXML
    public void setStakeErrorSuccess(String result, boolean success){
        Platform.runLater(() -> {
            stakeErrorSuccess.setText(result);
            if (success)stakeErrorSuccess.setTextFill(Color.GREEN);
            else stakeErrorSuccess.setTextFill(Color.RED);
        });

    }

    /**
     * Executes the stake setting dialog.
     * @throws IOException If an I/O error occurs.
     */
    public void doStake() throws IOException {
        SetStakeCustomDialog.doStake(borderPane, dialogWindow, stakeErrorSuccess, stakeLabelView);
    }
}
