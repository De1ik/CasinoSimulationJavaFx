package com.example.mycasinofx.Model.FxModels;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.controllers.Registration.LoginPageController;
import com.example.mycasinofx.controllers.mainMenu.MainMenuController;
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
                rouletteController.setPlayButtonDisable();
            }
        }
        else if (nextAnchorPane != null && nextAnchorPane.getId().equals("rouletteResultAnchor")) {
            RouletteResultController rouletteController = loader.getController();
            if (rouletteController != null) {
                rouletteController.generateGameResult();
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
