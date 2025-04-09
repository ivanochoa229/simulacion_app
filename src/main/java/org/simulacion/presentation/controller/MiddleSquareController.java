package org.simulacion.presentation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.simulacion.configuration.AppConfig;
import org.simulacion.exception.InvalidInputException;
import org.simulacion.generator.MiddleSquare;
import org.simulacion.presentation.dto.MiddleSquareRequest;
import org.simulacion.service.MiddleSquareService;
import utils.Path;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class MiddleSquareController {

    private final MiddleSquareService service;

    public MiddleSquareController() {
        this.service = new MiddleSquareService(new MiddleSquare());
    }

    @FXML
    private TextField txtDigits;

    @FXML
    private TextArea txtFieldNumbers;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtSead;

    @FXML
    void generateNumbers() {
        try {
            MiddleSquareRequest request = new MiddleSquareRequest(  Integer.parseInt(txtSead.getText()),
                                                                    Integer.parseInt(txtDigits.getText()),
                                                                    Integer.parseInt(txtQuantity.getText()));
            List<Double> numbers = service.generateNumbers(request);
            txtFieldNumbers.setText(formatNumbers(numbers));
            clearInputFields();
        } catch (InvalidInputException | NumberFormatException e) {
            showErrorAlert("Error", e.getMessage());
        }
    }

    @FXML
    void returnMain(ActionEvent event) {
        AppConfig.setScene(Path.PRINCIPAL_CONTROLLER);
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
