package org.simulacion.presentation.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.simulacion.configuration.AppConfig;
import org.simulacion.utils.Path;

public class SelectTestController {

    @FXML
    void comeBackMenu(ActionEvent event) {
        AppConfig.setScene(Path.PRINCIPAL_CONTROLLER);
    }

    @FXML
    void selectFrequency(ActionEvent event) {
        AppConfig.setScene(Path.TEST_ALPHA_INTERVAL_CONTROLLER);
    }

    @FXML
    void selectKS(ActionEvent event) {
        AppConfig.setScene(Path.TEST_ONLY_ALPHA_CONTROLLER);
    }

    @FXML
    void selectMean(ActionEvent event) {
        AppConfig.setScene(Path.TEST_ONLY_ALPHA_CONTROLLER);
    }

    @FXML
    void selectSeries(ActionEvent event) {
        AppConfig.setScene(Path.TEST_ALPHA_INTERVAL_CONTROLLER);
    }

    @FXML
    void selectUpDown(ActionEvent event) {
        AppConfig.setScene(Path.TEST_ONLY_ALPHA_CONTROLLER);
    }

}
