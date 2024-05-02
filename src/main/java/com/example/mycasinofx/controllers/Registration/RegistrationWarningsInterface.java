package com.example.mycasinofx.controllers.Registration;

import javafx.scene.control.Label;

public interface RegistrationWarningsInterface {

    public void showEmptyDataPopup(Label label);
    public void showAgeWarning(Label label);
    public void resetWarning(Label label);
    public void passwordNotEqual(Label label);
    public void emailAlreadyExist(Label label);
    public void emailDoesNotExist(Label label);
    public void emailFormatError(Label label);
    public void passwordDoesNotExist(Label label);
    public void passwordLength(Label label);
    public void nameLength(Label label);
}
