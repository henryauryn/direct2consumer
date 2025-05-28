package org.henrylightfoot.d2c.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import org.henrylightfoot.d2c.model.object.Log;
import org.henrylightfoot.d2c.model.object.Task;

public class FullCustomerView {
    private Button backButton = new Button("← Back");
    private Label customerHeader = new Label("Customer Profile");
    private Label customerName = new Label("Name: John Doe");
    private Label customerEmail = new Label("Email: john.doe@example.com");
    private Label customerDOB = new Label("Date of Birth: 1990-01-01");
    private TableView<Task> taskTableView = new TableView();
    private TableView<Log> logTableView = new TableView();
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

        // Profile panel
        VBox custLabels = new VBox(10, customerName, customerEmail, customerDOB);
        customerName.getStyleClass().add("choiceLabel");
        customerEmail.getStyleClass().add("choiceLabel");
        customerDOB.getStyleClass().add("choiceLabel");
        Label custTitleLabel = new Label("Customer Info");
        custTitleLabel.getStyleClass().add("panel-title");
        VBox.setVgrow(custLabels, Priority.ALWAYS);
        custLabels.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox profilePanel = new VBox(10, custTitleLabel, custLabels);
        profilePanel.getStyleClass().add("panel");

        //tasks panel
        Label tasksLabel = new Label("Assigned Tasks");
        tasksLabel.getStyleClass().add("panel-title");
        VBox tasksPanel = new VBox(10, tasksLabel, taskTableView);
        tasksPanel.getStyleClass().add("panel");
        VBox.setVgrow(taskTableView, Priority.ALWAYS);
        taskTableView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //logs panel
        Label logLabel = new Label("Activity Logs");
        logLabel.getStyleClass().add("panel-title");
        VBox logsPanel = new VBox(10, logLabel, logTableView);
        logsPanel.getStyleClass().add("panel");
        VBox.setVgrow(logTableView, Priority.ALWAYS);
        logTableView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //purchases panel
        Label titleLabel = new Label("Purchases");
        titleLabel.getStyleClass().add("panel-title");
        VBox purchasesPanel = new VBox(10, titleLabel, productList);
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
    public Label getCustomerName() {
        return customerName;
    }
    public Label getCustomerEmail() {
        return customerEmail;
    }
    public Label getCustomerDOB() {
        return customerDOB;
    }
    public TableView<Task> getTaskTableView() {
        return taskTableView;
    }
    public TableView<Log> getLogTableView() {
        return logTableView;
    }
    public ListView<String> getProductList() {
        return productList;
    }
    public Button getBackButton() {
        return backButton;
    }

}