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
        //pass the ref to JavaFX's PrimaryStage into the core controller singleton class 'Triage', and display the app landing page
        Triage.getInstance(stage).showPage("welcome");
    }

    public static void main(String[] args) {
        //after various javaFX launch processes, the above start method will be called
        launch(args);
    }
}