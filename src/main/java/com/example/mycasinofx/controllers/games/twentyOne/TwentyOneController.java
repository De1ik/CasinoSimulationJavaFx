package com.example.mycasinofx.controllers.games.twentyOne;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.Model.games.ResultGenericClass;
import com.example.mycasinofx.Model.games.TwentyOne.TwentyOne;
import com.example.mycasinofx.Model.games.strategy_pattern.GameResultStrategy;
import com.example.mycasinofx.Model.games.strategy_pattern.TwentyOneResultImpl;
import com.example.mycasinofx.Model.Player;
import com.example.mycasinofx.controllers.custom_dialog_stake.ResultDialog;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller class for managing the UI and logic of the Twenty-One (Blackjack) game.
 */
public class TwentyOneController implements Initializable {

    /**
     * Anchor pane containing the UI elements for the game.
     */
    @FXML
    private AnchorPane twentyOneStartedAnchor, resultGame;

    /**
     * Label displaying the game result.
     */
    @FXML
    private Label result;

    /**
     * Label displaying the player's balance.
     */
    @FXML
    private Label balanceLabel;

    /**
     * Label displaying the amount of stake placed by the player.
     */
    @FXML
    private Label amountStake;

    /**
     * Label displaying the total value of player's hand cards.
     */
    @FXML
    private Label playerValue;

    /**
     * Label displaying the total value of dealer's hand cards (alternative layout).
     */
    @FXML
    private Label dillerValue;

    /**
     * Image views for displaying player's hand cards.
     */
    @FXML
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7;

    /**
     * Image views for displaying dealer's hand cards.
     */
    @FXML
    private ImageView imageViewBot1, imageViewBot2, imageViewBot3, imageViewBot4;

    /**
     * Button for getting a new card.
     */
    @FXML
    private Button getCard;

    /**
     * Button for showing the game result.
     */
    @FXML
    private Button showRes;

    /**
     * HBox containing the dealer's total value label (alternative layout).
     */
    @FXML
    private HBox dillerValueHbox;

    /**
     * Border pane containing the main layout of the game.
     */
    @FXML
    private BorderPane borderPane;

    /**
     * Instance of the Twenty-One game.
     */
    private TwentyOne twentyOne;

    /**
     * Instance of the player participating in the game.
     */
    private Player player;

    /**
     * Interface for switching pages in the application.
     */
    private PageSwitchInterface pageSwitch;

    /**
     * Instance of the generic class for storing game results.
     */
    private ResultGenericClass<Integer> resultGenericClass;

    /**
     * Strategy for displaying game results.
     */
    private GameResultStrategy gameResultStrategy;

