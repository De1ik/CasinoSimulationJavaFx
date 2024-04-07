package com.example.mycasinofx.controllers.mainMenu;

import com.example.mycasinofx.Model.FxModels.NumericInputDialog;
import com.example.mycasinofx.Model.games.Games;
import com.example.mycasinofx.Model.player.Player;
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

public class MainMenuLoginController {
    @FXML
    private AnchorPane mainMenuLogin, dialogWindow;
    @FXML
    private Label setBalanceViewLabel, stakeLabelView, stakeErrorSuccess;
    @FXML
    private Button exitButton;
    @FXML
    private BorderPane borderPane;
    private Player player;
    private PageSwitchInterface pageSwitch;

    public void initialize() {
        player = Player.getPlayer();
        pageSwitch = new SwitchPage();
        exitButton.setOnAction(e -> pageSwitch.confirmExit());
        dialogWindow.setMouseTransparent(true);
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

    @FXML
    public void goPlay21() throws IOException{
        pageSwitch.goPlay21(mainMenuLogin);
    }

    public void goVoting() throws IOException {
        pageSwitch.goVotingPageChange(mainMenuLogin);
    }


    @FXML
    public void setBalanceViewLabel(){
        Platform.runLater(() -> setBalanceViewLabel.setText("Balance: " + player.getBalance()));
    }
    @FXML
    public void setStakeViewLabel(){
        Platform.runLater(() -> stakeLabelView.setText("Stake: " + player.getCurrentStake()));
    }
    @FXML
    public void setStakeErrorSuccess(String result, boolean success){
        Platform.runLater(() -> {
            stakeErrorSuccess.setText(result);
            if (success)stakeErrorSuccess.setTextFill(Color.GREEN);
            else stakeErrorSuccess.setTextFill(Color.RED);
        });

    }


//    @FXML
//    private void setStakeDialog() {
//        NumericInputDialog dialog = new NumericInputDialog("Set The Current Stake", "Enter the number:", "Stake is:");
//        dialog.showNumericInputDialog().ifPresent(value -> {
//            value = Math.round(value * 100.0) / 100.0;
//            if ((player.getBalance() >= value) && (value > Games.getMinimumStake()) && (value < Games.getMaximumStake())) {
//                player.setCurrentStake(value);
//                setStakeErrorSuccess("The Stake Was Changed", true);
//                setStakeViewLabel();
//            }
//            else {
//                if (value < Games.getMinimumStake()) setStakeErrorSuccess("The minimum Stake is " + Games.getMinimumStake(), false);
//                else if (value > Games.getMaximumStake()) setStakeErrorSuccess("The maximum Stake is " + Games.getMaximumStake(), false);
//                else setStakeErrorSuccess("You Do Not Have Money!", false);
//            }
//        });
//    }

    public void doStake() throws IOException {
        SetStakeCustomDialog.doStake(borderPane, dialogWindow, stakeErrorSuccess, stakeLabelView);
    }
}
