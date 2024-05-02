package com.example.mycasinofx.controllers;

import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutAuthorControllers{

    private SwitchPage switchPage;

    @FXML
    private AnchorPane aboutAuthorAnchor;
    @FXML
    private Hyperlink click;

    public void initialize(){
        switchPage = new SwitchPage();
    }

    @FXML
    public void goFirstMenu() throws IOException {
        switchPage.goFirstMenu(aboutAuthorAnchor);
    }




    @FXML
    public void clickLink() throws IOException {
        switchPage.goFirstMenu(aboutAuthorAnchor);
        try {
            // Открываем ссылку во внешнем браузере
            Desktop.getDesktop().browse(new URI("https://github.com/De1ik"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }





}
