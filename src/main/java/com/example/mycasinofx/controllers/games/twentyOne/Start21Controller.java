package com.example.mycasinofx.controllers.games.twentyOne;

import com.example.mycasinofx.Model.Player;
import com.example.mycasinofx.controllers.custom_dialog_stake.SetStakeCustomDialog;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for managing the UI of the Start21 game.
 */
public class Start21Controller implements Initializable {

    /**
     * Interface for switching pages in the application.
     */
    private PageSwitchInterface pageSwitch;

    /**
     * Anchor pane containing the UI elements for the Start21 game page.
     */
    @FXML
    AnchorPane anchor21;

    /**
     * Dialog window for custom stake input.
     */
    @FXML
    AnchorPane dialogWindow;

    /**
     * Label displaying the player's balance.
     */
    @FXML
    Label balanceLabel;

    /**
     * Label displaying the amount of stake placed by the player.
     */
    @FXML
    Label amountStake;

    /**
     * Label for displaying warnings or notifications.
     */
    @FXML
    Label warningsLabel;

    /**
     * Border pane containing the main layout of the game.
     */
    @FXML
    BorderPane borderPane;

    /**
     * The player object associated with the current game session.
     */
    private Player player;

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up initial configurations and disables the dialog window.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dialogWindow.setMouseTransparent(true);
        player = Player.getPlayer();
        pageSwitch = new SwitchPage();
    }

    /**
     * Navigates to the Start21 game page.
     * If the player has sufficient balance, navigates to the game page; otherwise, displays a warning.
     * @throws IOException If an I/O exception occurs.
     */
    public void go21() throws IOException {
        if (player.tryDoStake()) {
            pageSwitch.go21(anchor21);
        }
        else{
            warningsLabel.setText("Insufficient Balance");
        }
    }

    /**
     * Navigates to the main menu.
     * @throws IOException If an I/O exception occurs.
     */
    public void goMainMenu() throws IOException {
        pageSwitch.goMainMenu(anchor21);
    }

    /**
     * Updates the balance and amount of stake labels with the player's current values.
     */
    public void updateStakeBalanceLabels(){
        balanceLabel.setText("Balance:  " + player.getBalance());
        amountStake.setText("Amount of Stake: " + player.getCurrentStake());
    }

    /**
     * Opens a dialog window for the player to input a custom stake amount.
     * @throws IOException If an I/O exception occurs.
     */
    public void doStake() throws IOException {
        SetStakeCustomDialog.doStake(borderPane, dialogWindow, warningsLabel, amountStake);
    }
}
