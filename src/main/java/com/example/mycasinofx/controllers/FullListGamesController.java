package com.example.mycasinofx.controllers;

import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class FullListGamesController {

    @FXML
    private AnchorPane fullListGamesAnchor;
    private PageSwitchInterface pageSwitch;

    public void initialize(){
        pageSwitch = new SwitchPage();
    }

    @FXML
    public void goMainMenu() throws IOException{
        pageSwitch.goMainMenu(fullListGamesAnchor);
        return;
    }

}
