package com.example.mycasinofx.controllers.Registration;

import com.example.mycasinofx.Model.passwordHashing.PasswordHash;
import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.DatabaseManager;
import com.example.mycasinofx.Model.Player;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;


/**
 * The LoginPageController class controls the functionality of the login page in the application.
 * It manages user input validation, database interactions for login, and page navigation.
 */
public class LoginPageController {
    /**
     * Anchor Pane of the LoginPageController class.
     */
    @FXML
    private AnchorPane loginPane;

    /**
     * Text field for entering the user's email.
     */
    @FXML
    private TextField emailField;

    /**
     * Text field for entering the user's password.
     */
    @FXML
    private TextField passwordField;

    /**
     * Label to display error messages.
     */
    @FXML
    private Label errorMessage;
    /**
     * The player who wants to log in.
     */
    private Player player;

    /**
     * Manages interactions with the database.
     */
    private DatabaseManager databaseManager;

    /**
     * Interface for displaying registration warnings.
     */
    private RegistrationWarningsInterface registrationWarningsInterface;

    /**
     * Interface for switching between pages.
     */
    private PageSwitchInterface pageSwitch;

    /**
     * Initializes the LoginPageController by setting up event listeners for input validation.
     */
    public void initialize(){
        player = Player.getPlayer();
        registrationWarningsInterface = new Warnings();
        databaseManager = new DatabaseManager();
        pageSwitch = new SwitchPage();

        // Event listener for email field
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckValidData.isValidEmail(newValue)) {
                emailField.setStyle("-fx-border-color: red;");
            } else {
                emailField.setStyle("");
                emailField.setStyle("-fx-border-color: #1ddc06;");
            }
        });

        // Event listener for password field
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckValidData.isValidPasswordLength(newValue)) {
                passwordField.setStyle("-fx-border-color: red;");
            } else {
                passwordField.setStyle("");
                passwordField.setStyle("-fx-border-color: #1ddc06;");
            }
        });

    }


    /**
     * Switches the application to the main menu page.
     * @throws IOException if there is an error loading the main menu page.
     */
    @FXML
    public void goMainMenu() throws IOException {
        pageSwitch.goMainMenu(loginPane);
    }

    /**
     * Attempts to log in the user with the provided email and password.
     * @throws IOException if there is an error loading a page.
     * @throws SQLException if there is an error with the SQL database.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    @FXML
    public void goLogin() throws IOException, SQLException, ClassNotFoundException {
        String loginEmail = emailField.getText().trim();
        String loginPassword = PasswordHash.hashPassword(passwordField.getText().trim());

        if (loginEmail.isEmpty() || loginPassword.isEmpty()){
            registrationWarningsInterface.showEmptyDataPopup(errorMessage);
        }
        else if(!CheckValidData.isValidEmail(loginEmail)){
            registrationWarningsInterface.emailFormatError(errorMessage);
        }
        else if (!CheckValidData.isValidPasswordLength(loginPassword)) {
            registrationWarningsInterface.passwordLength(errorMessage);
        }
        else if (DAOPattern.checkValidEmail(loginEmail) != 1 && DAOPattern.checkValidEmail(loginEmail) != -1) {
            registrationWarningsInterface.emailDoesNotExist(errorMessage);
        }
        else if (DAOPattern.checkValidPassword(loginPassword, loginEmail) != 1){
            registrationWarningsInterface.passwordDoesNotExist(errorMessage);
        }
        else{
            loginUser(loginEmail, loginPassword);
            if (player.getUsername() == null) {
                errorMessage.setText("Something went Wrong");
            }
            else{
                pageSwitch.goMainMenu(loginPane);
            }
        }
    }

    /**
     * Logs in the user with the provided email and password.
     * @param loginEmail the email of the user.
     * @param loginPassword the password of the user.
     */
    @FXML
    private void loginUser(String loginEmail, String loginPassword){
        DAOPattern.loginUserDB(loginEmail, loginPassword);
    }

    /**
     * Switches the application to the registration page.
     * @throws IOException if there is an error loading the registration page.
     */
    @FXML
    public void goRegistrationPage() throws IOException {
        pageSwitch.goRegister(loginPane);
    }

    /**
     * Resets the error message label.
     */
    @FXML
    public void resetWarning(){
        registrationWarningsInterface.resetWarning(errorMessage);
    }
}
