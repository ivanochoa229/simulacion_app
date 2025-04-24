package org.simulacion.presentation.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.simulacion.configuration.AppConfig;
import org.simulacion.test.*;
import org.simulacion.utils.Path;

public class SelectTestController {

    @FXML
    void comeBackMenu(ActionEvent event) {
        AppConfig.setScene(Path.PRINCIPAL_CONTROLLER);
    }

    @FXML
    void selectFrequency(ActionEvent event) {
        AppConfig.selectedTest = new FrequencyTest();
        AppConfig.testName = "Frequency Test";
        AppConfig.setScene(Path.TEST_ALPHA_INTERVAL_CONTROLLER);
    }

    @FXML
    void selectKS(ActionEvent event) {
        AppConfig.selectedTest = new KolmogorovSmirnovTest();
        AppConfig.testName = "Kolmogorov Smirnov Test";
        AppConfig.setScene(Path.TEST_ONLY_ALPHA_CONTROLLER);
    }

    @FXML
    void selectMean(ActionEvent event) {
        AppConfig.selectedTest = new MeanTest();
        AppConfig.testName = "Mean Test";
        AppConfig.setScene(Path.TEST_ONLY_ALPHA_CONTROLLER);
    }

    @FXML
    void selectSeries(ActionEvent event) {
        AppConfig.selectedTest = new SeriesTest();
        AppConfig.testName = "Series Test";
        AppConfig.setScene(Path.TEST_ALPHA_INTERVAL_CONTROLLER);
    }

    @FXML
    void selectUpDown(ActionEvent event) {
        AppConfig.selectedTest = new UpDownRunsTest();
        AppConfig.testName = "UpDown Runs Test";
        AppConfig.setScene(Path.TEST_ONLY_ALPHA_CONTROLLER);
    }

}
