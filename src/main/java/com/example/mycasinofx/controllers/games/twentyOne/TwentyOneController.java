package com.example.mycasinofx.controllers.games.twentyOne;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.Model.games.TwentyOne.TwentyOne;
import com.example.mycasinofx.Model.player.Player;
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

public class TwentyOneController implements Initializable {

    @FXML
    private AnchorPane twentyOneStartedAnchor, resultGame;

    @FXML
    private Label result, balanceLabel, amountStake, playerCard3, playerCard4, playerCard5, playerCard6, playerCard7, playerValue, botCard1, botCard2, botValue, dillerValue;

    @FXML
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageViewBot1, imageViewBot2, imageViewBot3, imageViewBot4;

    @FXML
    private Button getCard, showRes;

    @FXML
    private HBox dillerValueHbox;

    @FXML
    BorderPane borderPane;

    TwentyOne twentyOne;
    private Player player;
    private PageSwitchInterface pageSwitch;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        twentyOne = new TwentyOne();
        pageSwitch = new SwitchPage();
        player = Player.getPlayer();
    }


    public void showGameResultDialog(int res) throws IOException {
        ResultDialog.showGameResultDialog(twentyOneStartedAnchor, borderPane, resultGame, 3, twentyOne.getBotValue(), twentyOne.getPlayerValue(), res);
    }

    public void goPlay21() throws IOException {
        pageSwitch.goPlay21(twentyOneStartedAnchor);
    }


    @FXML
    public void setBalanceLabel() {
        balanceLabel.setText("" + player.getBalance());
    }

    @FXML
    public void setCurStakeLabel() {
        amountStake.setText("" + player.getCurrentStake());
    }

    @FXML
    public void updateLabels() {
        setBalanceLabel();
        setCurStakeLabel();
    }

    public void startGame(){
        getCard.setVisible(true);
        showRes.setVisible(true);
        twentyOne.play_game();
        playerValue.setText("" + twentyOne.getPlayerValue());
        updateLabelsPlayerHandCards();
        updateBotHandCards(false);
        updateLabels();
        dillerValueHbox.setVisible(false);
    }

    public void getCard() throws IOException {
        twentyOne.getCardToHand(true);
        playerValue.setText("" + twentyOne.getPlayerValue());
        updateLabelsPlayerHandCards();
        if (twentyOne.getPlayerValue() > 21){
            getCard.setVisible(false);
            showRes.setVisible(false);
            updateBotHandCards(true);
            ResultDialog.showGameResultDialog(twentyOneStartedAnchor, borderPane, resultGame, 3, twentyOne.getBotValue(), twentyOne.getPlayerValue(), 2);
        }

    }



    public void getBotCard(){
        while(twentyOne.tryGetBotCard()) {
            twentyOne.tryGetBotCard();
            updateBotHandCards(false);
        }
    }


    public void compareWinner() throws IOException {
        dillerValueHbox.setVisible(true);
        getCard.setVisible(false);
        showRes.setVisible(false);
        getBotCard();
        updateBotHandCards(true);
        dillerValue.setText(""+twentyOne.getBotValue());
        int res;
        if (twentyOne.getPlayerValue() > twentyOne.getBotValue() || twentyOne.getBotValue() > 21){
            player.setBalance(player.getBalance() + player.getCurrentStake() * 2);
            player.setProfit(player.getCurrentStake() * 2 - player.getCurrentStake());
            result.setText("You win");
            res = 1;
        }
        else if(twentyOne.getPlayerValue() < twentyOne.getBotValue()){
            player.setProfit(-(player.getCurrentStake() * 2));
            result.setText("You lose");
            res = 2;
        }
        else{
            player.setProfit(0);
            result.setText("Draw");
            res = 0;
        }
        showGameResultDialog(res);
    }

    public void showBotCards(){
        ArrayList<String[]> cards = twentyOne.getBotCards();
        botValue.setText("" + twentyOne.getBotValue());
        botCard1.setText(cards.get(0)[0] + " " + cards.get(0)[1] + " " + cards.get(0)[2]);
        botCard2.setText(cards.get(1)[0] + " " + cards.get(1)[1] + " " + cards.get(1)[2]);
    }


    public void updateLabelsPlayerHandCards(){
        setInvisibleButtons();
        ArrayList<String[]> cards = twentyOne.getPlayerCards();
        for (String[] card: cards) {
            System.out.println(card[0] + " " + card[1]);
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


    public void updateBotHandCards(boolean show){
        setInvisibleBotButtons();
        ArrayList<String[]> cards = twentyOne.getBotCards();
        for (String[] card: cards) {
            System.out.println(card[0] + " " + card[1]);
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
//            else if (!imageView5.isVisible()){
//                imageView5.setImage(image);
//                imageView5.setVisible(true);
//            }
//            else if (!imageView6.isVisible()){
//                imageView6.setImage(image);
//                imageView6.setVisible(true);
//            }
//            else if (!imageView7.isVisible()){
//                imageView7.setImage(image);
//                imageView7.setVisible(true);
//            }
        }
    }





    public void setInvisibleButtons(){
        imageView1.setVisible(false);
        imageView2.setVisible(false);
        imageView3.setVisible(false);
        imageView4.setVisible(false);
        imageView5.setVisible(false);
        imageView6.setVisible(false);
        imageView7.setVisible(false);
    }

    public void setInvisibleBotButtons(){
        imageViewBot1.setVisible(false);
        imageViewBot2.setVisible(false);
        imageViewBot3.setVisible(false);
        imageViewBot4.setVisible(false);
    }
}
