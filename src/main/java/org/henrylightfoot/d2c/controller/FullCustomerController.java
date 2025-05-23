package org.henrylightfoot.d2c.controller;

import javafx.scene.Scene;
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
        scene = new Scene(view.getView(), 250, 250);
    }

    public Scene getScene() {
        return scene;
    }
}
