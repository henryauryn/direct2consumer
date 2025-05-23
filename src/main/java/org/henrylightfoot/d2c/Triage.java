package org.henrylightfoot.d2c;

import javafx.stage.Stage;
import org.henrylightfoot.d2c.controller.Controller;
import org.henrylightfoot.d2c.controller.WelcController;

import java.util.Map;
import org.henrylightfoot.d2c.controller.*;


public class Triage {
    private static Triage instance;
    private final Stage stage;

    private final Map<String, Controller> pages;
    private Triage(Stage stage) {
        this.stage = stage;
        stage.setTitle("direct2Consumer");
        pages = Map.of("welcome", new WelcController(this), "reports", new ReportsController(this), "tasks", new TasksController(this), "products", new ProductsController(this), "search", new SearchController(this));
    }
    public static Triage getInstance(Stage stage) {
        if (instance == null) {
            instance = new Triage(stage);
        }
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void showPage(String page) {
        stage.setScene(pages.get(page).getScene());
        stage.setMaximized(false);
        stage.show();
    }


}
