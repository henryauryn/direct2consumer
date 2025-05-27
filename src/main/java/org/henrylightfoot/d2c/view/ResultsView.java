package org.henrylightfoot.d2c.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.henrylightfoot.d2c.model.object.Customer;
import org.henrylightfoot.d2c.model.object.d2cObject;

public class ResultsView {
    private Button viewButton = new Button("View");
    private Button backButton = new Button("Back");
    private TableView<d2cObject> tableView = new TableView<>();

    public Parent getView() {
        Label header = new Label("Results");
        header.getStyleClass().add("page-title");


        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<d2cObject, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("uniqueID"));

        TableColumn<d2cObject, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<d2cObject, String> dateCol = new TableColumn<>("Date of Birth");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<d2cObject, String> detailsCol = new TableColumn<>("Contact Details");
        detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));

        tableView.getColumns().addAll(idCol, nameCol, dateCol, detailsCol);

        TableView.TableViewSelectionModel<d2cObject> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        tableView.setPlaceholder(new Label("No results found"));


        viewButton.getStyleClass().add("button");


        backButton.getStyleClass().add("button");



        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        buttonBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(buttonBox, Priority.ALWAYS);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        buttonBox.getChildren().addAll(backButton, spacer, viewButton);


        VBox layout = new VBox(20, header, tableView, buttonBox);
        layout.getStyleClass().add("main-container");
        VBox root = new VBox();
        root.setSpacing(15);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(new Label("Search Result"));
        return layout;
    }

    public Button getBackButton() {
        return backButton;
    }
    public Button getViewButton() {
        return viewButton;
    }
    public TableView<d2cObject> getTableView() {
        return tableView;
    }


}
