package com.example.mycasinofx.controllers.mainMenu;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.Model.FxModels.Component;
import com.example.mycasinofx.Model.FxModels.NumericInputDialog;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.Model.FxModels.SceneSwitch;
import com.example.mycasinofx.Model.games.Games;
import javafx.application.Platform;
import javafx.fxml.FXML;


import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class MainMenuController {


    @FXML
    private AnchorPane mainMenuAnchor, mainMenuChangeAnchor;
    @FXML
    private Label setBalanceViewLabel, stakeLabelView, stakeErrorSuccess;
    private final Player player = Player.getPlayer();


//    public void setAnchor() throws IOException {
//        Player player = Player.getPlayer();
//        FXMLLoader loader;
////        AnchorPane nextAnchorPane;
//        if (player.getUsername() == null) {
//            loader = new FXMLLoader(Application.class.getResource("view/Registration/not_login_user.fxml"));
//            nextAnchorPane = loader.load();
////            nextAnchorPane = (AnchorPane) loader.getNamespace().get("loginAnchorMicro");
////            System.out.println(nextAnchorPane);
//
//        }
//        else{
//            loader = new FXMLLoader(Application.class.getResource("view/Registration/register_page.fxml"));
//            nextAnchorPane = loader.load();
//        }
//
//        Component.resize(nextAnchorPane, mainMenuChangeAnchor.getPrefWidth(), mainMenuChangeAnchor.getPrefHeight());
//        mainMenuChangeAnchor.getChildren().clear();
//        mainMenuChangeAnchor.getChildren().add(nextAnchorPane);
//    }

    public void goFullList() throws IOException{
        new SceneSwitch(mainMenuAnchor, "view/fullListGames.fxml");
    }

    public void goRoulette() throws IOException{
        checkLoginUser("view/Games/roulette.fxml");
    }

    @FXML
    public void goSlots() throws IOException{
        checkLoginUser("view/Games/slots.fxml");
    }

    @FXML
    public void goLogin() throws IOException{
        new SceneSwitch(mainMenuAnchor, "view/Registration/login.fxml");
    }

    @FXML
    public void goRegister() throws IOException{
        new SceneSwitch(mainMenuAnchor, "view/Registration/register_page.fxml");
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


    public void checkLoginUser(String path) throws IOException {
        if (player.getUsername() == null) {
            new SceneSwitch(mainMenuAnchor, "view/Registration/login.fxml");
        }
        else{
            new SceneSwitch(mainMenuAnchor, path);
        }

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

