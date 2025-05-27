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
        view.getBackButton().setOnAction(event -> {triage.showPage("welcome");});
        scene = new Scene(view.getView(), 250, 250);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }
}