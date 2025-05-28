package org.henrylightfoot.d2c.controller;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.henrylightfoot.d2c.Triage;

public class  DialogueEngine {
    //Dialogue Engine simulates a Pop-up window with complete freedom, by creating a brand new Stage, as JavaFX Alerts are annoying
    private final Stage dialogueStage;
    public DialogueEngine (Triage triage, Scene scene, String title) {
        dialogueStage = new Stage();
        //making sure dialogueEngine steals the spotlight:
        dialogueStage.initModality(Modality.WINDOW_MODAL);
        dialogueStage.initOwner(triage.getStage());
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        dialogueStage.setScene(scene);
        dialogueStage.setTitle(title);
    }
    public void activate() {
        dialogueStage.showAndWait();
    }
    public Stage getDialogueStage() {
        return dialogueStage;
    }
    //close method here so employers of this class can easily link a button to window-closing functionality
    public void close() {
        dialogueStage.close();
    }
}

