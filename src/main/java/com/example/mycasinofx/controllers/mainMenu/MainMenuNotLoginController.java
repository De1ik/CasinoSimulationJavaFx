package com.example.mycasinofx.controllers.mainMenu;

import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class MainMenuNotLoginController {
    @FXML
    private AnchorPane mainMenuNotLogin;
    @FXML
    private Button exitButton;
    private final PageSwitchInterface pageSwitch = new SwitchPage();


    public void initialize() {
        exitButton.setOnAction(e -> pageSwitch.confirmExit());
    }

    public void goRoulette() throws IOException{
        pageSwitch.goRoulette(mainMenuNotLogin);
    }

    @FXML
    public void goSlots() throws IOException{
        pageSwitch.goSlots(mainMenuNotLogin);
    }

    @FXML
    public void go21() throws IOException{
        pageSwitch.go21(mainMenuNotLogin);
    }

    @FXML
    public void goVoting() throws IOException {
        pageSwitch.goVotingPageChange(mainMenuNotLogin);
    }

    @FXML
    public void goLogin() throws IOException{
        pageSwitch.goLogin(mainMenuNotLogin);
    }

    @FXML
    public void goRegister() throws IOException{
        pageSwitch.goRegister(mainMenuNotLogin);
    }

}
