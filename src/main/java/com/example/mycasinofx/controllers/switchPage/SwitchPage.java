package com.example.mycasinofx.controllers.switchPage;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.Model.FxModels.SceneSwitch;
import com.example.mycasinofx.Model.FxModels.Serialization;
import com.example.mycasinofx.Model.Player;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;

import java.io.IOException;
import java.util.Objects;

/**
 * The SwitchPage class implements the PageSwitchInterface to provide methods for switching between pages in the application.
 */
public class SwitchPage implements PageSwitchInterface{

    /**
     * Switches to the main menu page.
     * @param anchorPane the anchor pane containing the main menu.
     * @throws IOException if there is an error loading the main menu page.
     */
    @Override
    public void goMainMenu(AnchorPane anchorPane) throws IOException {
        Player player = Player.getPlayer();
        if (player.getUsername() == null) {
            new SceneSwitch(anchorPane, "view/main_menu/main_menu_not_login.fxml");
        }
        else{
            new SceneSwitch(anchorPane, "view/main_menu/main_menu_login.fxml");
        }
    }

    /**
     * Switches to the login page.
     * @param anchorPane the anchor pane containing the login page.
     * @throws IOException if there is an error loading the login page.
     */
    @Override
    public void goLogin(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/Registration/login.fxml");
    }

    /**
     * Switches to the registration page.
     * @param anchorPane the anchor pane containing the registration page.
     * @throws IOException if there is an error loading the registration page.
     */
    @Override
    public void goRegister(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/Registration/register_page.fxml");
    }

    /**
     * Switches to the slots page.
     * @param anchorPane the anchor pane containing the slots page.
     * @throws IOException if there is an error loading the slots page.
     */
    @Override
    public void goSlots(AnchorPane anchorPane) throws IOException {
        checkLoginUser(anchorPane, "view/Games/slot/slots.fxml");
    }

    /**
     * Switches to the play 21 page.
     * @param anchorPane the anchor pane containing the play 21 page.
     * @throws IOException if there is an error loading the play 21 page.
     */
    @Override
    public void goPlay21(AnchorPane anchorPane) throws IOException {
        checkLoginUser(anchorPane, "view/Games/twenty_one/start21.fxml");
    }

    /**
     * Switches to the 21 page.
     * @param anchorPane the anchor pane containing the 21 page.
     * @throws IOException if there is an error loading the 21 page.
     */
    @Override
    public void go21(AnchorPane anchorPane) throws IOException {
        checkLoginUser(anchorPane, "view/Games/twenty_one/twenty_one.fxml");
    }

    /**
     * Switches to the roulette page.
     * @param anchorPane the anchor pane containing the roulette page.
     * @throws IOException if there is an error loading the roulette page.
     */
    @Override
    public void goRoulette(AnchorPane anchorPane) throws IOException {
        checkLoginUser(anchorPane, "view/Games/roulette/roulette.fxml");
    }

    /**
     * Switches to the roulette result page.
     * @param anchorPane the anchor pane containing the roulette result page.
     * @throws IOException if there is an error loading the roulette result page.
     */
    @Override
    public void goRouletteResult(AnchorPane anchorPane) throws IOException {
        checkLoginUser(anchorPane, "view/Games/roulette/rouletteResult.fxml");
    }

    /**
     * Switches to the page with all games.
     * @param anchorPane the anchor pane containing the page with all games.
     * @throws IOException if there is an error loading the page with all games.
     */
    @Override
    public void goFullList(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/fullListGames.fxml");
    }

    /**
     * Switches to the page with menu of voting.
     * @param anchorPane the anchor pane containing the page with menu of voting.
     * @throws IOException if there is an error loading the page with menu of voting.
     */
    @Override
    public void goVotingPageChange(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/active_voting/menu_voting.fxml");
    }

    /**
     * Switches to the page with voting for the best game.
     * @param anchorPane the anchor pane containing the page with voting for the best game.
     * @throws IOException if there is an error loading the page with voting for the best game.
     */
    @Override
    public void goVotingBestGamePageChange(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/active_voting/voting_procces/best_game_voting.fxml");
    }

    /**
     * Switches to the page with voting for the new game.
     * @param anchorPane the anchor pane containing the page with voting for the new game.
     * @throws IOException if there is an error loading the page with voting for the new game.
     */
    @Override
    public void goVotingNewGamePageChange(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/active_voting/voting_procces/new_game_voting.fxml");
    }

    /**
     * Switches to the page with result of the voting for the new game.
     * @param anchorPane the anchor pane containing the page with result of the voting for the new game.
     * @throws IOException if there is an error loading the page with result of the voting for the new game.
     */
    @Override
    public void goVotingNewGameResult(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/active_voting/voting_result/new_game_result.fxml");
    }

    /**
     * Switches to the page with result of the voting for the best game.
     * @param anchorPane the anchor pane containing the page with result of the voting for the best game.
     * @throws IOException if there is an error loading the page with result of the voting for the best game.
     */
    @Override
    public void goVotingBestGameResult(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/active_voting/voting_result/best_game_result.fxml");
    }

    /**
     * Switches to the page with info about author.
     * @param anchorPane the anchor pane containing the page with info about author.
     * @throws IOException if there is an error loading the page with info about author.
     */
    @Override
    public void goAboutAuthor(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/aboutAuthor.fxml");
    }

    /**
     * Switches to the start page.
     * @param anchorPane the anchor pane containing the start page.
     * @throws IOException if there is an error loading the start page.
     */
    @Override
    public void goFirstMenu(AnchorPane anchorPane) throws IOException {
        new SceneSwitch(anchorPane, "view/firstMenu.fxml");
    }

    /**
     * Confirms exit action.
     */
    @Override
    public void confirmExit() {

        ImageView imageView = new ImageView(new Image("/casino-logo.png"));
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Application");
        alert.setHeaderText("Do you want to exit Casino?");
        alert.setContentText("Are you sure?");

        HBox graphicContainer = new HBox(imageView);
        alert.getDialogPane().setGraphic(graphicContainer);

        String css = Objects.requireNonNull(Application.class.getResource("view/styles/styles.css")).toExternalForm();
        alert.getDialogPane().getStylesheets().add(css);

        alert.initModality(Modality.APPLICATION_MODAL);


        if (alert.showAndWait().get() == ButtonType.OK) {
            Player player = Player.getPlayer();
            Serialization userSettingsToSave = new Serialization(player.getCurrentStake()); // Замените на реальные данные
            userSettingsToSave.save("user_last_stake.ser");
            Platform.exit();
        }
    }

    /**
     * Checks if a user is logged in and navigates accordingly.
     * @param anchorPane the anchor pane to navigate from.
     * @param path the path to navigate to.
     * @throws IOException if there is an error loading the destination page.
     */
    @Override
    public void checkLoginUser(AnchorPane anchorPane, String path) throws IOException {
        Player player = Player.getPlayer();
        if (player.getUsername() == null) {
            new SceneSwitch(anchorPane, "view/Registration/login.fxml");
        }
        else{
            new SceneSwitch(anchorPane, path);
        }
    }
}
