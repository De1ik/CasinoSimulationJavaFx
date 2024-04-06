package com.example.mycasinofx.controllers.games.twentyOne;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.Model.games.Slots.Slots;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.controllers.custom_dialog_stake.SetStakeCustomDialog;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Start21Controller implements Initializable {

    private PageSwitchInterface pageSwitch;
    @FXML
    AnchorPane anchor21, dialogWindow;

    @FXML
    Label balanceLabel, amountStake, warningsLabel;

    @FXML
    BorderPane borderPane;

    private Player player;
    private double profit;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        dialogWindow.setMouseTransparent(true);
        player = Player.getPlayer();
        pageSwitch = new SwitchPage();
    }

    public void go21() throws IOException {
        if (player.tryDoStake()) {
            player.setBalance(player.getBalance() - player.getCurrentStake());
            pageSwitch.go21(anchor21);
        }
        else{
            warningsLabel.setText("Insufficient Balance");
        }


        pageSwitch.go21(anchor21);
    }

    public void goMainMenu() throws IOException {
        pageSwitch.goMainMenu(anchor21);
    }

    public void updateStakeBalanceLabels(){
        balanceLabel.setText("Balance:  " + player.getBalance());
        amountStake.setText("Amount of Stake: " + player.getCurrentStake());

    }
    public void doStake() throws IOException {
        SetStakeCustomDialog.doStake(borderPane, dialogWindow, warningsLabel, amountStake);
    }
}
