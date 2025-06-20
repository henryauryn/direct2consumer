package org.henrylightfoot.d2c.view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class WelcView {
    //home page buttons
    private Button viewCustomersButton = new Button("Customers");
    private Button reportsButton = new Button("Reports");
    private Button viewOutstandingTasks = new Button("Outstanding Tasks");
    private Button editProductDirectory = new Button("Product Directory");
    private Button exitButton = new Button("Exit");

    public Parent getView() {
        // Title
        Label titleLabel = new Label("Welcome");
        titleLabel.getStyleClass().add("page-title");
        viewCustomersButton.getStyleClass().add("button");
        reportsButton.getStyleClass().add("button");
        viewOutstandingTasks.getStyleClass().add("button");
        editProductDirectory.getStyleClass().add("button");
        exitButton.getStyleClass().add("button");
        VBox buttonBox = new VBox(15,
                viewCustomersButton,
                reportsButton,
                viewOutstandingTasks,
                editProductDirectory,
                exitButton
        );
        buttonBox.setAlignment(Pos.CENTER);
        VBox root = new VBox(40, titleLabel, buttonBox);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("main-container");
        return root;
    }
    //getters
    public Button getExitButton() {
        return exitButton;
    }
    public Button getViewOutstandingTasksButton() {
        return viewOutstandingTasks;
    }
    public Button getEditProductDirectoryButton() {
        return editProductDirectory;
    }
    public Button getReportsButton() {
        return reportsButton;
    }
    public Button getViewCustomersButton() {
        return viewCustomersButton;
    }
}
