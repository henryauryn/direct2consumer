package org.henrylightfoot.d2c.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.model.ProductInList;
import org.henrylightfoot.d2c.model.factory.LogFactory;
import org.henrylightfoot.d2c.model.factory.TaskFactory;
import org.henrylightfoot.d2c.model.object.d2cObject;
import org.henrylightfoot.d2c.view.FullCustomerView;

import java.util.ArrayList;


public class FullCustomerController implements Controller {
    //triage so we can access useful global variables
    private final Triage triage;
    //initializing scene globally so we can return it and style it easily
    private final Scene scene;
    //initializing view and dialogueEngine
    private final FullCustomerView view;
    private DialogueEngine dialogueEngine;
    //JavaFX Selection Models so I can see what table row has been selected when buttons are clicked
    private final TableView.TableViewSelectionModel<d2cObject> logSelectionModel;
    private final TableView.TableViewSelectionModel<d2cObject> taskSelectionModel;
    //object creation
    private TaskFactory taskFactory = new TaskFactory();
    private LogFactory logFactory = new LogFactory();

    public FullCustomerController(Triage triage) {
        this.triage = triage;
        view = new FullCustomerView();
        this.logSelectionModel = view.getLogTableView().getSelectionModel();
        this.taskSelectionModel = view.getTaskTableView().getSelectionModel();
        setControls();
        scene = new Scene(view.getView(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }
    private void setControls() {
        //button and field set-up
        view.getAddLogButton().setOnAction(e -> {openAddLog();});
        view.getAddTaskButton().setOnAction(e -> {openAddTask();});
        view.getMarkTaskButton().setOnAction(e -> {markCompleted();});
        view.getAddPurchaseButtonButton().setOnAction(e -> {openAddPurchase();});

        TextFormatter<String> priceFormatter = new TextFormatter<>(triage.getFilter());
        view.getQuantityField().setTextFormatter(priceFormatter);

        view.getSaveButton().setOnAction(e -> {});
        view.getCancelButton().setOnAction(e -> {closeWindow(view.getCancelButton());});
        view.getBackButton().setOnAction(e -> {triage.showPage("result");});
    }
    private void populateData() {
        //fill each panel with data from Postgres
        //customer profile
        view.getCustomerHeader().setText(triage.getCustomerDisplayed().nameProperty().get());
        view.getCustomerEmail().setText("Contact details: " + triage.getCustomerDisplayed().detailsProperty().get());
        view.getCustomerDOB().setText("Date of birth: " + triage.getCustomerDisplayed().dateProperty().get());
        //purchases
        populatePurchases();
        //tasks
        view.getTaskTableView().getItems().clear();
        view.getTaskTableView().getItems().addAll(triage.getTaskDisplayed());
        //logs
        view.getLogTableView().getItems().clear();
        view.getLogTableView().getItems().addAll(triage.getLogDisplayed());
    }
    //object creation pop-ups
    private void openAddLog() {
        view.getSaveButton().setOnAction(e -> {insertLog();});
        dialogueEngine = new DialogueEngine(triage, new Scene(view.getAddLogView(), 500, 350), "Add Log");
        dialogueEngine.activate();
    }
    private void openAddTask() {
        view.getSaveButton().setOnAction(e -> {insertTask();});
        dialogueEngine = new DialogueEngine(triage, new Scene(view.getAddTaskView(), 500, 350), "Add Task");
        dialogueEngine.activate();
    }
    private void openAddPurchase() {
        view.getProductChoice().getItems().clear();
        view.getProductChoice().getItems().addAll(triage.getDbService().getProductsToPickFrom());
        view.getSaveButton().setOnAction(e -> {insertPurchase();});
        dialogueEngine = new DialogueEngine(triage, new Scene(view.getAddPurchaseView(), 500, 350), "Add Purchase");
        dialogueEngine.activate();
    }
    //close window func to easily link to cancel button for pop-ups
    private void closeWindow(Button theButton) {
        Stage currentStage = (Stage) theButton.getScene().getWindow();
        currentStage.close();
    }
    //refershing data after pop-ups perform insertions on database
    private void refreshTasks() {
        triage.setTaskDisplayed(triage.getCustomerDisplayed().uniqueIDProperty().get());
        view.getTaskTableView().getItems().clear();
        view.getTaskTableView().getItems().addAll(triage.getTaskDisplayed());
    }
    private void refreshLogs() {
        triage.setLogDisplayed(triage.getCustomerDisplayed().uniqueIDProperty().get());
        view.getLogTableView().getItems().clear();
        view.getLogTableView().getItems().addAll(triage.getLogDisplayed());
    }
    private void populatePurchases() {
        view.getProductList().getItems().clear();
        ArrayList<ProductInList> purchases = new ArrayList<>(triage.getDbService().getCustPurchases(triage.getCustomerDisplayed().uniqueIDProperty().get()));
        view.getProductList().getItems().addAll(purchases);

        if (!purchases.isEmpty()) {
            view.getPurchaseHeadline().setText(triage.getDbService().getCustPurchaseHeadline(triage.getCustomerDisplayed().uniqueIDProperty().get()));
        } else {
            view.getPurchaseHeadline().setText("");
        }
    }
    //methods for said insertions, calling global database transfer object reference from Triage:
    private void insertTask() {
        triage.getDbService().insertTask(taskFactory.create(view.getTaskChoice().getValue(), view.getTaskDateContents(), view.getTaskDetailsField().getText(), triage.getCustomerDisplayed().uniqueIDProperty().get()));
        refreshTasks();
        closeWindow(view.getCancelButton());
    }
    private void insertLog() {
        triage.getDbService().insertLog(logFactory.create(view.getLogChoice().getValue(), view.getLogDateContents(), view.getLogDetails(), triage.getCustomerDisplayed().uniqueIDProperty().get()));
        refreshLogs();
        closeWindow(view.getCancelButton());
    }
    private void insertPurchase() {
        triage.getDbService().insertPurchase(triage.getCustomerDisplayed().uniqueIDProperty().get(), view.getProductChoice().getValue(), view.getPurchaseDatePicker().getValue().toString(), Integer.parseInt(view.getQuantityField().getText()));
        populatePurchases();
        closeWindow(view.getCancelButton());
    }
    //changing a specifically selected task to compeleted in database
    private void markCompleted() {
        if (!taskSelectionModel.isEmpty()) {
            triage.getDbService().taskCompleted(taskSelectionModel.getSelectedItem().uniqueIDProperty().get());
            refreshTasks();
        } else {
            dialogueEngine = new DialogueEngine(triage, new Scene(view.getAlert(), 250, 150), "");
            dialogueEngine.activate();
        }
    }
    //feed scene to Triage and PrimaryStage
    public Scene getScene() {
        populateData();
        return scene;
    }
}
