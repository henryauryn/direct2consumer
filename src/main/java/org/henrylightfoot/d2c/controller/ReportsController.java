package org.henrylightfoot.d2c.controller;
import javafx.scene.Scene;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.view.ReportsView;
import org.henrylightfoot.d2c.view.TasksView;


public class ReportsController implements Controller {
    private final Triage triage;
    private final Scene scene;
    private final ReportsView view;

    public ReportsController(Triage triage) {
        this.triage = triage;
        view = new ReportsView();
        view.getBackButton().setOnAction(event -> {triage.showPage("welcome");});
        scene = new Scene(view.getView(), 250, 250);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

    }

    public Scene getScene() {
        return scene;
    }
}