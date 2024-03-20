package com.example.mycasinofx.controllers.voting.resultVoting;

import com.example.mycasinofx.Model.database.DAOPattern;
import com.example.mycasinofx.Model.database.DatabaseManager;
import com.example.mycasinofx.Model.database.constants.ConstBestGameVotingTable;
import com.example.mycasinofx.Model.database.constants.ConstNewGameVotingTable;
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

public class ResultNewGameController implements Initializable {
    @FXML
    private AnchorPane resultNewGame;
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
        int amountBackarat = DAOPattern.selectNumberVotes("radioBackarat", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        System.out.println("amountBackarat" + amountBackarat);
        int amountPoker = DAOPattern.selectNumberVotes("radioPoker", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        System.out.println("amountPoker" + amountPoker);
        int amountDurak = DAOPattern.selectNumberVotes("radioDurak", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        System.out.println("amountOthers" + amountDurak);
        int amountOthers = DAOPattern.selectNumberVotes("", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        amountOthers -= (amountBackarat + amountPoker + amountDurak);
        System.out.println("amountOthers" + amountOthers);


        addSeries("Backarat", amountBackarat);
        addSeries("Poker", amountPoker);
        addSeries("Durak", amountDurak);
        addSeries("Others", amountOthers);
    }

    private void addSeries(String x, int y) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(x, y));
        barChart.getData().add(series);
    }

    public void mainMenu() throws IOException {
        switchPage.goMainMenu(resultNewGame);
    }

    public void reVote() throws IOException {
        switchPage.goVotingNewGamePageChange(resultNewGame);
    }
}
