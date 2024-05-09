package com.example.mycasinofx.controllers.mainMenu;

import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Controller class for the main menu for users who haven't registered. .
 */
public class MainMenuNotLoginController {
    /**
     * The anchor pane for the main menu without login interface.
     */
    @FXML
    private AnchorPane mainMenuNotLogin;
    /**
     * The button for exiting the application.
     */
    @FXML
    private Button exitButton;

    /**
     * Interface for switching pages.
     */
    private PageSwitchInterface pageSwitch;


    /**
     * Initializes the controller.
     */
    public void initialize() {
        exitButton.setOnAction(e -> pageSwitch.confirmExit());
        pageSwitch = new SwitchPage();
    }

    /**
     * Navigates to the roulette game page.
     * @throws IOException If an I/O error occurs.
     */
    public void goRoulette() throws IOException{
        pageSwitch.goRoulette(mainMenuNotLogin);
    }

    /**
     * Navigates to the slots game page.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void goSlots() throws IOException{
        pageSwitch.goSlots(mainMenuNotLogin);
    }

    /**
     * Navigates to the 21 game page.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void go21() throws IOException{
        pageSwitch.go21(mainMenuNotLogin);
    }

    /**
     * Navigates to the voting page.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void goVoting() throws IOException {
        pageSwitch.goVotingPageChange(mainMenuNotLogin);
    }

    /**
     * Navigates to the login page.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void goLogin() throws IOException{
        pageSwitch.goLogin(mainMenuNotLogin);
    }

    /**
     * Navigates to the registration page.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void goRegister() throws IOException{
        pageSwitch.goRegister(mainMenuNotLogin);
    }

}
