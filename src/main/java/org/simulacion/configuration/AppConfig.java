package org.simulacion.configuration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.simulacion.test.TestInterface;

import java.io.IOException;
import java.util.List;

public class AppConfig {
    private static Stage primaryStage;
    public static TestInterface selectedTest;
    public static String testName;

    public static void initialize(Stage stage) {
        primaryStage = stage;
    }

    public static void setScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(AppConfig.class.getResource(fxmlPath));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar la escena: " + fxmlPath);
            e.printStackTrace();
        }
    }

}
