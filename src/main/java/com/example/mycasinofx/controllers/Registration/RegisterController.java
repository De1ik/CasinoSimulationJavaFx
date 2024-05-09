package com.example.mycasinofx.controllers.Registration;

import com.example.mycasinofx.Model.passwordHashing.PasswordHash;
import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.DatabaseManager;
import com.example.mycasinofx.Model.exceptions.AgeFailAccess;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The RegisterController class controls the functionality of the registration page in the application.
 * It manages user input validation, database interactions for registration, and page navigation.
 */
public class RegisterController {
    /**
     * The anchor pane for the registration page.
     */
    @FXML
    AnchorPane registrationPage;

    /**
     * Text field for entering the user's email.
     */
    @FXML
    TextField emailField;

    /**
     * Text field for entering the user's login name.
     */
    @FXML
    TextField loginField;

    /**
     * Text field for entering the user's age.
     */
    @FXML
    TextField ageField;

    /**
     * Text field for entering the user's password confirmation.
     */
    @FXML
    TextField passwordConfirmField;

    /**
     * Text field for entering the user's password.
     */
    @FXML
    TextField passwordField;

    /**
     * Label to display error messages.
     */
    @FXML
    Label errorMessage;

    /**
     * Interface for displaying registration warnings.
     */
    private RegistrationWarningsInterface registrationWarningsInterface;

    /**
     * Manages interactions with the database.
     */
    private DatabaseManager databaseManager;

    /**
     * Interface for switching between pages.
     */
    private PageSwitchInterface pageSwitch;


    /**
     * Initializes the RegisterController by setting up event listeners for input validation.
     */
    public void initialize(){
        registrationWarningsInterface = new Warnings();
        databaseManager = new DatabaseManager();
        pageSwitch = new SwitchPage();
        // Event listener for login field
        loginField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckValidData.isValidNameLength(newValue)) {
                loginField.setStyle("-fx-border-color: red;");
            } else {
                loginField.setStyle("");
                loginField.setStyle("-fx-border-color: #1ddc06;");
            }
        });
        // Event listener for email field
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckValidData.isValidEmail(newValue)) {
                emailField.setStyle("-fx-border-color: red;");
            } else {
                emailField.setStyle("");
                emailField.setStyle("-fx-border-color: #1ddc06;");
            }
        });
        // Event listener for password confirmation field
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckValidData.isValidPasswordLength(newValue)) {
                passwordField.setStyle("-fx-border-color: red;");
            } else {
                passwordField.setStyle("");
                passwordField.setStyle("-fx-border-color: #1ddc06;");
            }
        });

        // Event listener for password confirmation field
        passwordConfirmField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckValidData.isValidPasswordLength(newValue)) {
                passwordConfirmField.setStyle("-fx-border-color: red;");
            } else {
                passwordConfirmField.setStyle("");
                passwordConfirmField.setStyle("-fx-border-color: #1ddc06;");
            }
        });
        // Event listener for age field
        ageField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ageField.setText(oldValue);
            }
            if (newValue.length() > 2){
                ageField.setText(oldValue);
            }
        });



    }

    /**
     * Switches the application to the main menu page.
     * @throws IOException if there is an error loading the main menu page.
     */
    @FXML
    public void goMainMenu() throws IOException {
        pageSwitch.goMainMenu(registrationPage);
    }

    /**
     * Switches the application to the login page.
     * @throws IOException if there is an error loading the login page.
     */
    @FXML
    public void goLogin() throws IOException {
        pageSwitch.goLogin(registrationPage);
    }

    /**
     * Attempts to register the user with the provided information.
     * @throws IOException if there is an error loading a page.
     * @throws SQLException if there is an error with the SQL database.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    @FXML
    public void goRegistrationPage() throws IOException, SQLException, ClassNotFoundException {
        String loginName = loginField.getText().trim();
        String loginPassword = passwordField.getText().trim();
        String passwordConfirm = passwordConfirmField.getText().trim();
        String loginEmail = emailField.getText().trim();
        int loginAge = 0;
        try{
            loginAge = Integer.parseInt(ageField.getText().trim());
        } catch (Exception e){
            System.out.println(e);
        }

        if (loginName.isEmpty() || loginPassword.isEmpty() || loginEmail.isEmpty()){
            registrationWarningsInterface.showEmptyDataPopup(errorMessage);
        }
        else if(!CheckValidData.isValidNameLength(loginName)){
            registrationWarningsInterface.nameLength(errorMessage);
        }
        else if(!CheckValidData.isValidEmail(loginEmail)){
            registrationWarningsInterface.emailFormatError(errorMessage);
        }
        else if (DAOPattern.checkValidEmail(loginEmail) != 0 && DAOPattern.checkValidEmail(loginEmail) != -1){
            registrationWarningsInterface.emailAlreadyExist(errorMessage);
        }
        else if (!CheckValidData.isValidPasswordLength(loginPassword)){
            registrationWarningsInterface.passwordLength(errorMessage);
        }
        else if (!CheckValidData.isValidPasswordLength(passwordConfirm)){
            registrationWarningsInterface.passwordLength(errorMessage);
        }
        else if(!passwordConfirm.equals(loginPassword)){
            registrationWarningsInterface.passwordNotEqual(errorMessage);
        }
        else {
            try{
                //customException
                CheckValidData.validate(loginAge);
                loginPassword = PasswordHash.hashPassword(loginPassword);
                registerUser(loginName, loginPassword, loginEmail, loginAge);
                goLogin();
            } catch (AgeFailAccess e) {
                System.out.println(e.getMessage());
                registrationWarningsInterface.showAgeWarning(errorMessage);
            }
        }
    }

    /**
     * Registers the user with the provided information.
     * @param loginName the username of the user.
     * @param loginPassword the password of the user.
     * @param loginEmail the email of the user.
     * @param loginAge the age of the user.
     */
    public void registerUser(String loginName, String loginPassword, String loginEmail, int loginAge){
        DAOPattern.registerUser(loginName, loginPassword, loginEmail, loginAge);
    }

    /**
     * Resets the error message label.
     */
    @FXML
    public void resetWarning(){
        registrationWarningsInterface.resetWarning(errorMessage);
    }
}
