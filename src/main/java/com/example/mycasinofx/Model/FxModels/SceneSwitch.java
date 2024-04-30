package com.example.mycasinofx.Model.UsefulComponents;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.controllers.Registration.LoginPageController;
import com.example.mycasinofx.controllers.games.slots.SlotsController;
import com.example.mycasinofx.controllers.games.twentyOne.Start21Controller;
import com.example.mycasinofx.controllers.games.twentyOne.TwentyOneController;
import com.example.mycasinofx.controllers.Registration.RegisterController;
import com.example.mycasinofx.controllers.games.roulette.RouletteController;
import com.example.mycasinofx.controllers.games.roulette.RouletteResultController;
import com.example.mycasinofx.controllers.mainMenu.MainMenuLoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SceneSwitch {
    public SceneSwitch(AnchorPane currentAnchorPane, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxml));
        AnchorPane nextAnchorPane = loader.load();
        // Проверяем на null
        if (nextAnchorPane != null && nextAnchorPane.getId().equals("mainMenuLogin")) {
            MainMenuLoginController mainMenuController = loader.getController();
            if (mainMenuController != null) {
                mainMenuController.setBalanceViewLabel();
                mainMenuController.setStakeViewLabel();
            }
        }
        else if (nextAnchorPane != null && nextAnchorPane.getId().equals("rouletteAnchor")) {
            RouletteController rouletteController = loader.getController();
            if (rouletteController != null) {
                rouletteController.reset();
                rouletteController.setProfit();
                rouletteController.setPlayButtonDisable();
            }
        }
        else if (nextAnchorPane != null && nextAnchorPane.getId().equals("slotsAnchor")) {
            SlotsController slotsController = loader.getController();
            if (slotsController != null) {
                slotsController.updateLabels();
            }
        }
        else if (nextAnchorPane != null && nextAnchorPane.getId().equals("anchor21")) {
            Start21Controller start21Controller = loader.getController();
            if (start21Controller != null) {
                start21Controller.updateStakeBalanceLabels();
            }
        }
        else if (nextAnchorPane != null && nextAnchorPane.getId().equals("twentyOneStartedAnchor")) {
            TwentyOneController twentyOneController = loader.getController();
            if (twentyOneController != null) {
                twentyOneController.startGame();
            }
        }
        else if (nextAnchorPane != null && nextAnchorPane.getId().equals("rouletteResultAnchor")) {
            RouletteResultController rouletteResultController = loader.getController();
            if (rouletteResultController != null) {
                rouletteResultController.generateGameResult();
            }
        }
        else if (nextAnchorPane != null && nextAnchorPane.getId().equals("loginPane")) {
            LoginPageController loginPageController = loader.getController();
            if (loginPageController != null) {
                loginPageController.resetWarning();
            }
        }
        else if (nextAnchorPane != null && nextAnchorPane.getId().equals("registrationPage")) {
            RegisterController registerController = loader.getController();
            if (registerController != null) {
                registerController.resetWarning();
            }
        }

        Component.resize(nextAnchorPane, currentAnchorPane.getPrefWidth(), currentAnchorPane.getPrefHeight());
//        System.out.println(currentAnchorPane.getWidth()*Application.lastTransformation.getX()+ " "+" "+ currentAnchorPane.getBoundsInLocal().getHeight()*Application.lastTransformation.getY());
        System.out.println("SIZE" + currentAnchorPane.getPrefWidth() + ' ' + currentAnchorPane.getPrefHeight());

        currentAnchorPane.getChildren().clear();
        currentAnchorPane.getChildren().add(nextAnchorPane);
    }
}
