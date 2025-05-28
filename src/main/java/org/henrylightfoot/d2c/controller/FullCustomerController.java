package org.henrylightfoot.d2c.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.henrylightfoot.d2c.Triage;
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
        scene = new Scene(view.getView(), 900, 650);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }
    private void setButtons() {
        view.getBackButton().setOnAction(e -> {triage.showPage("result");});
    }

    public Scene getScene() {
        view.getCustomerHeader().setText(triage.getCustomerDisplayed().nameProperty().get());
        return scene;
    }
}
