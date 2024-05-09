package com.example.mycasinofx.controllers.switchPage;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * The PageSwitchInterface defines methods for switching between pages in the application.
 */
public interface PageSwitchInterface {

    /**
     * Switches to the main menu page.
     * @param anchorPane the anchor pane containing the main menu.
     * @throws IOException if there is an error loading the main menu page.
     */
    public void goMainMenu(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the login page.
     * @param anchorPane the anchor pane containing the login page.
     * @throws IOException if there is an error loading the login page.
     */
    public void goLogin(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the registration page.
     * @param anchorPane the anchor pane containing the registration page.
     * @throws IOException if there is an error loading the registration page.
     */
    public void goRegister(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the slots page.
     * @param anchorPane the anchor pane containing the slots page.
     * @throws IOException if there is an error loading the slots page.
     */
    public void goSlots(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the play 21 page.
     * @param anchorPane the anchor pane containing the play 21 page.
     * @throws IOException if there is an error loading the play 21 page.
     */
    public void goPlay21(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the 21 page.
     * @param anchorPane the anchor pane containing the 21 page.
     * @throws IOException if there is an error loading the 21 page.
     */
    public void go21(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the roulette page.
     * @param anchorPane the anchor pane containing the roulette page.
     * @throws IOException if there is an error loading the roulette page.
     */
    public void goRoulette(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the roulette result page.
     * @param anchorPane the anchor pane containing the roulette result page.
     * @throws IOException if there is an error loading the roulette result page.
     */
    public void goRouletteResult(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the full list page.
     * @param anchorPane the anchor pane containing the full list page.
     * @throws IOException if there is an error loading the full list page.
     */
    public void goFullList(AnchorPane anchorPane) throws IOException;

    /**
     * Confirms exit action.
     */
    public void confirmExit();

    /**
     * Checks if a user is logged in and navigates accordingly.
     * @param anchorPane the anchor pane to navigate from.
     * @param path the path to navigate to.
     * @throws IOException if there is an error loading the destination page.
     */
    public void checkLoginUser(AnchorPane anchorPane, String path) throws IOException;

    /**
     * Switches to the voting page.
     * @param anchorPane the anchor pane containing the voting page.
     * @throws IOException if there is an error loading the voting page.
     */
    public void goVotingPageChange(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the voting page for the best game.
     * @param anchorPane the anchor pane containing the voting page for the best game.
     * @throws IOException if there is an error loading the voting page for the best game.
     */
    public void goVotingBestGamePageChange(AnchorPane anchorPane) throws IOException;


    /**
     * Switches to the voting page for a new game.
     * @param anchorPane the anchor pane containing the voting page for a new game.
     * @throws IOException if there is an error loading the voting page for a new game.
     */
    public void goVotingNewGamePageChange(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the result page for a new game voting.
     * @param anchorPane the anchor pane containing the result page for a new game voting.
     * @throws IOException if there is an error loading the result page for a new game voting.
     */
    public void goVotingNewGameResult(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the result page for the best game voting.
     * @param anchorPane the anchor pane containing the result page for the best game voting.
     * @throws IOException if there is an error loading the result page for the best game voting.
     */
    public void goVotingBestGameResult(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the about author page.
     * @param anchorPane the anchor pane containing the about author page.
     * @throws IOException if there is an error loading the about author page.
     */
    public void goAboutAuthor(AnchorPane anchorPane) throws IOException;

    /**
     * Switches to the first menu page.
     * @param anchorPane the anchor pane containing the first menu page.
     * @throws IOException if there is an error loading the first menu page.
     */
    public void goFirstMenu(AnchorPane anchorPane) throws IOException;
}
