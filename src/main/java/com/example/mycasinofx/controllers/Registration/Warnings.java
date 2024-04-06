package com.example.mycasinofx.controllers.Registration;

import javafx.scene.control.Label;

public class Warnings implements RegistrationWarningsInterface{

    @Override
    public void showEmptyDataPopup(Label label){
        label.setText("Full Fill All Fields");
        label.setVisible(true);
    }

    @Override
    public void showAgeWarning(Label label){
        label.setText("The age must be more than 18");
        label.setVisible(true);
    }

    @Override
    public void passwordNotEqual(Label label){
        label.setText("The password not equal");
        label.setVisible(true);
    }

    @Override
    public void resetWarning(Label label){
        label.setVisible(false);
    }

    @Override
    public void emailAlreadyExist(Label label){
        label.setText("The email already exists");
        label.setVisible(true);
    }

    @Override
    public void emailDoesNotExist(Label label){
        label.setText("The email does not exist");
        label.setVisible(true);
    }

    @Override
    public void passwordDoesNotExist(Label label){
        label.setText("The password is not correct");
        label.setVisible(true);
    }

    @Override
    public void emailFormatError(Label label){
        label.setText("Wrong Format Email");
        label.setVisible(true);
    }

    @Override
    public void passwordLength(Label label){
        label.setText("Password must contain at least 6 characters");
        label.setVisible(true);
    }

    @Override
    public void nameLength(Label label){
        label.setText("Name must contain at least 3 characters");
        label.setVisible(true);
    }
}
