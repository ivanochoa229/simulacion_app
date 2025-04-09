package org.simulacion.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.simulacion.controller.PrincipalController;

import utils.Path;

import java.io.IOException;


public class Main extends Application {

    public static Main main;
    private Stage stageWindow;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        main = this;
        stageWindow = stage;
        setScene(Path.PRINCIPAL_CONTROLLER);
    }


    public void setScene(String path){
        FXMLLoader loader = new FXMLLoader( getClass().getResource(path));
        try{
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            stageWindow.setScene(scene);
            stageWindow.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}