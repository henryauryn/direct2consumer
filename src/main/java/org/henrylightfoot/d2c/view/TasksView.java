package org.henrylightfoot.d2c.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.henrylightfoot.d2c.model.object.d2cObject;

public class TasksView {
    private Button completedButton = new Button("Mark as completed");
    private Button backButton = new Button("Back");
    private Button okButton = new Button("Okay");
    private TableView<d2cObject> tableView = new TableView<>();

    public Parent getView() {
        Label header = new Label("Outstanding Tasks");
        header.getStyleClass().add("page-title");


        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<d2cObject, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("uniqueID"));

        TableColumn<d2cObject, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<d2cObject, String> dateCol = new TableColumn<>("Deadline");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<d2cObject, String> detailsCol = new TableColumn<>("Description");
        detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));

        TableColumn<d2cObject, String> custIdCol = new TableColumn<>("Customer ID");
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));

        TableColumn<d2cObject, String> custNameCol = new TableColumn<>("Customer Name");
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("custName"));

        tableView.getColumns().addAll(idCol, typeCol, dateCol, detailsCol, custIdCol, custNameCol);

        TableView.TableViewSelectionModel<d2cObject> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        tableView.setPlaceholder(new Label("No tasks found"));


        completedButton.getStyleClass().add("button");


        backButton.getStyleClass().add("button");



        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        buttonBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(buttonBox, Priority.ALWAYS);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        buttonBox.getChildren().addAll(backButton, spacer, completedButton);


        VBox layout = new VBox(20, header, tableView, buttonBox);
        layout.getStyleClass().add("main-container");

        return layout;
    }

    public Parent getAlert() {
        Label title = new Label("Oops!");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: 600; -fx-text-fill: #222;");
        Label message = new Label("Task not selected.");
        message.setStyle("-fx-font-size: 14px; -fx-text-fill: #444;");
        okButton.getStyleClass().add("button");

        VBox layout = new VBox(15, title, message, okButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setSpacing(15);
        layout.getStyleClass().add("alert-container");
        return layout;
    }

    public Button getBackButton() {
        return backButton;
    }
    public Button getCompletedButton() {
        return completedButton;
    }
    public Button getOkButton() {
        return okButton;
    }
    public TableView<d2cObject> getTableView() {
        return tableView;
    }


}
