package com.example.mycasinofx.controllers.games.slots;

import com.example.mycasinofx.Application;
import com.example.mycasinofx.Model.games.ResultGenericClass;
import com.example.mycasinofx.Model.games.Slots.Slots;
import com.example.mycasinofx.Model.games.strategy_pattern.GameResultStrategy;
import com.example.mycasinofx.Model.games.strategy_pattern.SlotResultImpl;
import com.example.mycasinofx.Model.Player;
import com.example.mycasinofx.controllers.custom_dialog_stake.SetStakeCustomDialog;
import com.example.mycasinofx.controllers.games.usefulComponent.ResultMessage;
import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller class for managing the UI of the SlotsController game page.
 */
public class SlotsController implements Initializable {

    /**
     * Represents the player participating in the slots game.
     */
    private Player player;

    /**
     * The root anchor pane of the slots game UI.
     */
    @FXML
    private AnchorPane slotsAnchor;

    /**
     * The dialog window used for user interaction.
     */
    @FXML
    private AnchorPane dialog_window;

    /**
     * The label displaying the player's balance.
     */
    @FXML
    private Label balanceLabel;

    /**
     * The label displaying the current stake amount.
     */
    @FXML
    private Label amountStake;

    /**
     * The label used for displaying warnings or game results.
     */
    @FXML
    private Label warningsLabel;

    /**
     * The label used for displaying game results.
     */
    @FXML
    private Label resultLabel;

    /**
     * The border pane containing the slots game UI elements.
     */
    @FXML
    private BorderPane borderPane;

    /**
     * The image view for the first slot number's upward arrow.
     */
    @FXML
    private ImageView numb1upImg;

    /**
     * The image view for the first slot number.
     */
    @FXML
    private ImageView numb1Img;

    /**
     * The image view for the first slot number's downward arrow.
     */
    @FXML
    private ImageView numb1downImg;

    /**
     * The image view for the second slot number's upward arrow.
     */
    @FXML
    private ImageView numb2upImg;

    /**
     * The image view for the second slot number.
     */
    @FXML
    private ImageView numb2Img;

    /**
     * The image view for the second slot number's downward arrow.
     */
    @FXML
    private ImageView numb2downImg;

    /**
     * The image view for the third slot number's upward arrow.
     */
    @FXML
    private ImageView numb3upImg;

    /**
     * The image view for the third slot number.
     */
    @FXML
    private ImageView numb3Img;

    /**
     * The image view for the third slot number's downward arrow.
     */
    @FXML
    private ImageView numb3downImg;

    /**
     * The button used to initiate the slots game.
     */
    @FXML
    private Button playGameButton;

    /**
     * The button used to skip the animation of the slots game.
     */
    @FXML
    private Button skipButton;

    /**
     * The grid pane containing the slots game UI elements.
     */
    @FXML
    private GridPane slotGridPane;

    /**
     * The profit earned or lost during the slots game.
     */
    private double profit;

    /**
     * The image representing the slot number seven.
     */
    private Image slotSevenImage;

    /**
     * The image representing the cherry symbol in the slots game.
     */
    private Image slotCherryImage;

    /**
     * The image representing the pumpkin symbol in the slots game.
     */
    private Image slotPumpkinImage;

    /**
     * The image representing the diamond symbol in the slots game.
     */
    private Image slotDiamantImage;

    /**
     * The image representing the grapes symbol in the slots game.
     */
    private Image slotGrapesImage;

    /**
     * Flag indicating whether to skip the animation of the slots game.
     */
    private boolean skipAnimation;

    /**
     * The strategy for displaying game results logs in the slots game.
     */
    private GameResultStrategy gameResultStrategy;

    /**
     * The instance for managing the slots game logic.
     */
    private Slots slots;

    /**
     * The interface for switching between different pages or scenes in the application.
     */
    private PageSwitchInterface pageSwitch;

    /**
     * The instance for managing generic game results.
     */
    private ResultGenericClass resultGenericClass;


    /**
     * Initializes the controller.
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
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
        gameResultStrategy = new SlotResultImpl();
    }

    /**
     * Switches to the main menu scene.
     * @throws IOException If an I/O error occurs.
     */
    public void goMainMenu() throws IOException {
        pageSwitch.goMainMenu(slotsAnchor);
    }

    /**
     * Updates the balance label.
     */
    @FXML
    public void setBalanceLabel() {
        balanceLabel.setText(" " + player.getBalance());
    }

    /**
     * Sets the message label.
     */
    public void setMessageLabel() {
        ResultMessage.updateMessageLabel(resultLabel, balanceLabel, player);
    }

    /**
     * Updates the current stake label.
     */
    @FXML
    public void setCurStakeLabel() {
        amountStake.setText("Current Stake is: " + player.getCurrentStake());
    }

    /**
     * Updates the balance and current stake labels.
     */
    @FXML
    public void updateLabels() {
        setBalanceLabel();
        setCurStakeLabel();
    }

    /**
     * Sets the result labels for the slots.
     * @param generalArrayCopy The copy of the general array containing slot numbers.
     * @param finishIndex1     The index of the first slot result.
     * @param finishIndex2     The index of the second slot result.
     * @param finishIndex3     The index of the third slot result.
     */
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

    /**
     * Starts the slots game.
     */
    @FXML
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
            gameResultStrategy.showGameResult(profit);
            player.updateDBBalance();

            animation();
            slots.reset();
        }
        else{
            warningsLabel.setText("Insufficient Balance");
        }
    }

    /**
     * Performs the animation for the slots.
     */
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

                    setSlotImage(numb1upImg, generalArrayCopy.get(0).get(((curCounter + startIndex1 + 2) % sizeArray1)));
                    setSlotImage(numb1Img, generalArrayCopy.get(0).get(((curCounter + startIndex1 + 1) % sizeArray1)));
                    setSlotImage(numb1downImg, generalArrayCopy.get(0).get(((curCounter + startIndex1) % sizeArray1)));

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
            slots.reset();
        }).start();
    }

    /**
     * Performs actions after the animation finishes.
     */
    @FXML
    public void actionAfterAnimation() {
        skipButton.setVisible(false);
        playGameButton.setVisible(true);
        updateLabels();
        skipAnimation = false;
        setMessageLabel();
    }

    /**
     * Skips the animation.
     */
    @FXML
    public void skipAnimation() {
        skipAnimation = true;
    }

    /**
     * Sets the slot image for the given image view based on the number.
     * @param imageView The image view to set the image.
     * @param number    The number indicating the slot image.
     */
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

    /**
     * Opens the dialog window for setting stake.
     * @throws IOException If an I/O error occurs.
     */
    public void doStake() throws IOException {
        SetStakeCustomDialog.doStake(borderPane, dialog_window, warningsLabel, amountStake);
    }
}