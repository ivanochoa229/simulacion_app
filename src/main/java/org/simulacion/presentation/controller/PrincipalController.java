package org.simulacion.presentation.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.simulacion.configuration.AppConfig;
import utils.Path;

public class PrincipalController {

    @FXML
    void selectAdd(ActionEvent event) {

    }

    @FXML
    void selectLehmer(ActionEvent event) {

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
