package org.henrylightfoot.d2c.controller;
import javafx.scene.Scene;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.view.SearchView;


public class SearchController implements Controller {
    private final Triage triage;
    private final Scene scene;
    private final SearchView view;

    public SearchController(Triage triage) {
        this.triage = triage;
        view = new SearchView();
        scene = new Scene(view.getView(), 250, 250);
    }

    public Scene getScene() {
        return scene;
    }
}