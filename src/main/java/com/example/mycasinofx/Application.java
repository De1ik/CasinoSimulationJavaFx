package com.example.mycasinofx;

import com.example.mycasinofx.Model.FxModels.Component;
import com.example.mycasinofx.Model.FxModels.Serialization;
import com.example.mycasinofx.Model.player.Player;
import com.example.mycasinofx.controllers.FirstMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;


public class Application extends javafx.application.Application {
    private static Scale lastTransformation;
    @Override
    public void start(Stage stage){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/firstMenu.fxml"));
            Parent root = loader.load();

            FirstMenuController mainController = loader.getController();


            lastTransformation = new Scale(1, 1, 0, 0);
            mainController.getMainAnchor().getTransforms().add(lastTransformation);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("view/styles/styles.css").toExternalForm());

            Component.resize(mainController.getGreenPain(), Screen.getPrimary().getBounds().getWidth(),  Screen.getPrimary().getBounds().getHeight());

            stage.setScene(scene);

            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.setForceIntegerRenderScale(true);
            stage.getIcons().add(new Image("/casino-logo.png"));




            stage.setFullScreen(true);
            stage.show();








            scene.widthProperty().addListener((observable, oldValue, newValue) -> {
                lastTransformation.setX((newValue.doubleValue() / oldValue.doubleValue()) * lastTransformation.getX());
            });

            scene.heightProperty().addListener((observable, oldValue, newValue) -> {
                lastTransformation.setY((newValue.doubleValue() / oldValue.doubleValue()) * lastTransformation.getY());
            });


            stage.setOnCloseRequest(event -> {
                event.consume();
                exitCasino(stage);
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void exitCasino(Stage stage){

        ImageView imageView = new ImageView(new Image("/casino-logo.png"));
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


        alert.getDialogPane().setGraphic(imageView);

        alert.setTitle("Exit Application");
        alert.setHeaderText("Do you want to exit Casino?");
        alert.setContentText("Are you sure?");

        String css = Objects.requireNonNull(this.getClass().getResource("view/styles/styles.css")).toExternalForm();
        alert.getDialogPane().getStylesheets().add(css);

        alert.initModality(Modality.APPLICATION_MODAL);

        if (alert.showAndWait().get() == ButtonType.OK) {
            Player player = Player.getPlayer();
            Serialization userSettingsToSave = new Serialization(player.getCurrentStake());
            System.out.println("Last user stake was save in 'user_last_stake.ser'");
            userSettingsToSave.save("user_last_stake.ser");
            stage.close();
        }
    }

    public void start() {
        launch();
    }
}