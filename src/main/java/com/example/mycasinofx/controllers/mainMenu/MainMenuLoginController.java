package com.example.mycasinofx.controllers.mainMenu;

import com.example.mycasinofx.Model.FxModels.NumericInputDialog;
import com.example.mycasinofx.Model.games.Games;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MainMenuLoginController {
    @FXML
    private AnchorPane mainMenuLogin;
    @FXML
    private Label setBalanceViewLabel, stakeLabelView, stakeErrorSuccess;
    @FXML
    private Button exitButton;
    private final Player player = Player.getPlayer();
    private final PageSwitchInterface pageSwitch = new SwitchPage();

    public void initialize() {
        exitButton.setOnAction(e -> pageSwitch.confirmExit());
    }



    public void goFullList() throws IOException {
        pageSwitch.goFullList(mainMenuLogin);
    }

    public void goRoulette() throws IOException{
        pageSwitch.goRoulette(mainMenuLogin);
    }

    @FXML
    public void goSlots() throws IOException{
        pageSwitch.goSlots(mainMenuLogin);
    }

    public void goVoting() throws IOException {
        pageSwitch.goVotingPageChange(mainMenuLogin);
    }



    public void setBalanceViewLabel(){
        Platform.runLater(() -> setBalanceViewLabel.setText("Balance: " + player.getBalance()));
    }
    public void setStakeViewLabel(){
        Platform.runLater(() -> stakeLabelView.setText("Stake: " + player.getCurrentStake()));
    }
    public void setStakeErrorSuccess(String result, boolean success){
        Platform.runLater(() -> {
            stakeErrorSuccess.setText(result);
            if (success)stakeErrorSuccess.setTextFill(Color.GREEN);
            else stakeErrorSuccess.setTextFill(Color.RED);
        });

    }


    @FXML
    private void setStakeDialog() {
        NumericInputDialog dialog = new NumericInputDialog("Set The Current Stake", "Enter the number:", "Stake is:");
        dialog.showNumericInputDialog().ifPresent(value -> {
            value = Math.round(value * 100.0) / 100.0;
            if ((player.getBalance() >= value) && (value > Games.getMinimumStake()) && (value < Games.getMaximumStake())) {
                player.setCurrentStake(value);
                setStakeErrorSuccess("The Stake Was Changed", true);
                setStakeViewLabel();
            }
            else {
                if (value < Games.getMinimumStake()) setStakeErrorSuccess("The minimum Stake is " + Games.getMinimumStake(), false);
                else if (value > Games.getMaximumStake()) setStakeErrorSuccess("The maximum Stake is " + Games.getMaximumStake(), false);
                else setStakeErrorSuccess("You Do Not Have Money!", false);
            }
        });
    }
}
