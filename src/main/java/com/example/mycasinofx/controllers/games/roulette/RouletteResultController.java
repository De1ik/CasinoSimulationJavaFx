package com.example.mycasinofx.controllers.games.roulette;

import com.example.mycasinofx.Model.FxModels.SceneSwitch;
import com.example.mycasinofx.Model.games.Roulette.RouletteSetUp;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.Model.games.Roulette.Roulette;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
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
    private Label balanceLabel, exactNumberLabel, messageLabel;
    @FXML
    private GridPane gridPaneRoulette;
    @FXML
    private Polygon zeroPolygon;
    @FXML
    private TextFlow textFlow;

    private double profit;

    public Roulette roulette;
    
    private final Player player = Player.getPlayer();
    private PageSwitchInterface pageSwitch;

    public RouletteResultController(){
        roulette = Roulette.getRoulette();
        pageSwitch = new SwitchPage();
    }



    ////profit before and profit after will be the same
    //-------------------Generate The Result Add All Components-----------------------
    public void generateGameResult() {
        updateLabels();
        double playerBalanceBefore = player.getBalance();


        roulette.setGamed(true);
        roulette.generateResult();
        //convert to int because the type of my result is Object
        int curGeneration = roulette.getResult();
        System.out.println("RES: " + curGeneration);

        roulette.checkWinner();
        double playerBalanceAfter = player.getBalance();
        this.profit = playerBalanceAfter - playerBalanceBefore;
        System.out.println("PROFIT: "+this.profit);


        effectResult(gridPaneRoulette, curGeneration);


    }
    //--------------------------------------------------------------------------------




    public void setMessageLabel(){

        if (this.profit > 0){
            messageLabel.setText("You won: " + this.profit);
        }
        else if (this.profit < 0){
            messageLabel.setText("You lose: " + (this.profit*-1));
        }
        else{
            messageLabel.setText("You're left with the same balance");
        }
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
            exactNumberLabel.setText("Exact Number Which you Select: " + res);
        }
    }
    @FXML
    public void setBalanceLabel() {
        balanceLabel.setText("Current balance is: " + player.getBalance());
    }

    public void updateLabels(){
        setCurrentStakeText();
        setExactNumberText();
        setBalanceLabel();
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
                }
                else{
                    setBalanceLabel();
                    setMessageLabel();
                }
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
                }
                else {
                    setBalanceLabel();
                    setMessageLabel();
                }
            } else {
                changeColor(gridPane, index + 1, delay, curGeneration); // Пропускаем элементы, которые не являются Label
            }
        } else {
            System.out.println("END OF ANIMATION");
            setBalanceLabel();
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
