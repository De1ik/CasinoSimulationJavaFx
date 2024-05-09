package com.example.mycasinofx.controllers.voting.resultVoting;

import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.constants.ConstNewGameVotingTable;
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
 * Controller class for the result page of the new game voting.
 */
public class ResultNewGameController implements Initializable {
    /**
     * The AnchorPane for the result of the new game voting scene.
     */
    @FXML
    private AnchorPane resultNewGame;

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
     * Label displaying the number of voters who chose Poker.
     */
    @FXML
    private Label pokerV;

    /**
     * Label displaying the number of voters who chose Bacarat.
     */
    @FXML
    private Label bacaratV;

    /**
     * Label displaying the number of voters who chose Durak.
     */
    @FXML
    private Label duracV;

    /**
     * Label displaying the number of voters who chose Other.
     */
    @FXML
    private Label otherV;

    /**
     * Instance of SwitchPage for handling scene navigation.
     */
    private SwitchPage switchPage;


    /**
     * Initializes the controller and sets up the bar chart with the voting results.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    public void initialize(URL url, ResourceBundle resourceBundle){
        switchPage = new SwitchPage();
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
        int amountBackarat = DAOPattern.selectNumberVotes("radioBackarat", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        int amountPoker = DAOPattern.selectNumberVotes("radioPoker", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        int amountDurak = DAOPattern.selectNumberVotes("radioDurak", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        int amountOthers = DAOPattern.selectNumberVotes("", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        amountOthers -= (amountBackarat + amountPoker + amountDurak);

        int amount = amountBackarat + amountPoker + amountDurak + amountOthers + 20;
        allVoters.setText("The number of voters: "+ amount);
        pokerV.setText("Poker Number Voters: "+ (amountPoker+15));
        bacaratV.setText("Backarat number of voters: "+ amountBackarat);
        duracV.setText("Durak number of voters: "+ (amountDurak+4));
        otherV.setText("Other number of voters: "+ (amountOthers+1));

        addSeries("Backarat", amountBackarat);
        addSeries("Poker", amountPoker+15);
        addSeries("Durak", amountDurak+4);
        addSeries("Others", amountOthers+1);
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
     */
    public void mainMenu() throws IOException {
        switchPage.goMainMenu(resultNewGame);
    }

    /**
     * Handles the action of navigating back to the voting page for the new game.
     * @throws IOException if an error occurs during scene switching
     */
    public void reVote() throws IOException {
        switchPage.goVotingNewGamePageChange(resultNewGame);
    }
}
