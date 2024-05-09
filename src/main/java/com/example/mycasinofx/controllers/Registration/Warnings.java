package com.example.mycasinofx.controllers.Registration;

import javafx.scene.control.Label;


/**
 * The Warnings class implements the RegistrationWarningsInterface to provide methods for displaying registration-related warnings.
 */
public class Warnings implements RegistrationWarningsInterface{

    /**
     * Displays a warning for empty data in registration fields.
     * @param label the label to display the warning message.
     */
    @Override
    public void showEmptyDataPopup(Label label){
        label.setText("Fill out all the data");
        label.setVisible(true);
    }

    /**
     * Displays a warning for invalid age input.
     * @param label the label to display the warning message.
     */
    @Override
    public void showAgeWarning(Label label){
        label.setText("Age must be at least 18");
        label.setVisible(true);
    }

    /**
     * Displays a warning for password mismatch during registration.
     * @param label the label to display the warning message.
     */
    @Override
    public void passwordNotEqual(Label label){
        label.setText("Passwords do not match");
        label.setVisible(true);
    }

    /**
     * Resets the warning message.
     * @param label the label to reset.
     */
    @Override
    public void resetWarning(Label label){
        label.setVisible(false);
    }

    /**
     * Displays a warning for existing email during registration.
     * @param label the label to display the warning message.
     */
    @Override
    public void emailAlreadyExist(Label label){
        label.setText("Email already exists");
        label.setVisible(true);
    }

    /**
     * Displays a warning for non-existing email during login.
     * @param label the label to display the warning message.
     */
    @Override
    public void emailDoesNotExist(Label label){
        label.setText("Email does not exist");
        label.setVisible(true);
    }

    /**
     * Displays a warning for incorrect password during login.
     * @param label the label to display the warning message.
     */
    @Override
    public void passwordDoesNotExist(Label label){
        label.setText("Incorrect password");
        label.setVisible(true);
    }

    /**
     * Displays a warning for incorrect email format.
     * @param label the label to display the warning message.
     */
    @Override
    public void emailFormatError(Label label){
        label.setText("Invalid email format");
        label.setVisible(true);
    }

    /**
     * Displays a warning for invalid password length.
     * @param label the label to display the warning message.
     */
    @Override
    public void passwordLength(Label label){
        label.setText("Password must be at least 6 characters long");
        label.setVisible(true);
    }

    /**
     * Displays a warning for invalid username length during registration.
     * @param label the label to display the warning message.
     */
    @Override
    public void nameLength(Label label){
        label.setText("Username must be at least 3 characters long");
        label.setVisible(true);
    }
}
