package org.simulacion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.simulacion.application.Main;
import org.simulacion.generator.MiddleSquare;
import utils.Path;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class MiddleSquareController {


    @FXML
    private TextField txtDigits;

    @FXML
    private TextArea txtFieldNumbers;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtSead;

    @FXML
    void generateNumbers(ActionEvent event) {
        try {
            if (txtSead.getText().isEmpty() || txtDigits.getText().isEmpty() || txtQuantity.getText().isEmpty()) {
                throw new IllegalArgumentException("Complete todos los campos.");
            }

            int seed = Integer.parseInt(txtSead.getText());
            int digits = Integer.parseInt(txtDigits.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());

            if (digits <= 0 || quantity <= 0) {
                throw new IllegalArgumentException("Dígitos y cantidad deben ser > 0.");
            }

            MiddleSquare middleSquare = new MiddleSquare();
            String numbersText = formatNumbers(middleSquare.getNumbers(seed, digits, quantity));
            txtFieldNumbers.setText(numbersText);

            clearInputFields();

        } catch (NumberFormatException e) {
            showErrorAlert("Error", "Ingrese solo números válidos.");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Error", e.getMessage());
        }
    }


    @FXML
    void returnMain(ActionEvent event) {
        Main.main.setScene(Path.PRINCIPAL_CONTROLLER);
    }

    @FXML
    void selectTest(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        setupIntegerTextField(txtSead);
        setupIntegerTextField(txtDigits);
        setupIntegerTextField(txtQuantity);

        txtSead.setOnMouseClicked(e -> txtFieldNumbers.clear());
        txtDigits.setOnMouseClicked(e -> txtFieldNumbers.clear());
        txtQuantity.setOnMouseClicked(e -> txtFieldNumbers.clear());
    }

    private void setupIntegerTextField(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };
        textField.setTextFormatter(new TextFormatter<>(filter));
    }

    private void addClearTextAreaListener(TextField field) {
        field.setOnMouseClicked(e -> txtFieldNumbers.clear());
        field.setOnKeyPressed(e -> txtFieldNumbers.clear());
    }

    private String formatNumbers(List<Double> numbers) {
        AtomicInteger counter = new AtomicInteger();
        return numbers.stream()
                .map(n -> {
                    int count = counter.getAndIncrement();
                    return n.toString() + ((count + 1) % 5 == 0 && count != numbers.size() - 1 ? "\n" : "  ");
                })
                .collect(Collectors.joining()).trim();
    }

    private void clearInputFields() {
        txtSead.clear();
        txtDigits.clear();
        txtQuantity.clear();
    }

    private void showErrorAlert(String title, String message) {
        new Alert(Alert.AlertType.ERROR, message, ButtonType.OK)
                .showAndWait();
    }
}
