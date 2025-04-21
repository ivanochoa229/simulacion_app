package org.simulacion.presentation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.simulacion.configuration.AppConfig;
import org.simulacion.generator.MixedCongruential;
import org.simulacion.presentation.dto.MixedCongruentialRequest;
import org.simulacion.service.MixedCongruentialService;
import org.simulacion.utils.InputCleaner;
import org.simulacion.utils.InputValidator;
import org.simulacion.utils.Path;
import org.simulacion.utils.ViewUtils;

import java.util.List;

public class MixedCongruentialController {

    private final MixedCongruentialService service;

    public MixedCongruentialController() {
        this.service = new MixedCongruentialService(new MixedCongruential());
    }

    @FXML
    private TextField txtA;

    @FXML
    private TextField txtC;

    @FXML
    private TextArea txtFieldNumbers;

    @FXML
    private TextField txtM;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtSead;

    @FXML
    void generateNumbers(ActionEvent event) {
        if (InputValidator.areFieldsEmpty(txtSead, txtA, txtC, txtM, txtQuantity)) {
            ViewUtils.showErrorAlert("Error", "Complete los campos");
            return;
        }
        try {
            MixedCongruentialRequest request = new MixedCongruentialRequest(  Integer.parseInt(txtA.getText()),
                    Integer.parseInt(txtC.getText()),
                    Integer.parseInt(txtM.getText()),
                    Integer.parseInt(txtSead.getText()),
                    Integer.parseInt(txtQuantity.getText()));
            List<Double> numbers = service.generateNumbers(request);
            txtFieldNumbers.setText(ViewUtils.formatNumbers(numbers));
            InputCleaner.clearInputFields(txtSead, txtA, txtC, txtM, txtQuantity);
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

    }

}
