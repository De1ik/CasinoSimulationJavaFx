package com.example.mycasinofx.controllers;

import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Controller class for the about author information section.
 */
public class AboutAuthorControllers{

    /**
     * Instance of SwitchPage for handling scene navigation.
     */
    private SwitchPage switchPage;

    /**
     * The AnchorPane for the about author section.
     */
    @FXML
    private AnchorPane aboutAuthorAnchor;

    /**
     * Hyperlink for clicking to navigate.
     */
    @FXML
    private Hyperlink click;

    /**
     * Initializes the controller.
     * Initializes the SwitchPage instance.
     */
    public void initialize(){
        switchPage = new SwitchPage();
    }

    /**
     * Handles the action of navigating to the first menu.
     * @throws IOException if an error occurs during scene switching
     */
    @FXML
    public void goFirstMenu() throws IOException {
        switchPage.goFirstMenu(aboutAuthorAnchor);
    }

    /**
     * Handles the action of clicking the hyperlink.
     * Opens the author's GitHub page in the default browser.
     * @throws IOException if an error occurs during scene switching or opening the URL
     */
    @FXML
    public void clickLink() throws IOException {
//        switchPage.goFirstMenu(aboutAuthorAnchor);
        try {
            // Открываем ссылку во внешнем браузере
            Desktop.getDesktop().browse(new URI("https://github.com/De1ik"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }





}
