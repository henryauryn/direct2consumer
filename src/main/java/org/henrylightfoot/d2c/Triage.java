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
    private static Triage instance;
    private final Stage stage;
    private final dbBroker databaseService;
    private ArrayList<d2cObject> searchResults = new ArrayList<d2cObject>();
    private d2cObject customerDisplayed;
    private ArrayList<d2cObject> taskDisplayed = new ArrayList<>();
    private ArrayList<d2cObject> logDisplayed = new ArrayList<>();
    private UnaryOperator<TextFormatter.Change> filter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("-?\\d*(\\.\\d*)?")) {
            return change;
        }
        return null;
    };
    private final DAO accessService = new DAO();
    private final ReportGenerator reportGenerator;
    private TaskNotifier taskNotifier;


    private final Map<String, Controller> pages;
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

    public void showPage(String page) {
        stage.setScene(pages.get(page).getScene());
        stage.setMaximized(false);
        stage.show();
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
