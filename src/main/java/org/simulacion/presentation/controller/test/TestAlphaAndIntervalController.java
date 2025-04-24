package org.simulacion.presentation.controller.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.simulacion.configuration.AppConfig;
import org.simulacion.repository.GlobalRepository;
import org.simulacion.utils.Path;
import org.simulacion.utils.ViewUtils;

import java.util.Arrays;
import java.util.List;

public class TestAlphaAndIntervalController {

    @FXML
    private ComboBox<Integer> jComboAlpha;

    @FXML
    private Label labelTest;

    @FXML
    private TextField txtInterval;

    @FXML
    void backMenu(ActionEvent event) {
        AppConfig.setScene(Path.PRINCIPAL_CONTROLLER);
    }

    @FXML
    void backSelectTest(ActionEvent event) {
        AppConfig.setScene(Path.SELECT_TEST_CONTROLLER);
    }

    @FXML
    void doTest(ActionEvent event) {
        List<Double> sample = GlobalRepository.getSharedNumbers();
        List<Integer> conditions = Arrays.asList(jComboAlpha.getValue(), Integer.parseInt(txtInterval.getText()));
        boolean passed = AppConfig.selectedTest.checkSample(sample, conditions);
        if (passed) {
            labelTest.setText("El conjunto de datos paso la prueba");
            labelTest.setStyle("-fx-text-fill: green;");
        } else {
            labelTest.setText("El conjunto de datos no paso la prueba");
            labelTest.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void initialize() {
        jComboAlpha.getItems().addAll(1, 5, 10);
        jComboAlpha.setValue(1);
        ViewUtils.setupIntegerTextField(txtInterval);
        jComboAlpha.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                System.out.println("Valor seleccionado: " + newVal);
                ViewUtils.resetLabel(labelTest);
            }
        });
        txtInterval.textProperty().addListener((obs, oldVal, newVal) -> {
            ViewUtils.resetLabel(labelTest);
        });
    }
}


