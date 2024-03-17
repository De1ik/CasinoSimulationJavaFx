package com.example.mycasinofx.Model.FxModels;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Scale;

public class Component {

    static public void resize(AnchorPane component, double width, double height) {
        double scaleX = width / component.getPrefWidth();
        double scaleY = height / component.getPrefHeight();
        component.getTransforms().add(new Scale(scaleX, scaleY, 0, 0));
    }
}
