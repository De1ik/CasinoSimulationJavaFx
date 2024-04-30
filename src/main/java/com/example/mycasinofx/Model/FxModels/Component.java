package com.example.mycasinofx.Model.UsefulComponents;

import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;

public class Component {

    static public void resize(AnchorPane component, double width, double height) {
        double scaleX = width / component.getPrefWidth();
        double scaleY = height / component.getPrefHeight();
        component.getTransforms().add(new Scale(scaleX, scaleY, 0, 0));
    }
}
