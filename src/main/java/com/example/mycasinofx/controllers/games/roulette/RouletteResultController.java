package com.example.mycasinofx.controllers.games.roulette;

import com.example.mycasinofx.Model.games.ResultGenericClass;
import com.example.mycasinofx.Model.games.Roulette.RouletteSetUp;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.Model.games.Roulette.Roulette;
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
    @FXML
    private AnchorPane rouletteResultAnchor;

    @FXML
    private Label balanceLabel, exactNumberLabel, messageLabel, amountStake, balanceDefaultLabel;
    @FXML
    private GridPane gridPaneRoulette;
    @FXML
    private Polygon zeroPolygon;
    @FXML
    private TextFlow textFlow;
    @FXML
    private Button redButton, blackButton, greenButton, evenButton, oddButton;

    public Roulette roulette;

    private ResultGenericClass resultGenericClass;

    private final Player player;
    private PageSwitchInterface pageSwitch;

    public void initialize(){
        redButton.setMouseTransparent(true);
        blackButton.setMouseTransparent(true);
        greenButton.setMouseTransparent(true);
        evenButton.setMouseTransparent(true);
        oddButton.setMouseTransparent(true);
    }


    public RouletteResultController() {
        roulette = Roulette.getRoulette();
        pageSwitch = new SwitchPage();
        player = Player.getPlayer();
        resultGenericClass = ResultGenericClass.getResult();
    }


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



    //-------------------Generate The Result Add All Components-----------------------
    public void generateGameResult() {
        updateLabels();
        //convert to int because the type of my result is Object
        int curGeneration = (int) resultGenericClass.getResultValue();
        System.out.println("RES: " + curGeneration);
        System.out.println("PROFIT: " + player.getProfit());
        effectResult(gridPaneRoulette, curGeneration);


    }
    //--------------------------------------------------------------------------------


    public void setMessageLabel() {

        balanceDefaultLabel.setText("Current balance is: ");
        ResultMessage.updateMessageLabel(messageLabel, balanceLabel, player);
    }


    //-------------------Set Label Info-----------------------------------------------
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

    @FXML
    public void setExactNumberText() {
        String res = roulette.getStringExactNumber();

        if (res.equals("")) {
            exactNumberLabel.setText("You Did Not Select Exact Number");
        } else {
            exactNumberLabel.setText("" + res);
        }
    }


    @FXML
    public void setAmountStake() {
        amountStake.setText("Amount of Stake: " + player.getCurrentStake());
    }

    public void updateLabels() {
        setCurrentStakeText();
        setExactNumberText();
        setAmountStake();
        setStakeButtons();
//        setBalanceLabel();
    }
    //--------------------------------------------------------------------------------


    //-------------------Start Animation----------------------------------------------
    public void effectResult(GridPane gridPane, int curGeneration) {
        changeColor(gridPane, 0, 10, curGeneration);
    }

    private void changeColor(GridPane gridPane, int index, int delay, int curGeneration) {
        if (index < 55) {
            int number = ((curGeneration + 19) + index) % 37;
            System.out.println("INDEX : " + number);
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
    //--------------------------------------------------------------------------------


    //-----------------------------Change Page----------------------------------------
    public void playAgain() throws IOException {
        pageSwitch.goRoulette(rouletteResultAnchor);
    }

    public void goMainMenu() throws IOException {
        pageSwitch.goMainMenu(rouletteResultAnchor);
    }
    //---------------------------------------------------------------------------------

}
