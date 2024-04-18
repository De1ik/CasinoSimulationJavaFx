package com.example.mycasinofx.controllers.games.slots;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.Model.games.ResultGenericClass;
import com.example.mycasinofx.Model.games.Slots.Slots;
import com.example.mycasinofx.Model.player.Player;

import com.example.mycasinofx.controllers.custom_dialog_stake.SetStakeCustomDialog;
import com.example.mycasinofx.controllers.games.usefulComponent.ResultMessage;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SlotsController implements Initializable {

    private Player player;

    @FXML
    private AnchorPane slotsAnchor, dialog_window;
    @FXML
    private Label balanceLabel, amountStake, warningsLabel, resultLabel;
    @FXML
    private BorderPane borderPane;
    @FXML
    private ImageView numb1upImg, numb1Img, numb1downImg, numb2upImg, numb2Img, numb2downImg, numb3upImg, numb3Img, numb3downImg;
    @FXML
    private Button playGameButton, skipButton;
    @FXML
    private GridPane slotGridPane;

    private double profit;

    Image slotSevenImage, slotCherryImage, slotPumpkinImage, slotDiamantImage, slotGrapesImage;

    private boolean skipAnimation;

    private Slots slots;

    private PageSwitchInterface pageSwitch;
    private ResultGenericClass resultGenericClass;



    public void initialize(URL url, ResourceBundle resourceBundle) {
        skipButton.setVisible(false);
        player = Player.getPlayer();
        slotSevenImage = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("view/picture/slot_element_7.png")));
        slotCherryImage = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("view/picture/slot_element_cherry.png")));
        slotPumpkinImage = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("view/picture/slot_element_pumpkin.png")));
        slotDiamantImage = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("view/picture/slot_element_diamant.png")));
        slotGrapesImage = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("view/picture/slot_element_grapes.png")));
        slots = Slots.getSlots();
        pageSwitch = new SwitchPage();
        resultGenericClass = ResultGenericClass.getResult();
    }


    public void goMainMenu() throws IOException {
        pageSwitch.goMainMenu(slotsAnchor);
    }


    @FXML
    public void setBalanceLabel() {
        balanceLabel.setText(" " + player.getBalance());
    }

    public void setMessageLabel() {
        ResultMessage.updateMessageLabel(resultLabel, balanceLabel, player);
    }

    @FXML
    public void setCurStakeLabel() {
        amountStake.setText("Current Stake is: " + player.getCurrentStake());
    }

    public void updateLabels() {
        setBalanceLabel();
        setCurStakeLabel();
    }


    @FXML
    synchronized public void setLabelsResult(ArrayList<ArrayList<Integer>> generalArrayCopy, int finishIndex1, int finishIndex2, int finishIndex3) {


        setSlotImage(numb1upImg, generalArrayCopy.get(0).get(slots.getPrevIndexArray(generalArrayCopy, 0, finishIndex1)));
        setSlotImage(numb1Img, generalArrayCopy.get(0).get(finishIndex1));
        setSlotImage(numb1downImg, generalArrayCopy.get(0).get(slots.getNextIndexArray(generalArrayCopy, 0, finishIndex1)));


        setSlotImage(numb2upImg, generalArrayCopy.get(1).get(slots.getPrevIndexArray(generalArrayCopy, 1, finishIndex2)));
        setSlotImage(numb2Img, generalArrayCopy.get(1).get(finishIndex2));
        setSlotImage(numb2downImg, generalArrayCopy.get(1).get(slots.getNextIndexArray(generalArrayCopy, 1, finishIndex2)));


        setSlotImage(numb3upImg, generalArrayCopy.get(2).get(slots.getPrevIndexArray(generalArrayCopy, 2, finishIndex3)));
        setSlotImage(numb3Img, generalArrayCopy.get(2).get(finishIndex3));
        setSlotImage(numb3downImg, generalArrayCopy.get(2).get(slots.getNextIndexArray(generalArrayCopy, 2, finishIndex3)));

    }


    public void playGame() {
        if (player.tryDoStake()) {
            profit = player.getBalance();
            slots.generateResult();

            slots.checkWinner();
            int stake = (int) resultGenericClass.getResultValue();


            player.setBalance(player.getBalance() - player.getCurrentStake());
            balanceLabel.setText(""+player.getBalance());
            if (stake != -1) {
                player.setBalance(player.getBalance() + player.getCurrentStake() * stake);
            }

            profit = -1*(profit - player.getBalance());
            player.setProfit(profit);
            player.updateDBBalance();

            animation();
            slots.reset();
        }
        else{
            warningsLabel.setText("Insufficient Balance");
        }
    }


    @FXML
    public void animation() {

        ArrayList<ArrayList<Integer>> generalArray = slots.getGeneralArray();

        //make the copy, because we reset the Slots data before enter in new Thread
        ArrayList<ArrayList<Integer>> generalArrayCopy = new ArrayList<>();
        for (ArrayList<Integer> innerList : generalArray) {
            generalArrayCopy.add(new ArrayList<>(innerList));
        }

        int sizeArray1 = generalArray.get(0).size();

        int startIndex1 = slots.getStartIndex().get(0);
        int startIndex2 = slots.getStartIndex().get(1);
        int startIndex3 = slots.getStartIndex().get(2);

        int finishIndex1 = slots.getFinishIndex().get(0);
        int finishIndex2 = slots.getFinishIndex().get(1);
        int finishIndex3 = slots.getFinishIndex().get(2);

        new Thread(() -> {
            skipButton.setVisible(true);
            playGameButton.setVisible(false);
            for (int i = 0; i < 50 && !skipAnimation; i++) {
                double duration = (i * 2 * 2) + (i * 2 * 2);
                int curCounter = i;
                Platform.runLater(() -> {

//                    setSlotImage(numb1outImg, generalArrayCopy.get(0).get(((curCounter + startIndex1) % sizeArray1)));
//                    checkAnimation(numb1upImg, 2);
                    setSlotImage(numb1upImg, generalArrayCopy.get(0).get(((curCounter + startIndex1 + 2) % sizeArray1)));
//                    checkAnimation(numb1upImg, 2);
                    setSlotImage(numb1Img, generalArrayCopy.get(0).get(((curCounter + startIndex1 + 1) % sizeArray1)));
//                    checkAnimation(numb1Img, 2);
                    setSlotImage(numb1downImg, generalArrayCopy.get(0).get(((curCounter + startIndex1) % sizeArray1)));
//                    checkAnimation(numb1downImg, 2);


                    setSlotImage(numb2upImg, generalArrayCopy.get(1).get(((curCounter + startIndex2 + 2) % sizeArray1)));
                    setSlotImage(numb2Img, generalArrayCopy.get(1).get(((curCounter + startIndex2 + 1) % sizeArray1)));
                    setSlotImage(numb2downImg, generalArrayCopy.get(1).get(((curCounter + startIndex2) % sizeArray1)));


                    setSlotImage(numb3upImg, generalArrayCopy.get(2).get(((curCounter + startIndex3 + 2) % sizeArray1)));
                    setSlotImage(numb3Img, generalArrayCopy.get(2).get(((curCounter + startIndex3 + 1) % sizeArray1)));
                    setSlotImage(numb3downImg, generalArrayCopy.get(2).get(((curCounter + startIndex3) % sizeArray1)));

                });
                try {
                    Thread.sleep((long) duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> {
                if (skipAnimation)
                    setLabelsResult(generalArrayCopy, finishIndex1, finishIndex2, finishIndex3);
                actionAfterAnimation();
            });
        }).start();
    }

//    @FXML
//    public void testAnimation() {
//        double startY = numb1upImg.localToScene(numb1upImg.getBoundsInLocal()).getCenterY();
//        double finishY = numb1downImg.localToScene(numb1downImg.getBoundsInLocal()).getCenterY();
//
//
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.millis(1000 / 60), event -> {
////                    moveImageViewDown(numb1upImg, startY, finishY);
//                    moveImageViewDown(numb1Img, startY, finishY);
////                    moveImageViewDown(numb1downImg, startY, finishY);
//                })
//        );
//        timeline.setCycleCount(Timeline.INDEFINITE); // Бесконечный цикл
//        timeline.play();
//
//
//    }

//    public static void moveImageViewDown(ImageView imageView, double startY, double finishY) {
//        double currentY = imageView.localToScene(imageView.getBoundsInLocal()).getCenterY();
//
//        if (currentY > finishY) {
//            imageView.setLayoutY(startY);
//            System.out.println("New Y: " + imageView.getLayoutY());
//            System.out.println("CHANGED PLACE!");
//        }
//        else {
//            TranslateTransition transition = new TranslateTransition(Duration.millis(1000 / 60), imageView);
//            transition.setByY(1);
//            transition.play();
//        }
//    }


    @FXML
    public void actionAfterAnimation() {
        skipButton.setVisible(false);
        playGameButton.setVisible(true);
        updateLabels();
        skipAnimation = false;
        setMessageLabel();
    }

    @FXML
    public void skipAnimation() {
        skipAnimation = true;
    }

    @FXML
    void setSlotImage(ImageView imageView, int number) {
        switch (number) {
            case 1:
                imageView.setImage(slotPumpkinImage);
                break;
            case 2:
                imageView.setImage(slotDiamantImage);
                break;
            case 3:
                imageView.setImage(slotSevenImage);
                break;
            case 4:
                imageView.setImage(slotCherryImage);
                break;
            case 5:
                imageView.setImage(slotGrapesImage);
                break;
        }

    }

//    public void checkAnimation(ImageView imageView, double duration) {
//
//        double startX = imageView.getX();
//        double startY = imageView.getY();
//
//        TranslateTransition transition = new TranslateTransition();
//        transition.setDuration(Duration.seconds(duration));
//        transition.setNode(imageView);
//        transition.setToY(imageView.getY() + 100);
//
//        transition.setOnFinished(event -> {
//            // Уменьшаем счетчик, когда анимация завершена
//            imageView.setX(startX);
//            imageView.setY(startY);
//        });
//
////        transition.setAutoReverse(true);
////        transition.setCycleCount(TranslateTransition.INDEFINITE);
//        // Запуск анимации
//        transition.play();
//    }

    public void doStake() throws IOException {
        SetStakeCustomDialog.doStake(borderPane, dialog_window, warningsLabel, amountStake);
    }
}