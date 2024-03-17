package com.example.mycasinofx.controllers.Registration;

import com.example.mycasinofx.Model.FxModels.SceneSwitch;
import com.example.mycasinofx.Model.Hashing.PasswordHash;
import com.example.mycasinofx.Model.database.DatabaseManager;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageController {
    @FXML
    AnchorPane loginPane;
    @FXML
    TextField emailField;
    @FXML
    TextField passwordField;
    @FXML
    Label errorMessage;

    private DatabaseManager databaseManager;
    private RegistrationWarningsInterface registrationWarningsInterface;
    private PageSwitchInterface pageSwitch;

    public void initialize(){
        registrationWarningsInterface = new Warnings();
        databaseManager = new DatabaseManager();
        pageSwitch = new SwitchPage();

        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckValidData.isValidEmail(newValue)) {
                emailField.setStyle("-fx-border-color: red;");
            } else {
                emailField.setStyle("");
                emailField.setStyle("-fx-border-color: #1ddc06;");
            }
        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckValidData.isValidPasswordLength(newValue)) {
                passwordField.setStyle("-fx-border-color: red;");
            } else {
                passwordField.setStyle("");
                passwordField.setStyle("-fx-border-color: #1ddc06;");
            }
        });

    }



    @FXML
    public void goMainMenu() throws IOException {
        pageSwitch.goMainMenu(loginPane);
    }

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
        else if (databaseManager.checkValidEmail(loginEmail) != 1 && databaseManager.checkValidEmail(loginEmail) != -1) {
            registrationWarningsInterface.emailDoesNotExist(errorMessage);
        }
        else if (databaseManager.checkValidPassword(loginPassword) != 1 && databaseManager.checkValidPassword(loginPassword) != -1){
            registrationWarningsInterface.passwordDoesNotExist(errorMessage);
        }
        else{
            loginUser(loginEmail, loginPassword);
            pageSwitch.goMainMenu(loginPane);
        }
    }

    @FXML
    private void loginUser(String loginEmail, String loginPassword) throws IOException, SQLException, ClassNotFoundException {
        databaseManager.loginUserDB(loginEmail, loginPassword);
    }

    @FXML
    public void goRegistrationPage() throws IOException {
        pageSwitch.goRegister(loginPane);
    }

    @FXML
    public void resetWarning(){
        registrationWarningsInterface.resetWarning(errorMessage);
    }
}
