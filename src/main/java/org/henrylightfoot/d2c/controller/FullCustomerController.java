package org.henrylightfoot.d2c.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.model.object.d2cObject;
import org.henrylightfoot.d2c.view.FullCustomerView;
import org.henrylightfoot.d2c.view.ReportsView;
import org.henrylightfoot.d2c.view.TasksView;


public class FullCustomerController implements Controller {
    private final Triage triage;
    private final Scene scene;
    private final FullCustomerView view;



    public FullCustomerController(Triage triage) {
        this.triage = triage;
        view = new FullCustomerView();
        setButtons();
        scene = new Scene(view.getView(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }
    private void setButtons() {
        view.getBackButton().setOnAction(e -> {triage.showPage("result");});
    }
    private void populateData() {
        view.getCustomerHeader().setText(triage.getCustomerDisplayed().nameProperty().get());
        view.getCustomerEmail().setText("Contact details: " + triage.getCustomerDisplayed().detailsProperty().get());
        view.getCustomerDOB().setText("Date of birth: " + triage.getCustomerDisplayed().dateProperty().get());

        view.getTaskTableView().getItems().clear();
        view.getTaskTableView().getItems().addAll(triage.getTaskDisplayed());

        view.getLogTableView().getItems().clear();
        view.getLogTableView().getItems().addAll(triage.getLogDisplayed());
    }

    public Scene getScene() {

        populateData();
        return scene;
    }
}
