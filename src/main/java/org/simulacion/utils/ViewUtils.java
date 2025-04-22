package org.simulacion.utils;

import javafx.scene.control.*;


import java.util.List;
import java.util.Optional;
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

    public static void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static Optional<String> showInputDialog(String title, String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(message);
        return dialog.showAndWait();
    }


}



