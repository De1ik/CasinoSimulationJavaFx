package com.example.mycasinofx.controllers.Registration;

import javafx.scene.control.Label;

/**
 * The RegistrationWarningsInterface defines methods for displaying various registration-related warnings.
 */
public interface RegistrationWarningsInterface {

    /**
     * Displays a warning for empty data in registration fields.
     * @param label the label to display the warning message.
     */
    void showEmptyDataPopup(Label label);

    /**
     * Displays a warning for invalid age input.
     * @param label the label to display the warning message.
     */
    void showAgeWarning(Label label);

    /**
     * Resets the warning message.
     * @param label the label to reset.
     */
    void resetWarning(Label label);

    /**
     * Displays a warning for password mismatch during registration.
     * @param label the label to display the warning message.
     */
    void passwordNotEqual(Label label);

    /**
     * Displays a warning for existing email during registration.
     * @param label the label to display the warning message.
     */
    void emailAlreadyExist(Label label);

    /**
     * Displays a warning for non-existing email during login.
     * @param label the label to display the warning message.
     */
    void emailDoesNotExist(Label label);

    /**
     * Displays a warning for incorrect email format.
     * @param label the label to display the warning message.
     */
    void emailFormatError(Label label);

    /**
     * Displays a warning for incorrect password during login.
     * @param label the label to display the warning message.
     */
    void passwordDoesNotExist(Label label);

    /**
     * Displays a warning for invalid password length.
     * @param label the label to display the warning message.
     */
    void passwordLength(Label label);

    /**
     * Displays a warning for invalid username length during registration.
     * @param label the label to display the warning message.
     */
    void nameLength(Label label);
}
