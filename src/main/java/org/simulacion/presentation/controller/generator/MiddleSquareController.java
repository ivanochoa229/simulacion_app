package org.simulacion.presentation.controller.generator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.simulacion.configuration.AppConfig;
import org.simulacion.generator.MiddleSquare;
import org.simulacion.presentation.dto.MiddleSquareRequest;
import org.simulacion.repository.GlobalRepository;
import org.simulacion.service.MiddleSquareService;
import org.simulacion.utils.InputCleaner;
import org.simulacion.utils.InputValidator;
import org.simulacion.utils.Path;
import org.simulacion.utils.ViewUtils;

import java.util.List;

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
        if (InputValidator.areFieldsEmpty(txtSead, txtDigits, txtQuantity)) {
            ViewUtils.showErrorAlert("Error", "Complete los campos");
            return;
        }
        try {
            MiddleSquareRequest request = new MiddleSquareRequest(  Integer.parseInt(txtSead.getText()),
                                                                    Integer.parseInt(txtDigits.getText()),
                                                                    Integer.parseInt(txtQuantity.getText()));
            List<Double> numbers = service.generateNumbers(request);
            GlobalRepository.setSharedNumbers(numbers);
            txtFieldNumbers.setText(ViewUtils.formatNumbers(numbers));
            InputCleaner.clearInputFields(txtSead, txtDigits, txtQuantity);
        } catch (Exception e) {
            ViewUtils.showErrorAlert("Error", e.getMessage());
        }
    }

    @FXML
    void returnMain(ActionEvent event) {
        AppConfig.setScene(Path.PRINCIPAL_CONTROLLER);
    }

    @FXML
    void selectTest(ActionEvent event) {
        AppConfig.setScene(Path.SELECT_TEST_CONTROLLER);
    }

    @FXML
    public void initialize() {
        ViewUtils.setupIntegerTextField(txtSead);
        ViewUtils.setupIntegerTextField(txtDigits);
        ViewUtils.setupIntegerTextField(txtQuantity);

        InputCleaner.clearTextAreaOnFocus(txtFieldNumbers, txtSead, txtDigits, txtQuantity);
    }

}
