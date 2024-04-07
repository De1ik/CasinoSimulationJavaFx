package com.example.mycasinofx.controllers;

import com.example.mycasinofx.Model.FxModels.SceneSwitch;

import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;


import java.io.IOException;

import javafx.scene.layout.AnchorPane;


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
    }

}
