package org.henrylightfoot.d2c.controller;
import javafx.scene.Scene;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.view.ProductsView;
import org.henrylightfoot.d2c.view.TasksView;


public class ProductsController implements Controller {
    private final Triage triage;
    private final Scene scene;
    private final ProductsView view;

    public ProductsController(Triage triage) {
        this.triage = triage;
        view = new ProductsView();
        scene = new Scene(view.getView(), 250, 250);
    }

    public Scene getScene() {
        return scene;
    }
}