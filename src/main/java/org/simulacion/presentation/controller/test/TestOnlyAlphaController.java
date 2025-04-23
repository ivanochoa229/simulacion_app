package org.simulacion.presentation.controller.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.simulacion.configuration.AppConfig;
import org.simulacion.utils.Path;

public class TestOnlyAlphaController {


    @FXML
    private ComboBox<Integer> jComboAlpha;

    @FXML
    private Label labelTest;

    @FXML
    void doTest(ActionEvent event) {
        Integer selectedValue = jComboAlpha.getValue();
    }

    @FXML
    void backMenu(ActionEvent event) {
        AppConfig.setScene(Path.PRINCIPAL_CONTROLLER);
    }

    @FXML
    void backSelectTest(ActionEvent event) {
        AppConfig.setScene(Path.SELECT_TEST_CONTROLLER);
    }

    @FXML
    public void initialize() {
        jComboAlpha.getItems().addAll(1, 5, 10);
        jComboAlpha.setValue(1);

        jComboAlpha.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                System.out.println("Valor seleccionado: " + newVal);
            }
        });
    }
}
