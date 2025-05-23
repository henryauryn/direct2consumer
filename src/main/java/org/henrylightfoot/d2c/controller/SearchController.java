package org.henrylightfoot.d2c.controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.view.SearchView;


public class SearchController implements Controller {
    private final Triage triage;
    private final Scene scene;
    private final SearchView view;
    private DialogueEngine dialogueEngine;

    public SearchController(Triage triage) {
        this.triage = triage;
        view = new SearchView();
        setButtons();
        scene = new Scene(view.getView(), 500, 270);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }

    private void setButtons() {
        view.getBackButton().setOnAction(e -> {triage.showPage("welcome");});
        view.getSearchButton().setOnAction(e -> {triage.showPage("customer");});
        view.getAddCustomerButton().setOnAction(e -> {openAdd();});
    }
    private void openAdd() {
        view.getSaveButton().setOnAction(e -> {closeWindow(view.getSaveButton());});
        view.getCancelButton().setOnAction(e -> {closeWindow(view.getCancelButton());});
        dialogueEngine = new DialogueEngine(triage, new Scene(view.getAddView(), 500, 350), "Add Customer");
        dialogueEngine.activate();
    }
    private void closeWindow(Button theButton) {
        Stage currentStage = (Stage) theButton.getScene().getWindow();
        currentStage.close();
    }

    public Scene getScene() {
        return scene;
    }
}