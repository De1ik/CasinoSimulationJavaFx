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

public class ResultBestGameController implements Initializable {
    @FXML
    private AnchorPane resultBestGame;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private Label allVoters, rouletteV, slotV, twentyOneV;
    private final SwitchPage switchPage = new SwitchPage();





    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            setBarChart();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setBarChart() throws SQLException, ClassNotFoundException {
        int amountRouletteVotes = DAOPattern.selectNumberVotes("radioRoulette", ConstBestGameVotingTable.BEST_GAME_TABLE, ConstBestGameVotingTable.NAME);
        int amountSlotVotes = DAOPattern.selectNumberVotes("radioSlot", ConstBestGameVotingTable.BEST_GAME_TABLE, ConstBestGameVotingTable.NAME);
        int amountTwentyOneVotes = DAOPattern.selectNumberVotes("radioTwentyOne", ConstBestGameVotingTable.BEST_GAME_TABLE, ConstBestGameVotingTable.NAME);


        int amount = amountRouletteVotes + amountSlotVotes + amountTwentyOneVotes + 11;
        allVoters.setText("The number of voters: "+ amount);
        rouletteV.setText("Roulette Number Voters: "+ (amountRouletteVotes+1));
        slotV.setText("Slot number of voters: "+ amountSlotVotes+4);
        twentyOneV.setText("Twenty One number of voters: "+ (amountTwentyOneVotes+6));


        addSeries("roulette", amountRouletteVotes+1);
        addSeries("slots", amountSlotVotes+4);
        addSeries("21", amountTwentyOneVotes+6);
    }

    private void addSeries(String x, int y) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x, y));
        barChart.getData().add(series);
    }

    public void mainMenu() throws IOException, SQLException, ClassNotFoundException {
        switchPage.goMainMenu(resultBestGame);
    }

    public void reVote() throws IOException, SQLException, ClassNotFoundException {
        switchPage.goVotingBestGamePageChange(resultBestGame);
    }
}
