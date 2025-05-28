package org.henrylightfoot.d2c;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.henrylightfoot.d2c.controller.Controller;
import org.henrylightfoot.d2c.controller.WelcController;

import java.util.ArrayList;
import java.util.Map;
import org.henrylightfoot.d2c.controller.*;
import org.henrylightfoot.d2c.model.dbBroker;
import org.henrylightfoot.d2c.model.object.d2cObject;


public class Triage {
    private static Triage instance;
    private final Stage stage;
    private final dbBroker databaseService;
    private ArrayList<d2cObject> searchResults = new ArrayList<d2cObject>();
    private d2cObject customerDisplayed;
    private d2cObject taskDisplayed;
    private d2cObject logDisplayed;

    private final Map<String, Controller> pages;
    private Triage(Stage stage) {
        this.stage = stage;
        this.databaseService = new dbBroker();
        stage.setTitle("direct2Consumer");
        pages = Map.of("welcome", new WelcController(this), "reports", new ReportsController(this), "tasks", new TasksController(this), "products", new ProductsController(this), "search", new SearchController(this), "customer", new FullCustomerController(this), "result", new ResultsController(this));
    }
    public static Triage getInstance(Stage stage) {
        if (instance == null) {
            instance = new Triage(stage);
        }
        return instance;
    }
    public dbBroker getDbService() {
        return databaseService;
    }
    public void saveSearchResults(ArrayList<d2cObject> results) {
        this.searchResults = results;
    }
    public ArrayList<d2cObject> getSearchResults() {
        return searchResults;
    }

    public void setCustomerDisplayed(d2cObject customerDisplayed) {
        this.customerDisplayed = customerDisplayed;
    }
    public void setTaskDisplayed(d2cObject taskDisplayed) {
        this.taskDisplayed = taskDisplayed;
    }
    public void setLogDisplayed(d2cObject logDisplayed) {
        this.logDisplayed = logDisplayed;
    }
    public d2cObject getCustomerDisplayed() {
        return customerDisplayed;
    }
    public d2cObject getTaskDisplayed() {
        return taskDisplayed;
    }
    public d2cObject getLogDisplayed() {
        return logDisplayed;
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
