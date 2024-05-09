package com.example.mycasinofx.controllers.games.roulette;

import com.example.mycasinofx.Model.games.ResultGenericClass;
import com.example.mycasinofx.Model.games.Roulette.Roulette;
import com.example.mycasinofx.Model.games.Roulette.RouletteSetUp;
import com.example.mycasinofx.Model.Player;
import com.example.mycasinofx.controllers.games.usefulComponent.ResultMessage;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.io.IOException;

public class RouletteResultController {
    /**
     * Anchor pane containing the UI elements for the Roulette game result.
     */
    @FXML
    private AnchorPane rouletteResultAnchor;

    /**
     * Label displaying the player's balance after the game.
     */
    @FXML
    private Label balanceLabel;

    /**
     * Label displaying the exact number selected by the player.
     */
    @FXML
    private Label exactNumberLabel;

    /**
     * Label displaying messages or notifications to the player.
     */
    @FXML
    private Label messageLabel;

    /**
     * Label displaying the amount of stake placed by the player.
     */
    @FXML
    private Label amountStake;

    /**
     * Label displaying the default balance.
     */
    @FXML
    private Label balanceDefaultLabel;

    /**
     * Grid pane containing the numbers layout in the roulette game result.
     */
    @FXML
    private GridPane gridPaneRoulette;

    /**
     * Polygon representing the zero number in the roulette grid.
     */
    @FXML
    private Polygon zeroPolygon;

    /**
     * Text flow for displaying dynamic text in the UI.
     */
    @FXML
    private TextFlow textFlow;

    /**
     * Button for selecting red numbers in the roulette game result.
     */
    @FXML
    private Button redButton;

    /**
     * Button for selecting black numbers in the roulette game result.
     */
    @FXML
    private Button blackButton;

    /**
     * Button for selecting green numbers in the roulette game result.
     */
    @FXML
    private Button greenButton;

    /**
     * Button for selecting even numbers in the roulette game result.
     */
    @FXML
    private Button evenButton;

    /**
     * Button for selecting odd numbers in the roulette game result.
     */
    @FXML
    private Button oddButton;

    /**
     * The Roulette game instance associated with the result.
     */
    public Roulette roulette;

    /**
     * Instance of the ResultGenericClass used for managing game results.
     */
    private ResultGenericClass resultGenericClass;

    /**
     * The player object associated with the current game session.
     */
    private final Player player;

    /**
     * Interface for switching pages in the application.
     */
    private PageSwitchInterface pageSwitch;

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up initial configurations and disables button interactions.
     */
    public void initialize(){
        redButton.setMouseTransparent(true);
        blackButton.setMouseTransparent(true);
        greenButton.setMouseTransparent(true);
        evenButton.setMouseTransparent(true);
        oddButton.setMouseTransparent(true);
    }

    /**
     * Constructor for the RouletteResultController class.
     * Initializes necessary objects and variables.
     */
    public RouletteResultController() {
        roulette = Roulette.getRoulette();
        pageSwitch = new SwitchPage();
        player = Player.getPlayer();
        resultGenericClass = ResultGenericClass.getResult();
    }

    /**
     * Sets the CSS style for stake buttons based on the player's current stakes.
     */
    public void setStakeButtons() {
        if (roulette.isGreenStakeSet()) {
            greenButton.getStyleClass().clear();
            greenButton.getStyleClass().add("green-roulette-stake-button-active");
        }
        if (roulette.isRedStakeSet()) {
            redButton.getStyleClass().clear();
            redButton.getStyleClass().add("red-roulette-stake-button-active");
        }
        if (roulette.isBlackStakeSet()) {
            blackButton.getStyleClass().clear();
            blackButton.getStyleClass().add("black-roulette-stake-button-active");
        }
        if (roulette.isEvenStakeSet()) {
            evenButton.getStyleClass().clear();
            evenButton.getStyleClass().add("pair-roulette-stake-button-active");
        }
        if (roulette.isOddStakeSet()) {
            oddButton.getStyleClass().clear();
            oddButton.getStyleClass().add("pair-roulette-stake-button-active");
        }
    }



    /**
     * Generates the game result by updating labels and animating the roulette grid.
     */
    public void generateGameResult() {
        updateLabels();
        //convert to int because the type of my result is Object
        int curGeneration = (int) resultGenericClass.getResultValue();
        effectResult(gridPaneRoulette, curGeneration);


    }

