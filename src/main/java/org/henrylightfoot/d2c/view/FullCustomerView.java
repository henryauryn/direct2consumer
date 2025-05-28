package org.henrylightfoot.d2c.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import org.henrylightfoot.d2c.model.object.Log;
import org.henrylightfoot.d2c.model.object.Task;
import org.henrylightfoot.d2c.model.object.d2cObject;

public class FullCustomerView {
    private Button backButton = new Button("← Back");
    private Button addTaskButton = new Button("Add Task");
    private Button markTaskButton = new Button("Mark Completed");
    private Button addLogButton = new Button("Add Log");
    private Button addPurchaseButton = new Button("Add Purchase");
    private Label customerHeader = new Label("Customer Profile");
    private Label customerEmail = new Label("Email: john.doe@example.com");
    private Label customerDOB = new Label("Date of Birth: 1990-01-01");
    private TableView<d2cObject> taskTableView = new TableView();
    private TableView<d2cObject> logTableView = new TableView();
    private ListView<String> productList = new ListView<>();

    public Parent getView() {
        backButton.getStyleClass().add("button");
        customerHeader.getStyleClass().add("page-title");

        HBox headerRow = new HBox(20, backButton, customerHeader);
        headerRow.setPadding(new Insets(20, 20, 10, 20));
        headerRow.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col1.setPercentWidth(50);
        col2.setPercentWidth(50);
        col1.setHgrow(Priority.ALWAYS);
        col2.setHgrow(Priority.ALWAYS);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row1.setPercentHeight(50);
        row2.setPercentHeight(50);
        row1.setVgrow(Priority.ALWAYS);
        row2.setVgrow(Priority.ALWAYS);

        grid.getColumnConstraints().addAll(col1, col2);
        grid.getRowConstraints().addAll(row1, row2);

        //task table set-up
        TableColumn<d2cObject, String> taskTypeCol = new TableColumn<>("Type");
        taskTypeCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<d2cObject, String> taskDateCol = new TableColumn<>("Deadline");
        taskDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<d2cObject, String> taskDetailsCol = new TableColumn<>("Description");
        taskDetailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        TableColumn<d2cObject, Boolean> taskDoneCol = new TableColumn<>("Done?");
        taskDoneCol.setCellValueFactory(new PropertyValueFactory<>("done"));
        taskTableView.getColumns().addAll(taskTypeCol, taskDateCol, taskDetailsCol, taskDoneCol);
        taskTableView.setPlaceholder(new Label("No tasks found"));

        //log table set-up
        TableColumn<d2cObject, String> logTypeCol = new TableColumn<>("Type");
        logTypeCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<d2cObject, String> logDetailsCol = new TableColumn<>("Details");
        logDetailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        TableColumn<d2cObject, String> logDateCol = new TableColumn<>("Date");
        logDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        logTableView.getColumns().addAll(logTypeCol, logDetailsCol, logDateCol);
        logTableView.setPlaceholder(new Label("No logs found"));

        // Profile panel
        VBox custLabels = new VBox(10, customerEmail, customerDOB);
        customerEmail.getStyleClass().add("choiceLabel");
        customerDOB.getStyleClass().add("choiceLabel");
        Label custTitleLabel = new Label("Profile");
        custTitleLabel.getStyleClass().add("panel-title");
        VBox.setVgrow(custLabels, Priority.ALWAYS);
        custLabels.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox profilePanel = new VBox(10, custTitleLabel, custLabels);
        profilePanel.getStyleClass().add("panel");

        //tasks panel
        Label tasksLabel = new Label("Tasks");
        tasksLabel.getStyleClass().add("panel-title");
        addTaskButton.getStyleClass().add("button");
        markTaskButton.getStyleClass().add("button");
        HBox titleRow = new HBox();
        titleRow.setAlignment(Pos.CENTER_LEFT);
        titleRow.setPadding(new Insets(0, 0, 10, 0));
        titleRow.setSpacing(10);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        titleRow.getChildren().addAll(tasksLabel, spacer, markTaskButton, addTaskButton);
        VBox taskPanelContent = new VBox(10, titleRow, taskTableView);
        taskPanelContent.setPadding(new Insets(10));
        taskPanelContent.setFillWidth(true);
        VBox tasksPanel = new VBox(10, taskPanelContent);
        tasksPanel.getStyleClass().add("panel");
        VBox.setVgrow(taskTableView, Priority.ALWAYS);
        taskTableView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //logs panel
        Label logsLabel = new Label("Logs");
        logsLabel.getStyleClass().add("panel-title");
        addLogButton.getStyleClass().add("button");
        HBox lTitleRow = new HBox();
        lTitleRow.setAlignment(Pos.CENTER_LEFT);
        lTitleRow.setPadding(new Insets(0, 0, 10, 0));
        lTitleRow.setSpacing(10);
        Region lSpacer = new Region();
        HBox.setHgrow(lSpacer, Priority.ALWAYS);
        lTitleRow.getChildren().addAll(logsLabel, lSpacer, addLogButton);
        VBox logPanelContent = new VBox(10, lTitleRow, logTableView);
        logPanelContent.setPadding(new Insets(10));
        logPanelContent.setFillWidth(true);
        VBox logsPanel = new VBox(10, logPanelContent);
        logsPanel.getStyleClass().add("panel");
        VBox.setVgrow(logTableView, Priority.ALWAYS);
        logTableView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //purchases panel
        Label purchasesLabel = new Label("Purchases");
        purchasesLabel.getStyleClass().add("panel-title");
        addPurchaseButton.getStyleClass().add("button");
        HBox pTitleRow = new HBox();
        pTitleRow.setAlignment(Pos.CENTER_LEFT);
        pTitleRow.setPadding(new Insets(0, 0, 10, 0));
        pTitleRow.setSpacing(10);
        Region pSpacer = new Region();
        HBox.setHgrow(pSpacer, Priority.ALWAYS);
        pTitleRow.getChildren().addAll(purchasesLabel, pSpacer, addPurchaseButton);
        VBox purchasePanelContent = new VBox(10, pTitleRow, productList);
        purchasePanelContent.setPadding(new Insets(10));
        purchasePanelContent.setFillWidth(true);
        VBox purchasesPanel = new VBox(10, purchasePanelContent);
        purchasesPanel.getStyleClass().add("panel");
        VBox.setVgrow(productList, Priority.ALWAYS);
        productList.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        grid.add(profilePanel, 0, 0);
        grid.add(tasksPanel, 1, 0);
        grid.add(logsPanel, 0, 1);
        grid.add(purchasesPanel, 1, 1);

        VBox root = new VBox(20, headerRow, grid);
        root.getStyleClass().add("main-container");
        return root;
    }



    public Label getCustomerHeader() {
        return customerHeader;
    }
    public Label getCustomerEmail() {
        return customerEmail;
    }
    public Label getCustomerDOB() {
        return customerDOB;
    }
    public TableView<d2cObject> getTaskTableView() {
        return taskTableView;
    }
    public TableView<d2cObject> getLogTableView() {
        return logTableView;
    }
    public ListView<String> getProductList() {
        return productList;
    }
    public Button getBackButton() {
        return backButton;
    }
    public Button getAddTaskButton() {
        return addTaskButton;
    }
    public Button getMarkTaskButton() {
        return markTaskButton;
    }
    public Button getAddLogButton() {
        return addLogButton;
    }
    public Button getAddPurchaseButtonButton() {
        return addPurchaseButton;
    }

}