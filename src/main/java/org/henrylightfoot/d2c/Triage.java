package org.henrylightfoot.d2c;

import javafx.collections.ObservableList;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import org.henrylightfoot.d2c.controller.Controller;
import org.henrylightfoot.d2c.controller.WelcController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.henrylightfoot.d2c.controller.*;
import org.henrylightfoot.d2c.model.DAO;
import org.henrylightfoot.d2c.model.dbBroker;
import org.henrylightfoot.d2c.model.object.Task;
import org.henrylightfoot.d2c.model.object.d2cObject;
import org.henrylightfoot.d2c.observer.ConsoleNotifier;
import org.henrylightfoot.d2c.observer.FXTaskDueObserver;
import org.henrylightfoot.d2c.observer.TaskNotifier;

import static jdk.xml.internal.SecuritySupport.getResource;


public class Triage {
    //singleton instance
    private static Triage instance;
    //storing JavaFX PrimaryStage and a HashMap to store all different page views
    private final Stage stage;
    private final Map<String, Controller> pages;
    //initiliazing DTO
    private final dbBroker databaseService;
    //storage for customer search results so other pages can access it
    private ArrayList<d2cObject> searchResults = new ArrayList<d2cObject>();
    //storage for a customer and their tasks/logs so other pages can access it
    private d2cObject customerDisplayed;
    private ArrayList<d2cObject> taskDisplayed = new ArrayList<>();
    private ArrayList<d2cObject> logDisplayed = new ArrayList<>();
    //storing a TextFormatting object here so multiple views can use it. It stops the user from entering non-numbers in certain TextFields
    private UnaryOperator<TextFormatter.Change> filter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("-?\\d*(\\.\\d*)?")) {
            return change;
        }
        return null;
    };
    //Initializing DAO here so both DTO (dbBroker) and ReportGenerator can use it
    private final DAO accessService = new DAO();
    // Initializing report generator object
    private final ReportGenerator reportGenerator;
    //part of observer pattern:
    private TaskNotifier taskNotifier;

    //initializing necessary global variables and pages, as well as implementing the observer notifiction system for any customer tasks due on the same day
    private Triage(Stage stage) {
        this.stage = stage;
        this.reportGenerator = new ReportGenerator(this);
        this.databaseService = new dbBroker(this);
        stage.setTitle("direct2Consumer");
        pages = Map.of("welcome", new WelcController(this), "reports", new ReportsController(this), "tasks", new TasksController(this), "products", new ProductsController(this), "search", new SearchController(this), "customer", new FullCustomerController(this), "result", new ResultsController(this));

        taskNotifier = new TaskNotifier();
        taskNotifier.addObserver(new FXTaskDueObserver());
        taskNotifier.addObserver(new ConsoleNotifier());
        checkTasksDueToday();
    }

    //getters
    public static Triage getInstance(Stage stage) {
        if (instance == null) {
            instance = new Triage(stage);
        }
        return instance;
    }
    public dbBroker getDbService() {
        return databaseService;
    }
    public ArrayList<d2cObject> getSearchResults() {
        return searchResults;
    }
    public d2cObject getCustomerDisplayed() {
        return customerDisplayed;
    }
    public ArrayList<d2cObject> getTaskDisplayed() {
        return taskDisplayed;
    }
    public ArrayList<d2cObject> getLogDisplayed() {
        return logDisplayed;
    }
    public Stage getStage() {
        return stage;
    }
    public UnaryOperator<TextFormatter.Change> getFilter() {
        return filter;
    }
    public DAO getAccessService() {
        return accessService;
    }
    public ReportGenerator getReportGenerator() {
        return reportGenerator;
    }
    //setters
    public void saveSearchResults(ArrayList<d2cObject> results) {
        this.searchResults = results;
    }
    public void setCustomerDisplayed(int custID) {
        this.customerDisplayed = this.getDbService().getCustomer(custID);
    }
    public void setTaskDisplayed(int custID) {
        this.taskDisplayed.clear();
        this.taskDisplayed.addAll(this.getDbService().getAllTasks(custID));
    }
    public void setLogDisplayed(int custID) {
        this.logDisplayed.clear();
        this.logDisplayed.addAll(this.getDbService().getAllLogs(custID));
    }
    //change view function
    public void showPage(String page) {
        stage.setScene(pages.get(page).getScene());
        stage.setMaximized(false);
        stage.show();
    }
    //notification implementation
    private void checkTasksDueToday() {
        List<d2cObject> allTasks = databaseService.getAllTaskTableData();
        LocalDate today = LocalDate.now();

        // Group tasks by customer ID if you expect multiple per customer
        Map<Integer, List<d2cObject>> dueMap = allTasks.stream()
                .filter(task -> LocalDate.parse(task.dateProperty().get()).isEqual(today))
                .collect(Collectors.groupingBy(d2cObject::getCustID));

        for (Map.Entry<Integer, List<d2cObject>> entry : dueMap.entrySet()) {
            taskNotifier.notifyObservers(entry.getKey(), entry.getValue());
        }
    }
}
