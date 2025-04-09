package org.simulacion.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public final class ViewUtils {

    public static void setupIntegerTextField(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            return newText.matches("\\d*") ? change : null;
        };
        textField.setTextFormatter(new TextFormatter<>(filter));
    }

    public static String formatNumbers(List<Double> numbers) {
        AtomicInteger counter = new AtomicInteger();
        return numbers.stream()
                .map(n -> {
                    int count = counter.getAndIncrement();
                    return n.toString() + ((count + 1) % 5 == 0 && count != numbers.size() - 1 ? "\n" : "  ");
                })
                .collect(Collectors.joining()).trim();
    }

    public static void showErrorAlert(String title, String message) {
        new Alert(Alert.AlertType.ERROR, message, ButtonType.OK).showAndWait();
    }
}
