package com.example.mycasinofx.Model.FxModels;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class NumericInputDialog extends Dialog<Double> {

    private TextField textField;

    public NumericInputDialog(String title, String headerText, String contentText) {
        setTitle(title);
        setHeaderText(headerText);
        setContentText(contentText);

        // Создаем текстовое поле
        textField = new TextField();
        textField.setPromptText("Введите число");

        // Ограничиваем ввод только числовыми символами
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                textField.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });

        // Упаковываем элементы в вертикальный контейнер
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label(contentText), textField);
        getDialogPane().setContent(vbox);

        // Устанавливаем кнопки "ОК" и "Отмена"
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Устанавливаем результат при нажатии на кнопку "ОК"
        setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    // Пытаемся преобразовать введенное значение в число
                    double value = Double.parseDouble(textField.getText());
                    return value;
                } catch (NumberFormatException e) {
                    // Если не удалось преобразовать, возвращаем null
                    return null;
                }
            }
            return null;
        });
    }

    public Optional<Double> showNumericInputDialog() {
        return showAndWait();
    }
}

