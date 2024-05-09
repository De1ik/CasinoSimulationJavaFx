package com.example.mycasinofx.Model.FxModels;

import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;

/**
 * The Component class provides utility methods for manipulating JavaFX components (scaling the elements size).
 */
public class Component {

    /**
     * Resizes the specified AnchorPane component to the specified width and height.
     * @param component The AnchorPane component to be resized.
     * @param width The new width of the component.
     * @param height The new height of the component.
     */
    static public void resize(AnchorPane component, double width, double height) {
        double scaleX = width / component.getPrefWidth();
        double scaleY = height / component.getPrefHeight();
        component.getTransforms().add(new Scale(scaleX, scaleY, 0, 0));
    }
}
