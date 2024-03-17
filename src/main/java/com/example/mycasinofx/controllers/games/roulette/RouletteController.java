package com.example.mycasinofx.controllers.games.roulette;


import com.example.mycasinofx.Model.FxModels.SceneSwitch;

import com.example.mycasinofx.Model.games.Roulette.RouletteSetUp;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.Model.games.Roulette.Roulette;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.io.IOException;


public class RouletteController {
    @FXML
    private AnchorPane rouletteAnchor;
    @FXML
    private Label balanceLabel, currentStakeLabel, exactNumberLabel;
    @FXML
    private Button evenButton, oddButton, redButton, blackButton, greenButton, playRouletteButton, goMainMenuButton;
    @FXML
    private GridPane gridPaneRoulette;
    @FXML
    private Polygon zeroPolygon;

    private final Player player;
    private final Roulette roulette;
    private PageSwitchInterface pageSwitch;

    public RouletteController() {
        roulette = Roulette.getRoulette();
        player = Player.getPlayer();
    }


    @FXML
    public void initialize() {
        pageSwitch = new SwitchPage();
        evenButton.getStyleClass().add("custom-button");
        oddButton.getStyleClass().add("custom-button");
        redButton.getStyleClass().add("custom-button");
        blackButton.getStyleClass().add("custom-button");
        greenButton.getStyleClass().add("custom-button");
        playRouletteButton.getStyleClass().add("custom-button-management");
        goMainMenuButton.getStyleClass().add("custom-button-management");


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
                    roulette.setExactNumber(true);
                    if (label != null) {
                        label.setBackground(Background.fill(Color.YELLOW));
                    } else {
                        zero.setFill(Color.YELLOW);
                    }
                    roulette.addExactNumberArray(number);
                    player.setBalance(player.getBalance() - player.getCurrentStake());
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
    @FXML
    public boolean clickButtonStake(boolean flag, Button button, int index, String string) {
        if (flag) {
            //change the color due css
            button.getStyleClass().clear();
            button.getStyleClass().add("custom-button");
            //------------------------------------------

            flag = false;
            roulette.setCurStakes(null, index);
            player.setBalance(player.getBalance() + player.getCurrentStake());
        } else {
            flag = true;
            playRouletteButton.setDisable(false);

            //change the color due css
            button.getStyleClass().clear();
            button.getStyleClass().add("custom-button-active");
            //------------------------------------------
            roulette.setCurStakes(string, index);
            player.setBalance(player.getBalance() - player.getCurrentStake());
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
        roulette.setBlackStakeSet(clickButtonStake(roulette.isGreenStakeSet(), greenButton, 4, " Green "));
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
            exactNumberLabel.setText("Exact Number Which you Select: " + res);
        }
    }

    @FXML
    public void setBalanceLabel() {
        balanceLabel.setText("Current balance: " + player.getBalance());
    }

    @FXML
    public void setCurrentStakeLabel() {
        String res = "";
        for (int i = 0; i < 6; i++) {
            if (roulette.getCurStakes(i) != null) res += roulette.getCurStakes(i);
        }
        currentStakeLabel.setText("Current stake is: " + res);
    }
    //-----------------------------------------------------------------------------------------------------------------

    @FXML
    public void updateLabels() {
        setCurrentStakeLabel();
        setBalanceLabel();
        setExactNumberLabel();
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
        //update the labels
        updateLabels();
        setPlayButtonDisable();
    }




    @FXML
    public void playRoulette() throws IOException {

        if (roulette.checkStartGame()) {
            new SceneSwitch(rouletteAnchor, "view/Games/rouletteResult.fxml");

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
    public void goMainMenu() throws IOException {
        reset();
        pageSwitch.goMainMenu(rouletteAnchor);
    }
}


