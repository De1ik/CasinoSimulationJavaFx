package com.example.mycasinofx.controllers;

import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the start page.
 */
public class FirstMenuController {

    /**
     * The AnchorPane for the first page.
     */
    @FXML
    private AnchorPane firstAnchor;

    /**
     * The main AnchorPane that remains unchanged.
     */
    @FXML
    private AnchorPane mainPaneNotChange;

    /**
     * The green AnchorPane.
     */
    @FXML
    private AnchorPane greenPain;

    /**
     * Button for exiting the application.
     */
    @FXML
    private Button exitButton;

    /**
     * Instance of SwitchPage for handling scene navigation.
     */
    private SwitchPage switchPage;

    /**
     * Instance of PageSwitchInterface for handling page switching.
     */
    private PageSwitchInterface pageSwitch;

    /**
     * Initializes the controller.
     * Sets up the event handler for the exitButton and initializes SwitchPage instance and pageSwitch instance.
     */
    public void initialize(){
        pageSwitch = new SwitchPage();
        switchPage = new SwitchPage();
        exitButton.setOnAction(e -> pageSwitch.confirmExit());
    }

    /**
     * Handles the action of navigating to the main menu.
     * @throws IOException if an error occurs during scene switching
     */
    @FXML
    public void goMainMenu() throws IOException {
        switchPage.goMainMenu(firstAnchor);
    }

    /**
     * Handles the action of navigating to the about author section.
     * @throws IOException if an error occurs during scene switching
     */
    @FXML
    public void goAboutAuthor() throws IOException {
        switchPage.goAboutAuthor(firstAnchor);
    }

    /**
     * Retrieves the main AnchorPane that remains unchanged.
     * @return The main AnchorPane
     */
    public AnchorPane getMainAnchor(){
        return mainPaneNotChange;
    }

    /**
     * Retrieves the green AnchorPane.
     * @return The green AnchorPane
     */
    public AnchorPane getGreenPain(){
        return greenPain;
    }

}