    /**
     * Sets the message label displaying the game result.
     */
    public void setMessageLabel() {

        balanceDefaultLabel.setText("Current balance is: ");
        ResultMessage.updateMessageLabel(messageLabel, balanceLabel, player);
    }


    /**
     * Sets the text for the current stake labels.
     */
    @FXML
    public void setCurrentStakeText() {
        int index = 0;
        //set labels by text
        for (int i = 0; i < 5; i++) {
            if (roulette.getCurStakes(i) != null) {
                if (textFlow.getChildren().get(index) instanceof Label) {
                    Label label = (Label) textFlow.getChildren().get(index);
                    label.setText(roulette.getCurStakes(i));
                    index += 1;
                }
            }
        }

        //set the label disabled
        for (int i = index; i < 5; i++) {
            if (textFlow.getChildren().get(i) instanceof Label) {
                Label label = (Label) textFlow.getChildren().get(i);
                label.setVisible(false);
            }
        }

    }

    /**
     * Sets the text for the exact number label.
     */
    @FXML
    public void setExactNumberText() {
        String res = roulette.getStringExactNumber();

        if (res.equals("")) {
            exactNumberLabel.setText("You Did Not Select Exact Number");
        } else {
            exactNumberLabel.setText("" + res);
        }
    }

    /**
     * Sets the text for the amount of stake label.
     */
    @FXML
    public void setAmountStake() {
        amountStake.setText("Amount of Stake: " + player.getCurrentStake());
    }

    /**
     * Updates all labels and stake buttons in the UI.
     */
    public void updateLabels() {
        setCurrentStakeText();
        setExactNumberText();
        setAmountStake();
        setStakeButtons();
//        setBalanceLabel();
    }



    /**
     * Animates the result by changing the color of roulette grid elements.
     */
    public void effectResult(GridPane gridPane, int curGeneration) {
        changeColor(gridPane, 0, 10, curGeneration);
    }

    /**
     * Changes the color of roulette grid elements to simulate animation.
     */
    private void changeColor(GridPane gridPane, int index, int delay, int curGeneration) {
        if (index < 55) {
            int number = ((curGeneration + 19) + index) % 37;
            if (number == 36) {
                zeroPolygon.setFill(Color.YELLOW);
                if (index + 1 != 55) {
                    PauseTransition pause = new PauseTransition(Duration.millis(delay));
                    pause.setOnFinished(event -> {
                        zeroPolygon.setFill(Color.GREEN);
                        int newDelay = (int) (delay * 1.1);
                        changeColor(gridPane, index + 1, newDelay, curGeneration);
                    });
                    pause.play();
                } else {
                    setMessageLabel();
                }
            //RTTI -> instanceof
            } else if (gridPane.getChildren().get(number) instanceof Label) {

                Label label = (Label) gridPane.getChildren().get(number);


                try {
                    label.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
                } catch (Exception e) {
                    System.out.println(e);
                }

                if (index + 1 != 55) {
                    PauseTransition pause = new PauseTransition(Duration.millis(delay));
                    pause.setOnFinished(event -> {

                        String color = RouletteSetUp.getColours(number + 1);
                        switch (color) {
                            case "Red":
                                label.setBackground(Background.fill(Color.RED));
                                break;
                            case "Green":
                                label.setBackground(Background.fill(Color.GREEN));
                                break;
                            case "Black":
                                label.setBackground(Background.fill(Color.BLACK));
                                break;
                            default:
                                label.setBackground(Background.fill(Color.YELLOW));
                        }
                        int newDelay = (int) (delay * 1.1); // Можете изменить коэффициент для более медленного замедления
                        changeColor(gridPane, index + 1, newDelay, curGeneration);
                    });
                    pause.play();
                } else {
                    setMessageLabel();
                }
            } else {
                changeColor(gridPane, index + 1, delay, curGeneration); // Пропускаем элементы, которые не являются Label
            }
        } else {
            //end animation
        }
    }

    /**
     * Navigates to the roulette game page to play again.
     * @throws IOException If an I/O exception occurs.
     */
    public void playAgain() throws IOException {
        pageSwitch.goRoulette(rouletteResultAnchor);
    }

    /**
     * Navigates to the main menu.
     * @throws IOException If an I/O exception occurs.
     */
    public void goMainMenu() throws IOException {
        pageSwitch.goMainMenu(rouletteResultAnchor);
    }
    //---------------------------------------------------------------------------------

}
