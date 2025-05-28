package org.henrylightfoot.d2c.controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.model.object.d2cObject;
import org.henrylightfoot.d2c.view.TasksView;


public class TasksController implements Controller {
    private final Triage triage;
    private final Scene scene;
    private final TasksView view;
    private final TableView.TableViewSelectionModel<d2cObject> selectionModel;
    private DialogueEngine dialogueEngine;

    public TasksController(Triage triage) {
        this.triage = triage;
        view = new TasksView();
        view.getBackButton().setOnAction(event -> {triage.showPage("welcome");});
        populateTable();
        selectionModel = view.getTableView().getSelectionModel();
        view.getCompletedButton().setOnAction(event -> {markCompleted();});
        view.getOkButton().setOnAction(event -> {closeWindow(view.getOkButton());});
        scene = new Scene(view.getView(), 900, 450);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }

    private void populateTable() {
        view.getTableView().getItems().clear();
        view.getTableView().getItems().addAll(triage.getDbService().getAllTaskTableData());
    }
    private void markCompleted() {
        if (!selectionModel.isEmpty()) {
            triage.getDbService().taskCompleted(selectionModel.getSelectedItem().uniqueIDProperty().get());
            populateTable();
        } else {
            dialogueEngine = new DialogueEngine(triage, new Scene(view.getAlert(), 250, 150), "");
            dialogueEngine.activate();
        }
    }

    private void closeWindow(Button theButton) {
        Stage currentStage = (Stage) theButton.getScene().getWindow();
        currentStage.close();
    }

    public Scene getScene() {
        return scene;
    }
}
