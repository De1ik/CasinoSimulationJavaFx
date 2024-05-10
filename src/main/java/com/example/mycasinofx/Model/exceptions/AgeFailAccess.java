package com.example.mycasinofx.Model.exceptions;

/**
 * The AgeFailAccess class represents an exception
 * that is thrown when access is denied due to age restrictions.
 */
public class AgeFailAccess extends Exception {

    /**
     * Constructs a new AgeFailAccess exception with
     * the specified error message.
     * @param errorMessage The error message explaining
     *                     the reason for the age access failure.
     */
    public AgeFailAccess(String errorMessage) {
        super(errorMessage);
    }
}
