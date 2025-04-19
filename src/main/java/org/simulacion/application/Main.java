package org.simulacion.application;

import javafx.application.Application;
import javafx.stage.Stage;

import org.simulacion.configuration.AppConfig;
import org.simulacion.utils.Path;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        AppConfig.initialize(primaryStage);
        AppConfig.setScene(Path.PRINCIPAL_CONTROLLER);
    }

    public static void main(String[] args) {
        launch(args);
    }
}