    /**
     * Player's balance before placing the stake.
     */
    private double balanceBeforeStake;

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up initial configurations and initializes game components.
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        twentyOne = new TwentyOne();
        pageSwitch = new SwitchPage();
        player = Player.getPlayer();
        resultGenericClass = (ResultGenericClass<Integer>) ResultGenericClass.getResult();
        gameResultStrategy = new TwentyOneResultImpl();
    }

    /**
     * Shows the game result dialog with the provided result value.
     * @param res The result value indicating the outcome of the game.
     * @throws IOException If an I/O exception occurs.
     */
    public void showGameResultDialog(int res) throws IOException {
        ResultDialog.showGameResultDialog(twentyOneStartedAnchor, borderPane, resultGame, 3, twentyOne.getBotValue(), twentyOne.getPlayerValue(), res);
    }

    /**
     * Navigates to the Play21 game page.
     * @throws IOException If an I/O exception occurs.
     */
    public void goPlay21() throws IOException {
        pageSwitch.goPlay21(twentyOneStartedAnchor);
    }

    /**
     * Updates the balance label with the player's current balance.
     */
    @FXML
    public void setBalanceLabel() {
        balanceLabel.setText("" + player.getBalance());
    }

    /**
     * Updates the current stake label with the player's current stake amount.
     */
    @FXML
    public void setCurStakeLabel() {
        amountStake.setText("" + player.getCurrentStake());
    }

    /**
     * Updates the labels displaying balance and current stake.
     */
    @FXML
    public void updateLabels() {
        setBalanceLabel();
        setCurStakeLabel();
    }

    /**
     * Starts the game by updating player's balance, showing game elements, playing the game,
     * updating UI with player's hand and bot's hand, and hiding dealer's value box.
     */
    public void startGame(){
        balanceBeforeStake = player.getBalance();
        player.setBalance(player.getBalance() - player.getCurrentStake());
        getCard.setVisible(true);
        showRes.setVisible(true);
        twentyOne.play_game();
        playerValue.setText("" + twentyOne.getPlayerValue());
        updateLabelsPlayerHandCards();
        updateBotHandCards(false);
        updateLabels();
        dillerValueHbox.setVisible(false);
    }

    /**
     * Draws a card for the player, updates UI accordingly, and checks if player's value exceeds 21.
     * @throws IOException If an I/O error occurs.
     */
    public void getCard() throws IOException {
        twentyOne.getCardToHand(true);
        playerValue.setText("" + twentyOne.getPlayerValue());
        updateLabelsPlayerHandCards();
        if (twentyOne.getPlayerValue() > 21){
            getCard.setVisible(false);
            showRes.setVisible(false);
            updateBotHandCards(true);
            player.setProfit(-player.getCurrentStake());
            ResultDialog.showGameResultDialog(twentyOneStartedAnchor, borderPane, resultGame, 3, twentyOne.getBotValue(), twentyOne.getPlayerValue(), 2);
        }

    }

    /**
     * Draws cards for the bot until it reaches a value less than or equal to 17.
     */
    public void getBotCard(){
        while(twentyOne.tryGetBotCard()) {
            twentyOne.tryGetBotCard();
            updateBotHandCards(false);
        }
    }

    /**
     * Compares player's and bot's values, updates UI accordingly, and displays game result.
     * @throws IOException If an I/O error occurs.
     */
    public void compareWinner() throws IOException {
        dillerValueHbox.setVisible(true);
        getCard.setVisible(false);
        showRes.setVisible(false);
        getBotCard();
        updateBotHandCards(true);
        dillerValue.setText(""+twentyOne.getBotValue());
        twentyOne.checkWinner();
        double differBalance = player.getBalance() - balanceBeforeStake;
        player.setProfit(differBalance);
        gameResultStrategy.showGameResult(differBalance);
        int res = resultGenericClass.getResultValue();
        switch (res){
            case 0:
                result.setText("Draw");
                break;
            case 1:
                result.setText("You win");
                break;
            case 2:
                result.setText("You lose");
                break;
        }
        showGameResultDialog(resultGenericClass.getResultValue());
    }

    /**
     * Updates UI with player's hand cards.
     */
    public void updateLabelsPlayerHandCards(){
        setInvisibleButtons();
        ArrayList<String[]> cards = twentyOne.getPlayerCards();
        for (String[] card: cards) {
            Image image = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("view/picture/cards/"+card[1]+"_of_"+card[0]+".png")));
            if (!imageView1.isVisible()){
                imageView1.setImage(image);
                imageView1.setVisible(true);
            }
            else if (!imageView2.isVisible()){
                imageView2.setImage(image);
                imageView2.setVisible(true);
            }
            else if (!imageView3.isVisible()){
                imageView3.setImage(image);
                imageView3.setVisible(true);
            }
            else if (!imageView4.isVisible()){
                imageView4.setImage(image);
                imageView4.setVisible(true);
            }
            else if (!imageView5.isVisible()){
                imageView5.setImage(image);
                imageView5.setVisible(true);
            }
            else if (!imageView6.isVisible()){
                imageView6.setImage(image);
                imageView6.setVisible(true);
            }
            else if (!imageView7.isVisible()){
                imageView7.setImage(image);
                imageView7.setVisible(true);
            }
        }
    }

    /**
     * Updates UI with bot's hand cards.
     * @param show True to show the cards, false to hide them.
     */
    public void updateBotHandCards(boolean show){
        setInvisibleBotButtons();
        ArrayList<String[]> cards = twentyOne.getBotCards();
        for (String[] card: cards) {
            Image image = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("view/picture/cards/"+card[1]+"_of_"+card[0]+".png")));
            Image imageShirt = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("view/picture/cards/card_shirt.png")));
            if (!imageViewBot1.isVisible()){
                if (show) imageViewBot1.setImage(image);
                else imageViewBot1.setImage(imageShirt);
                imageViewBot1.setVisible(true);
            }
            else if (!imageViewBot2.isVisible()){
                if (show) imageViewBot2.setImage(image);
                else imageViewBot2.setImage(imageShirt);
                imageViewBot2.setVisible(true);
            }
            else if (!imageViewBot3.isVisible()){
                if (show) imageViewBot3.setImage(image);
                else imageViewBot3.setImage(imageShirt);
                imageViewBot3.setVisible(true);
            }
            else if (!imageViewBot4.isVisible()){
                if (show) imageViewBot4.setImage(image);
                else imageViewBot4.setImage(imageShirt);
                imageViewBot4.setVisible(true);
            }

        }
    }

    /**
     * Hides player's hand cards.
     */
    public void setInvisibleButtons(){
        imageView1.setVisible(false);
        imageView2.setVisible(false);
        imageView3.setVisible(false);
        imageView4.setVisible(false);
        imageView5.setVisible(false);
        imageView6.setVisible(false);
        imageView7.setVisible(false);
    }

    /**
     * Hides bot's hand cards.
     */
    public void setInvisibleBotButtons(){
        imageViewBot1.setVisible(false);
        imageViewBot2.setVisible(false);
        imageViewBot3.setVisible(false);
        imageViewBot4.setVisible(false);
    }
}
