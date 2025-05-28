package org.henrylightfoot.d2c.controller;

import javafx.application.Platform;
import javafx.scene.Scene;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.view.WelcView;

public class WelcController implements Controller {
    private final Scene scene;
    private final Triage triage;

    WelcView view = new WelcView();
    //application traversal button setting-up
    public WelcController(Triage triage) {
        this.triage = triage;
        view.getViewCustomersButton().setOnAction(event -> {triage.showPage("search");});
        view.getViewOutstandingTasksButton().setOnAction(event -> {triage.showPage("tasks");});
        view.getExitButton().setOnAction(event -> Platform.exit());
        view.getReportsButton().setOnAction(event -> {triage.showPage("reports");});
        view.getEditProductDirectoryButton().setOnAction(event -> {triage.showPage("products");});
        scene = new Scene(view.getView(), 300, 400);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

    }



    public Scene getScene() {
        return scene;
    }
}
