package org.henrylightfoot.d2c.controller;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.henrylightfoot.d2c.Triage;

public class  DialogueEngine {
    private final Stage dialogueStage;

    public DialogueEngine (Triage triage, Scene scene, String title) {
        dialogueStage = new Stage();
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

    public void close() {
        dialogueStage.close();
    }
}

