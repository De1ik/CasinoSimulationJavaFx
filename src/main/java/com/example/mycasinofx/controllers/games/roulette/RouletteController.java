package com.example.mycasinofx.controllers.games.roulette;


import com.example.mycasinofx.Model.games.Roulette.Roulette;
import com.example.mycasinofx.Model.games.Roulette.RouletteSetUp;
import com.example.mycasinofx.Model.player.Player;
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


public class RouletteController {
    @FXML
    private AnchorPane rouletteAnchor, dialog_window;
    @FXML
    private Label balanceLabel, currentStakeLabel, exactNumberLabel, warningsLabel, zeroLabel, amountStake;
    @FXML
    private Button evenButton, oddButton, redButton, blackButton, greenButton, playRouletteButton;
    @FXML
    private GridPane gridPaneRoulette;
    @FXML
    private Polygon zeroPolygon;
    @FXML
    private BorderPane borderPane;

    private final Player player;
    private final Roulette roulette;
    private PageSwitchInterface pageSwitch;

    private double profit;

    public RouletteController() {
        roulette = Roulette.getRoulette();
        player = Player.getPlayer();
    }

    public void setProfit(){
        profit = player.getBalance();
    }


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


    //----------------------------------setting the visibility to the button-------------------------------------------
    @FXML
    public void setPlayButtonDisable() {
        if (!roulette.checkStartGame()) playRouletteButton.setDisable(true);
    }
    //-----------------------------------------------------------------------------------------------------------------

    //--------------------------update the components after clicking on the button-------------------------------------

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
    //-----------------------------------------------------------------------------------------------------------------


    //-------------------------------------Stake Button Clicked Action-------------------------------------------------
    @FXML
    public void even() {
        roulette.setEvenStakeSet(clickButtonStake(roulette.isEvenStakeSet(), evenButton, 0, " Even "));
        updateLabels();
        setPlayButtonDisable();
    }

    @FXML
    public void odd() {
        roulette.setOddStakeSet(clickButtonStake(roulette.isOddStakeSet(), oddButton, 1, " Odd "));
        updateLabels();
        setPlayButtonDisable();
    }

    @FXML
    public void red() {
        roulette.setRedStakeSet(clickButtonStake(roulette.isRedStakeSet(), redButton, 2, " Red "));
        updateLabels();
        setPlayButtonDisable();
    }

    @FXML
    public void black() {
        roulette.setBlackStakeSet(clickButtonStake(roulette.isBlackStakeSet(), blackButton, 3, " Black "));
        updateLabels();
        setPlayButtonDisable();
    }

    @FXML
    public void green() {
        roulette.setGreenStakeSet(clickButtonStake(roulette.isGreenStakeSet(), greenButton, 4, " Green "));
        updateLabels();
        setPlayButtonDisable();
    }
    //-----------------------------------------------------------------------------------------------------------------

    //------------------------------------Set New Info On Labels-----------------------------------------
    @FXML
    public void setExactNumberLabel() {
        String res = roulette.getStringExactNumber();

        if (res.equals("")) {
            exactNumberLabel.setText("You Did Not Select Exact Number");
        } else {
            exactNumberLabel.setText(res);
        }
    }

    @FXML
    public void setBalanceLabel() {
        balanceLabel.setText("Current balance: " + player.getBalance());
    }

    @FXML
    public void setAmountStake() {
        amountStake.setText("Amount of Stake: " + player.getCurrentStake());
    }

    @FXML
    public void setCurrentStakeLabel() {
        String res = "";
        for (int i = 0; i < 6; i++) {
            if (roulette.getCurStakes(i) != null) res += roulette.getCurStakes(i);
        }
        currentStakeLabel.setText(res);
    }
    //-----------------------------------------------------------------------------------------------------------------

    @FXML
    public void updateLabels() {
        setCurrentStakeLabel();
        setBalanceLabel();
        setExactNumberLabel();
    }


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


    //----------------------------------------------Reset All Components-----------------------------------------------
    //It used before calling this scene or before going to main menu from this scene
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




    @FXML
    public void playRoulette() throws IOException{

        if (roulette.checkStartGame()) {
            player.updateDBBalance();


            roulette.generateResult();
            roulette.checkWinner();

            player.updateDBBalance();
            profit = -1*(profit - player.getBalance());

            player.setProfit(profit);

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

    @FXML
    public void goMainMenu() throws IOException, SQLException, ClassNotFoundException {
        reset();
        player.updateDBBalance();
        pageSwitch.goMainMenu(rouletteAnchor);
    }



    @FXML
    public void doStake() throws IOException {
        reset();
        SetStakeCustomDialog.doStake(borderPane, dialog_window, warningsLabel, amountStake);
    }
}


