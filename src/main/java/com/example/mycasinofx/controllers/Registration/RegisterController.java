package com.example.mycasinofx.controllers.Registration;

import com.example.mycasinofx.Model.FxModels.SceneSwitch;
import com.example.mycasinofx.Model.Hashing.PasswordHash;
import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.DatabaseManager;
import com.example.mycasinofx.Model.exceptions.AgeFailAccess;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    AnchorPane registrationPage;

    @FXML
    TextField emailField, loginField, ageField;
    @FXML
    TextField passwordConfirmField, passwordField;
    @FXML
    Label errorMessage;
    private RegistrationWarningsInterface registrationWarningsInterface;
    private DatabaseManager databaseManager;

    private PageSwitchInterface pageSwitch;



    public void initialize(){
        registrationWarningsInterface = new Warnings();
        databaseManager = new DatabaseManager();
        pageSwitch = new SwitchPage();

        loginField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckValidData.isValidNameLength(newValue)) {
                loginField.setStyle("-fx-border-color: red;");
            } else {
                loginField.setStyle("");
                loginField.setStyle("-fx-border-color: #1ddc06;");
            }
        });

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


        passwordConfirmField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!CheckValidData.isValidPasswordLength(newValue)) {
                passwordConfirmField.setStyle("-fx-border-color: red;");
            } else {
                passwordConfirmField.setStyle("");
                passwordConfirmField.setStyle("-fx-border-color: #1ddc06;");
            }
        });

        ageField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ageField.setText(oldValue);
            }
            if (newValue.length() > 2){
                ageField.setText(oldValue);
            }
        });



    }

    @FXML
    public void goMainMenu() throws IOException {
        pageSwitch.goMainMenu(registrationPage);
    }

    @FXML
    public void goLogin() throws IOException {
        pageSwitch.goLogin(registrationPage);
    }

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

    public void registerUser(String loginName, String loginPassword, String loginEmail, int loginAge){
        DAOPattern.registerUser(loginName, loginPassword, loginEmail, loginAge);
    }
    @FXML
    public void resetWarning(){
        registrationWarningsInterface.resetWarning(errorMessage);
    }





}
