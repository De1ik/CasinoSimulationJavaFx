package com.example.mycasinofx.controllers.custom_dialog_stake;

import com.example.mycasinofx.Model.games.Games;
import com.example.mycasinofx.Model.observeImplementation.Observer;
import com.example.mycasinofx.Model.observeImplementation.ObserverArray;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.controllers.games.usefulComponent.ResultMessage;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DialogControllerResult {
    @FXML
    AnchorPane dialog_anchor;
    @FXML
    Label balanceLabel, amountStake, resultLabel, botValue, playerValue, bigLabelResult;
    int playAgainFxml;

    @FXML
    private AnchorPane mainPane;

    private PageSwitchInterface pageSwitch;

    private final Player player = Player.getPlayer();

    public void initialize(){
        pageSwitch = new SwitchPage();

    }

    public void update21Result(int botValueAmn, int playerValueAmn, int result){
        dialog_anchor.getStyleClass().clear();
        if (result == 1){
            dialog_anchor.getStyleClass().add("result-21-dialog-winner");
            bigLabelResult.setText("You Won");
        }
        else if (result == 2){
            dialog_anchor.getStyleClass().add("result-21-dialog-lose");
            bigLabelResult.setText("You Lose");
        }
        else{
            dialog_anchor.getStyleClass().add("result-21-dialog-draw");
            bigLabelResult.setText("Draw");
        }
        botValue.setText("Bot Value: " + botValueAmn);
        playerValue.setText("Your Value: " + playerValueAmn);
    }
    public void setMainPane(AnchorPane mainPane) {
        this.mainPane = mainPane;
    }

    public void setFxmlType(int curGame) {
        this.playAgainFxml = curGame;
    }

    public void playAgain() throws IOException {
        if (mainPane != null && playAgainFxml != -1){
            switch (playAgainFxml){
                case 1:
                    pageSwitch.goRoulette(mainPane);
                    break;
                case 2:
                    pageSwitch.goSlots(mainPane);
                    break;
                case 3:
                    pageSwitch.goPlay21(mainPane);
                    break;
            }
        }
    }

    public void goMainMenu() throws IOException {
        if (mainPane != null){
            pageSwitch.goMainMenu(mainPane);
        }
    }




    public void updateBalanceStakeLabel(){
        balanceLabel.setText("Current Balance: " + player.getBalance());
        amountStake.setText("Current Stake: " + player.getCurrentStake());
    }

    public void setMessageLabel() {
        ResultMessage.updateMessageLabel(resultLabel, balanceLabel, player);
    }





}
