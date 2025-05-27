package org.henrylightfoot.d2c.controller;
import javafx.scene.Scene;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.view.ResultsView;
import org.henrylightfoot.d2c.view.TasksView;


public class ResultsController implements Controller {
    private final Triage triage;
    private final Scene scene;
    private final ResultsView view;

    public ResultsController(Triage triage) {
        this.triage = triage;
        view = new ResultsView();
        scene = new Scene(view.getView(), 450, 300);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        setButtons();
    }

    private void setButtons() {
        view.getBackButton().setOnAction(event -> {triage.showPage("search");});
    }
    private void populateTable() {
        view.getTableView().getItems().clear();
        view.getTableView().getItems().addAll(triage.getSearchResults());
    }


    public Scene getScene() {
        populateTable();
        return scene;
    }
}