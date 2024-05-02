package com.example.mycasinofx.controllers;

import com.example.mycasinofx.controllers.switchPage.SwitchPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstMenuController {

    private Stage stage;

    @FXML
    private AnchorPane firstAnchor, mainPaneNotChange, greenPain;
    private SwitchPage switchPage;

    public void initialize(){
        switchPage = new SwitchPage();
    }



    @FXML
    public void goMainMenu() throws IOException {
        switchPage.goMainMenu(firstAnchor);
    }

    @FXML
    public void goAboutAuthor() throws IOException {
        switchPage.goAboutAuthor(firstAnchor);
    }

    @FXML
    public void exitCasino(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Casino");
        alert.setHeaderText("You want to exit Casino");
        alert.setContentText("Are you sure?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }
    }



    public AnchorPane getMainAnchor(){
        return mainPaneNotChange;
    }

    public AnchorPane getGreenPain(){
        return greenPain;
    }

}