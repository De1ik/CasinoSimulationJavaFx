package com.example.mycasinofx.controllers.games.roulette;


import com.example.mycasinofx.Model.games.Roulette.Roulette;
import com.example.mycasinofx.Model.games.Roulette.RouletteSetUp;
import com.example.mycasinofx.Model.games.strategy_pattern.GameResultStrategy;
import com.example.mycasinofx.Model.games.strategy_pattern.RouletteResultImpl;
import com.example.mycasinofx.Model.Player;
import com.example.mycasinofx.controllers.custom_dialog_stake.SetStakeCustomDialog;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller class for managing the Roulette game UI.
 */
public class RouletteController {
    /**
     * The anchor pane containing the main UI elements for the Roulette game.
     */
    @FXML
    private AnchorPane rouletteAnchor, dialog_window;
    /**
     * Label displaying the player's current balance.
     */
    @FXML
    private Label balanceLabel;

    /**
     * Label displaying the player's current stake amount.
     */
    @FXML
    private Label currentStakeLabel;

    /**
     * Label displaying the exact number chosen by the player.
     */
    @FXML
    private Label exactNumberLabel;

    /**
     * Label displaying any warnings or messages to the player.
     */
    @FXML
    private Label warningsLabel;

    /**
     * Label representing the zero number in the roulette grid.
     */
    @FXML
    private Label zeroLabel;

    /**
     * Label representing the amount of stake in the roulette game.
     */
    @FXML
    private Label amountStake;

    /**
     * Button for selecting even numbers in the roulette game.
     */
    @FXML
    private Button evenButton;

    /**
     * Button for selecting odd numbers in the roulette game.
     */
    @FXML
    private Button oddButton;

    /**
     * Button for selecting red numbers in the roulette game.
     */
    @FXML
    private Button redButton;

    /**
     * Button for selecting black numbers in the roulette game.
     */
    @FXML
    private Button blackButton;

    /**
     * Button for selecting green numbers in the roulette game.
     */
    @FXML
    private Button greenButton;

    /**
     * Button for initiating the roulette game.
     */
    @FXML
    private Button playRouletteButton;

    /**
     * Grid pane containing the numbers layout in the roulette game.
     */
    @FXML
    private GridPane gridPaneRoulette;

    /**
     * Polygon representing the zero number in the roulette grid.
     */
    @FXML
    private Polygon zeroPolygon;

    /**
     * Border pane containing the main UI elements for the Roulette game.
     */
    @FXML
    private BorderPane borderPane;

    /**
     * The player object associated with the current game session.
     */
    private final Player player;

    /**
     * The roulette object representing the game itself.
     */
    private final Roulette roulette;

    /**
     * Interface for switching pages in the application.
     */
    private PageSwitchInterface pageSwitch;

    /**
     * Strategy for determining game results in the Roulette game.
     */
    private GameResultStrategy gameResultStrategy;

    /**
     * The profit value calculated for the player during the game session.
     */
    private double profit;

    /**
     * Constructor for RouletteController.
     */
    public RouletteController() {
        roulette = Roulette.getRoulette();
        player = Player.getPlayer();
        gameResultStrategy = new RouletteResultImpl();
    }

