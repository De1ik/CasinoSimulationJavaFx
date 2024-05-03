package com.example.mycasinofx.controllers;

import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstMenuController {

    private Stage stage;

    @FXML
    private AnchorPane firstAnchor, mainPaneNotChange, greenPain;
    @FXML
    private Button exitButton;
    private SwitchPage switchPage;
    private final PageSwitchInterface pageSwitch = new SwitchPage();

    public void initialize(){
        switchPage = new SwitchPage();
        exitButton.setOnAction(e -> pageSwitch.confirmExit());
    }



    @FXML
    public void goMainMenu() throws IOException {
        switchPage.goMainMenu(firstAnchor);
    }

    @FXML
    public void goAboutAuthor() throws IOException {
        switchPage.goAboutAuthor(firstAnchor);
    }





    public AnchorPane getMainAnchor(){
        return mainPaneNotChange;
    }

    public AnchorPane getGreenPain(){
        return greenPain;
    }

}