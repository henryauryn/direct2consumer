package org.henrylightfoot.d2c.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ReportsView {
    //buttons
    private Button backButton = new Button("← Back");
    private Button custReportButton = new Button("Active Customers");
    private Button productsReportButton = new Button("Export Product Directory");
    private Button custTaskRateButton = new Button("Task Completion Rate");

    public Parent getView() {
        // Header Section
        HBox header = new HBox();
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(20, 30, 20, 30));
        header.setSpacing(20);
        backButton.getStyleClass().add("back-button");
        Label title = new Label("reports");
        title.getStyleClass().add("page-title");
        header.getChildren().addAll(backButton, title);
        // Button Grid Section
        GridPane buttonGrid = new GridPane();
        buttonGrid.setPadding(new Insets(40, 30, 30, 30));
        buttonGrid.setHgap(30);
        buttonGrid.setVgap(30);
        buttonGrid.setAlignment(Pos.CENTER);
        custReportButton.getStyleClass().add("report-button");
        custReportButton.setMinSize(160, 100);
        buttonGrid.add(custReportButton, 0 % 2, 0 / 2);
        productsReportButton.getStyleClass().add("report-button");
        productsReportButton.setMinSize(160, 100);
        buttonGrid.add(productsReportButton, 1 % 2, 1 / 2);
        custTaskRateButton.getStyleClass().add("report-button");
        custTaskRateButton.setMinSize(160, 100);
        buttonGrid.add(custTaskRateButton, 2 % 2, 2 / 2);
        // Main Layout
        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(header, buttonGrid);
        mainLayout.setSpacing(10);
        mainLayout.setStyle("-fx-background-color: white;");

        return mainLayout;
    }
    //getters
    public Button getBackButton() { return backButton; }
    public Button getCustReportButton() { return custReportButton; }
    public Button getProductsReportButton() { return productsReportButton; }
    public Button getCustTaskRateButton() { return custTaskRateButton; }

}
