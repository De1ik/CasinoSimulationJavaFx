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

public class ResultNewGameController implements Initializable {
    @FXML
    private AnchorPane resultNewGame;
    @FXML
    private BarChart<String, Number> barChart;
    private final SwitchPage switchPage = new SwitchPage();
    @FXML
    private Label allVoters;




    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            setBarChart();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setBarChart() throws SQLException, ClassNotFoundException {
        int amountBackarat = DAOPattern.selectNumberVotes("radioBackarat", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        int amountPoker = DAOPattern.selectNumberVotes("radioPoker", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        int amountDurak = DAOPattern.selectNumberVotes("radioDurak", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        int amountOthers = DAOPattern.selectNumberVotes("", ConstNewGameVotingTable.NEW_GAME_TABLE, ConstNewGameVotingTable.NAME);
        amountOthers -= (amountBackarat + amountPoker + amountDurak);

        int amount = amountBackarat + amountPoker + amountDurak + amountOthers + 20;
        System.out.println(amount);
        allVoters.setText("The number of voters: "+ amount);

        addSeries("Backarat", amountBackarat);
        addSeries("Poker", amountPoker+15);
        addSeries("Durak", amountDurak+4);
        addSeries("Others", amountOthers+1);
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
