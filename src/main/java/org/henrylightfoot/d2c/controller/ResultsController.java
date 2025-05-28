package org.henrylightfoot.d2c.controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.model.object.d2cObject;
import org.henrylightfoot.d2c.view.ResultsView;
import org.henrylightfoot.d2c.view.TasksView;


public class ResultsController implements Controller {
    private final Triage triage;
    private final Scene scene;
    private final ResultsView view;
    private final TableView.TableViewSelectionModel<d2cObject> selectionModel;
    private DialogueEngine dialogueEngine;

    public ResultsController(Triage triage) {
        this.triage = triage;
        view = new ResultsView();
        this.selectionModel = view.getTableView().getSelectionModel();
        scene = new Scene(view.getView(), 900, 450);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        setButtons();
    }

    private void setButtons() {
        view.getBackButton().setOnAction(event -> {triage.showPage("search");});
        view.getViewButton().setOnAction(event -> {viewCustomer();});
        view.getOkButton().setOnAction(event -> {closeWindow(view.getOkButton());});
    }
    private void populateTable() {
        view.getTableView().getItems().clear();
        view.getTableView().getItems().addAll(triage.getSearchResults());
    }
    private void viewCustomer() {
        if (!selectionModel.isEmpty()) {
            triage.setCustomerDisplayed(triage.getDbService().getCustomer(selectionModel.getSelectedItem().uniqueIDProperty().get()));
            triage.showPage("customer");
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
        populateTable();
        return scene;
    }
}