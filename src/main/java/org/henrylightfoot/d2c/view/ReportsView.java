package org.henrylightfoot.d2c.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class ReportsView {

    private final Button backButton = new Button("← Back");


    public Parent getView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.getStyleClass().add("main-container");

        // Header
        Text title = new Text("Reports");
        title.getStyleClass().add("page-title");


        // Footer with back and add buttons
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bottomRow = new HBox(10, backButton, spacer);
        bottomRow.setAlignment(Pos.CENTER);
        bottomRow.setMaxWidth(Double.MAX_VALUE);

        layout.getChildren().addAll(title, bottomRow);

        return layout;
    }



    public Button getBackButton() { return backButton; }

}
