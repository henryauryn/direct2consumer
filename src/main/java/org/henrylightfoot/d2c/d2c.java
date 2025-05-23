package org.henrylightfoot.d2c;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class d2c extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Triage.getInstance(stage).showPage("welcome");
    }

    public static void main(String[] args) {
        launch(args);
    }
}