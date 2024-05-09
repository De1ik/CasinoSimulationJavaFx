package com.example.mycasinofx.controllers.voting.resultVoting;

import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.constants.ConstBestGameVotingTable;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller class for the result page of the best game voting.
 */
public class ResultBestGameController implements Initializable {
    /**
     * The AnchorPane for the result of the best game voting scene.
     */
    @FXML
    private AnchorPane resultBestGame;

    /**
     * The BarChart displaying the voting results.
     */
    @FXML
    private BarChart<String, Number> barChart;

    /**
     * Label displaying the total number of voters.
     */
    @FXML
    private Label allVoters;

    /**
     * Label displaying the number of voters who chose Roulette.
     */
    @FXML
    private Label rouletteV;

    /**
     * Label displaying the number of voters who chose Slot.
     */
    @FXML
    private Label slotV;

    /**
     * Label displaying the number of voters who chose Twenty One.
     */
    @FXML
    private Label twentyOneV;

    /**
     * Instance of SwitchPage for handling scene navigation.
     */
    private final SwitchPage switchPage = new SwitchPage();

    /**
     * Initializes the controller and sets up the bar chart with the voting results.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            setBarChart();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets up the bar chart with the voting results.
     * @throws SQLException if a SQL exception occurs
     * @throws ClassNotFoundException if a required class is not found
     */
    private void setBarChart() throws SQLException, ClassNotFoundException {
        int amountRouletteVotes = DAOPattern.selectNumberVotes("radioRoulette", ConstBestGameVotingTable.BEST_GAME_TABLE, ConstBestGameVotingTable.NAME);
        int amountSlotVotes = DAOPattern.selectNumberVotes("radioSlot", ConstBestGameVotingTable.BEST_GAME_TABLE, ConstBestGameVotingTable.NAME);
        int amountTwentyOneVotes = DAOPattern.selectNumberVotes("radioTwentyOne", ConstBestGameVotingTable.BEST_GAME_TABLE, ConstBestGameVotingTable.NAME);


        int amount = amountRouletteVotes + amountSlotVotes + amountTwentyOneVotes + 11;
        allVoters.setText("The number of voters: "+ amount);
        rouletteV.setText("Roulette Number Voters: "+ (amountRouletteVotes+1));
        slotV.setText("Slot number of voters: "+ (amountSlotVotes+4));
        twentyOneV.setText("Twenty One number of voters: "+ (amountTwentyOneVotes+6));


        addSeries("roulette", amountRouletteVotes+1);
        addSeries("slots", amountSlotVotes+4);
        addSeries("21", amountTwentyOneVotes+6);
    }

    /**
     * Adds a series to the bar chart with the specified x value and y value.
     * @param x The x value for the series
     * @param y The y value for the series
     */
    private void addSeries(String x, int y) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x, y));
        barChart.getData().add(series);
    }

    /**
     * Handles the action of navigating back to the main menu.
     * @throws IOException if an error occurs during scene switching
     * @throws SQLException if an SQL exception occurs
     * @throws ClassNotFoundException if a required class is not found
     */
    public void mainMenu() throws IOException, SQLException, ClassNotFoundException {
        switchPage.goMainMenu(resultBestGame);
    }

    /**
     * Handles the action of navigating back to the voting page for the best game.
     * @throws IOException if an error occurs during scene switching
     * @throws SQLException if an SQL exception occurs
     * @throws ClassNotFoundException if a required class is not found
     */
    public void reVote() throws IOException, SQLException, ClassNotFoundException {
        switchPage.goVotingBestGamePageChange(resultBestGame);
    }
}
