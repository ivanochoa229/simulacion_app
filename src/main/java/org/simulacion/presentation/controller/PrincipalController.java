package org.simulacion.presentation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.simulacion.configuration.AppConfig;
import org.simulacion.utils.Path;

public class PrincipalController {

    @FXML
    void selectAdd(ActionEvent event) {
        AppConfig.setScene(Path.ADDITIONAL_CONGRUENTIAL_CONTROLLER);
    }

    @FXML
    void selectLehmer(ActionEvent event) {
        AppConfig.setScene(Path.LEHMER_CONTROLLER);
    }

    @FXML
    void selectMiddleSquare(ActionEvent event) {
        AppConfig.setScene(Path.MIDDLE_SQUARE_CONTROLLER);
    }

    @FXML
    void selectMix(ActionEvent event) {

    }

    @FXML
    void selectMult(ActionEvent event) {

    }
}