    /**
     * Sets the profit for the player.
     */
    public void setProfit(){
        profit = player.getBalance();
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * This method sets up event handlers for mouse clicks on the roulette grid and initializes
     * other necessary components.
     */
    @FXML
    public void initialize() {
        setAmountStake();
        pageSwitch = new SwitchPage();
        dialog_window.setMouseTransparent(true);
        zeroLabel.setMouseTransparent(true);
//        redButton.getStyleClass().add("red-roulette-stake-button-active");

        EventHandler<MouseEvent> rouletteClickHandler = event -> {
            Background background;
            Label label = null;
            Polygon zero = null;
            int number;
            if (event.getSource() instanceof Label) {
                label = (Label) event.getSource();
                background = label.getBackground();
                number = Integer.parseInt(label.getText());
            } else {
                zero = (Polygon) event.getSource();
                background = Background.fill(zero.getFill());
                number = 0;
            }


            if (background != null) {
                BackgroundFill fill = background.getFills().get(0);
                if (fill.getFill().equals(Color.YELLOW)) {
                    String color = RouletteSetUp.getColours(number);
                    if (label != null) {
                        if (color.equals("Red")) label.setBackground(Background.fill(Color.RED));
                        else if (color.equals("Black")) label.setBackground(Background.fill(Color.BLACK));
                    } else {
                        zero.setFill(Color.GREEN);
                    }
                    roulette.removeExactNumberArray(number);
                    player.setBalance(player.getBalance() + player.getCurrentStake());
                    if (roulette.isEmptyExactNumberArray()) {
                        roulette.setExactNumber(false);
                    }
                } else {
                    if (player.tryDoStake()) {
                        roulette.setExactNumber(true);
                        if (label != null) {
                            label.setBackground(Background.fill(Color.YELLOW));
                        } else {
                            zero.setFill(Color.YELLOW);
                        }
                        roulette.addExactNumberArray(number);
                        player.setBalance(player.getBalance() - player.getCurrentStake());
                    }
                    else{
                        warningsLabel.setText("Insufficient Balance");
                    }
                }
            }
            if (roulette.isEmptyExactNumberArray()) {
                roulette.setExactNumber(false);
                roulette.setCurStakes(null, 5);
                if (!roulette.checkStartGame()) playRouletteButton.setDisable(true);
            } else {
                roulette.setExactNumber(true);
                roulette.setCurStakes(" Exact Number ", 5);
                playRouletteButton.setDisable(false);
            }
            updateLabels();
        };


        //Add the components to the EventHandler<MouseEvent>
        for (int i = 0; i < gridPaneRoulette.getChildren().size(); i++) {
            if (gridPaneRoulette.getChildren().get(i) instanceof Label) {
                Label label = (Label) gridPaneRoulette.getChildren().get(i);
                label.setOnMouseClicked(rouletteClickHandler);
            }
        }
        //
        reset();
        zeroPolygon.setOnMouseClicked(rouletteClickHandler);
    }


    /**
     * Sets the play button disable state based on the game state.
     * Disables the play button if the game cannot be started.
     */
    @FXML
    public void setPlayButtonDisable() {
        if (!roulette.checkStartGame()) playRouletteButton.setDisable(true);
    }

    /**
     * Sets the CSS style class for a button to make it non-active.
     *
     * @param string The type of bet (e.g., "Red", "Black", "Green").
     * @param button The button to set as non-active.
     */
    public void setNonActiveButton(String string, Button button) {
        switch (string){
            case " Red ":
                button.getStyleClass().clear();
                button.getStyleClass().add("red-roulette-stake-button");
                break;
            case " Black ":
                button.getStyleClass().clear();
                button.getStyleClass().add("black-roulette-stake-button");
                break;
            case " Green ":
                button.getStyleClass().clear();
                button.getStyleClass().add("green-roulette-stake-button");
                break;
            default:
                button.getStyleClass().clear();
                button.getStyleClass().add("pair-roulette-stake-button");
                break;
        }
    }

    /**
     * Sets the CSS style class for a button to make it active.
     *
     * @param string The type of bet (e.g., "Red", "Black", "Green").
     * @param button The button to set as active.
     */
    public void setActiveButton(String string, Button button) {
        switch (string){
            case " Red ":
                button.getStyleClass().clear();
                button.getStyleClass().add("red-roulette-stake-button-active");
                break;
            case " Black ":
                button.getStyleClass().clear();
                button.getStyleClass().add("black-roulette-stake-button-active");
                break;
            case " Green ":
                button.getStyleClass().clear();
                button.getStyleClass().add("green-roulette-stake-button-active");
                break;
            default:
                button.getStyleClass().clear();
                button.getStyleClass().add("pair-roulette-stake-button-active");
                break;
        }
    }


    /**
     * Handles clicking on a betting button to place a stake.
     * Marks different kinds of bets as already made or as available for selection
     * @param flag The current state of the button (true for active, false for non-active).
     * @param button The button being clicked.
     * @param index The index of the stake.
     * @param string The type of bet (e.g., "Red", "Black", "Green").
     * @return The updated state of the button (true for active, false for non-active).
     */
    @FXML
    public boolean clickButtonStake(boolean flag, Button button, int index, String string) {
        if (flag) {
            //change the color due css
            setNonActiveButton(string, button);

            //------------------------------------------

            flag = false;
            roulette.setCurStakes(null, index);
            player.setBalance(player.getBalance() + player.getCurrentStake());
        } else {
            if (player.tryDoStake()) {
                flag = true;
                playRouletteButton.setDisable(false);

                //change the color due css
                setActiveButton(string, button);
                //------------------------------------------
                roulette.setCurStakes(string, index);
                player.setBalance(player.getBalance() - player.getCurrentStake());
            }
            else{
                warningsLabel.setText("Insufficient Balance");
            }
        }
        return flag;
    }

    /**
     * Handles clicking on the "Even" button to place a stake on even numbers.
     */
    @FXML
    public void even() {
        roulette.setEvenStakeSet(clickButtonStake(roulette.isEvenStakeSet(), evenButton, 0, " Even "));
        updateLabels();
        setPlayButtonDisable();
    }

    /**
     * Handles clicking on the "Odd" button to place a stake on odd numbers.
     */
    @FXML
    public void odd() {
        roulette.setOddStakeSet(clickButtonStake(roulette.isOddStakeSet(), oddButton, 1, " Odd "));
        updateLabels();
        setPlayButtonDisable();
    }

    /**
     * Handles clicking on the "Red" button to place a stake on red numbers.
     */
    @FXML
    public void red() {
        roulette.setRedStakeSet(clickButtonStake(roulette.isRedStakeSet(), redButton, 2, " Red "));
        updateLabels();
        setPlayButtonDisable();
    }

    /**
     * Handles clicking on the "Black" button to place a stake on black numbers.
     */
    @FXML
    public void black() {
        roulette.setBlackStakeSet(clickButtonStake(roulette.isBlackStakeSet(), blackButton, 3, " Black "));
        updateLabels();
        setPlayButtonDisable();
    }

    /**
     * Handles clicking on the "Green" button to place a stake on green number.
     */
    @FXML
    public void green() {
        roulette.setGreenStakeSet(clickButtonStake(roulette.isGreenStakeSet(), greenButton, 4, " Green "));
        updateLabels();
        setPlayButtonDisable();
    }

    /**
     * Sets the label displaying the exact number selected by the player.
     */
    @FXML
    public void setExactNumberLabel() {
        String res = roulette.getStringExactNumber();

        if (res.equals("")) {
            exactNumberLabel.setText("You Did Not Select Exact Number");
        } else {
            exactNumberLabel.setText(res);
        }
    }

    /**
     * Sets the label displaying the current balance of the player.
     */
    @FXML
    public void setBalanceLabel() {
        balanceLabel.setText("Current balance: " + player.getBalance());
    }

    /**
     * Sets the label displaying the amount of stake selected by the player.
     */
    @FXML
    public void setAmountStake() {
        amountStake.setText("Amount of Stake: " + player.getCurrentStake());
    }

    /**
     * Sets the label displaying the current stake of the player.
     */
    @FXML
    public void setCurrentStakeLabel() {
        String res = "";
        for (int i = 0; i < 6; i++) {
            if (roulette.getCurStakes(i) != null) res += roulette.getCurStakes(i);
        }
        currentStakeLabel.setText(res);
    }

    /**
     * Updates all labels in the UI.
     */
    @FXML
    public void updateLabels() {
        setCurrentStakeLabel();
        setBalanceLabel();
        setExactNumberLabel();
    }

    /**
     * Resets the CSS style for all buttons.
     */
    @FXML
    public void resetButtons(){
        redButton.getStyleClass().clear();
        redButton.getStyleClass().add("red-roulette-stake-button");
        blackButton.getStyleClass().clear();
        blackButton.getStyleClass().add("black-roulette-stake-button");
        greenButton.getStyleClass().clear();
        greenButton.getStyleClass().add("green-roulette-stake-button");
        evenButton.getStyleClass().clear();
        evenButton.getStyleClass().add("pair-roulette-stake-button");
        oddButton.getStyleClass().clear();
        oddButton.getStyleClass().add("pair-roulette-stake-button");
    }



    /**
     * Resets all components of the UI and game state.
     * Used before switching to another scene or returning to the main menu.
     */
    @FXML
    public void reset() {
        //check if it needed to return money
        roulette.returnStakeBeforeEnd();
        //reset array with exact numbers, all boolean stake, and the massive with current stakes
        roulette.clearExactNumberArray();
        roulette.resetBooleanStake();
        roulette.resetCurStakes();
        resetButtons();
        //update the labels
        updateLabels();
        setPlayButtonDisable();
    }



    /**
     * Handles clicking on the "Play" button to start the roulette game.
     * @throws IOException If an I/O exception occurs.
     */
    @FXML
    public void playRoulette() throws IOException{

        if (roulette.checkStartGame()) {
            player.updateDBBalance();


            roulette.generateResult();
            roulette.checkWinner();

            player.updateDBBalance();
            profit = -1*(profit - player.getBalance());

            player.setProfit(profit);

            gameResultStrategy.showGameResult(profit);

            pageSwitch.goRouletteResult(rouletteAnchor);

        } else {
            //if we can not start game show a warning that we need to do a stake
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Can not Play");
            alert.setHeaderText(null);
            alert.setContentText("Before start you must select the type of the Stake");
            alert.showAndWait();
        }
    }

    /**
     * Navigates to the main menu.
     * @throws IOException If an I/O exception occurs.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the class definition is not found.
     */
    @FXML
    public void goMainMenu() throws IOException, SQLException, ClassNotFoundException {
        reset();
        player.updateDBBalance();
        pageSwitch.goMainMenu(rouletteAnchor);
    }


    /**
     * Opens a dialog to allow the player to place a stake.
     * @throws IOException If an I/O exception occurs.
     */
    @FXML
    public void doStake() throws IOException {
        reset();
        SetStakeCustomDialog.doStake(borderPane, dialog_window, warningsLabel, amountStake);
    }
}


