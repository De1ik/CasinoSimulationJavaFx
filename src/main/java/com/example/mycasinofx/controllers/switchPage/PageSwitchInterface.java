package com.example.mycasinofx.controllers.switchPage;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public interface PageSwitchInterface {
    public void goMainMenu(AnchorPane anchorPane) throws IOException;
    public void goLogin(AnchorPane anchorPane) throws IOException;
    public void goRegister(AnchorPane anchorPane) throws IOException;
    public void goSlots(AnchorPane anchorPane) throws IOException;
    public void goPlay21(AnchorPane anchorPane) throws IOException;
    public void go21(AnchorPane anchorPane) throws IOException;
    public void goRoulette(AnchorPane anchorPane) throws IOException;
    public void goRouletteResult(AnchorPane anchorPane) throws IOException;
    public void goFullList(AnchorPane anchorPane) throws IOException;
    public void confirmExit();
    public void checkLoginUser(AnchorPane anchorPane, String path) throws IOException;
    public void goVotingPageChange(AnchorPane anchorPane) throws IOException;
    public void goVotingBestGamePageChange(AnchorPane anchorPane) throws IOException;
    public void goVotingNewGamePageChange(AnchorPane anchorPane) throws IOException;

    public void goVotingNewGameResult(AnchorPane anchorPane) throws IOException;

    public void goVotingBestGameResult(AnchorPane anchorPane) throws IOException;
}
