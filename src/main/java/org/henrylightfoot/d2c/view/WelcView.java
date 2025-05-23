package org.henrylightfoot.d2c.view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class WelcView {
    private final Button viewCustomersButton = new Button("View customers");
    private final Button reportsButton = new Button("Generate Reports");
    private final Button viewOutstandingTasks = new Button("View Outstanding Tasks");
    private final Button editProductDirectory = new Button("Edit Product Directory");
    private final Button exitButton = new Button("Exit");

    public Parent getView() {
        VBox root = new VBox();
        root.setSpacing(15);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(viewCustomersButton, reportsButton, viewOutstandingTasks, editProductDirectory, exitButton);
        return root;
    }

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
