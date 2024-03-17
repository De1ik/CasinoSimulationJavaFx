package com.example.mycasinofx.controllers.voting.resultVoting;

import com.example.mycasinofx.Model.database.DatabaseManager;
import com.example.mycasinofx.Model.database.constants.ConstBestGameVotingTable;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResultBestGameController implements Initializable {
    @FXML
    private AnchorPane resultBestGame;
    @FXML
    private BarChart<String, Number> barChart;
    private final SwitchPage switchPage = new SwitchPage();
    private final DatabaseManager databaseManager = new DatabaseManager();




    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            setBarChart();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setBarChart() throws SQLException, ClassNotFoundException {
        int amountRouletteVotes = databaseManager.selectNumberVotes("radioRoulette", ConstBestGameVotingTable.BEST_GAME_TABLE, ConstBestGameVotingTable.NAME);
        int amountSlotVotes = databaseManager.selectNumberVotes("radioSlot", ConstBestGameVotingTable.BEST_GAME_TABLE, ConstBestGameVotingTable.NAME);
        int amountTwentyOneVotes = databaseManager.selectNumberVotes("radioTwentyOne", ConstBestGameVotingTable.BEST_GAME_TABLE, ConstBestGameVotingTable.NAME);

        addSeries("roulette", amountRouletteVotes);
        addSeries("slots", amountSlotVotes);
        addSeries("21", amountTwentyOneVotes);
    }

    private void addSeries(String x, int y) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x, y));
        barChart.getData().add(series);
    }

    public void mainMenu() throws IOException {
        switchPage.goMainMenu(resultBestGame);
    }

    public void reVote() throws IOException {
        switchPage.goVotingBestGamePageChange(resultBestGame);
    }
}
