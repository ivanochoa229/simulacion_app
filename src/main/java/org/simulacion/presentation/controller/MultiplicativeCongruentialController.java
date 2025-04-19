package org.simulacion.presentation.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.simulacion.configuration.AppConfig;

import org.simulacion.generator.MultiplicativeCongruential;
import org.simulacion.presentation.dto.MultiplicativeCongruentialRequest;
import org.simulacion.service.MultiplicativeCongruentialService;
import org.simulacion.utils.InputCleaner;
import org.simulacion.utils.InputValidator;
import org.simulacion.utils.Path;
import org.simulacion.utils.ViewUtils;

import java.util.List;

public class MultiplicativeCongruentialController {

    private final MultiplicativeCongruentialService service;

    public MultiplicativeCongruentialController() {
        this.service = new MultiplicativeCongruentialService(new MultiplicativeCongruential());
    }

    @FXML
    private TextField txtA;

    @FXML
    private TextArea txtFieldNumbers;

    @FXML
    private TextField txtM;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtSeed;

    @FXML
    void generateNumbers(ActionEvent event) {
        if (InputValidator.areFieldsEmpty(txtSeed, txtA, txtM, txtQuantity)) {
            ViewUtils.showErrorAlert("Error", "Complete los campos");
            return;
        }
        try {
            MultiplicativeCongruentialRequest request = new MultiplicativeCongruentialRequest(  Integer.parseInt(txtA.getText()),
                    Integer.parseInt(txtM.getText()),
                    Integer.parseInt(txtSeed.getText()),
                    Integer.parseInt(txtQuantity.getText()));
            List<Double> numbers = service.generateNumbers(request);
            txtFieldNumbers.setText(ViewUtils.formatNumbers(numbers));
            InputCleaner.clearInputFields(txtSeed, txtA,  txtM, txtQuantity);
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
