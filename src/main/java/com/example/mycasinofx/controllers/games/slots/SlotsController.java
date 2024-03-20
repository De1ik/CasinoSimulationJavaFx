package com.example.mycasinofx.controllers.games.slots;

import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.Model.FxModels.SceneSwitch;

import com.example.mycasinofx.controllers.switchPage.PageSwitchInterface;
import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class SlotsController {

    private final Player player = Player.getPlayer();

    @FXML
    private AnchorPane slotsAnchor;

    private PageSwitchInterface pageSwitch = new SwitchPage();


    public void goMainMenu() throws IOException {
        pageSwitch.goMainMenu(slotsAnchor);
    }


    public void playGame() throws SQLException, ClassNotFoundException {
        player.setBalance(player.getBalance() - player.getCurrentStake());
        System.out.println("CUR BAL: " + player.getBalance());
        player.updateDBBalance();
    }
}
