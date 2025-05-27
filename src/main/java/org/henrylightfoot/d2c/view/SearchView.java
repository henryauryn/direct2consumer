package org.henrylightfoot.d2c.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class SearchView {

    private final Button backButton = new Button("Back");
    private final TextField searchField = new TextField();
    private final Button searchButton = new Button("Search");
    private final Button addCustomerButton = new Button("Add Customer");

    private final TextField nameField = new TextField();
    private final DatePicker dobPicker = new DatePicker();
    private final TextField emailField = new TextField();
    private final TextField phoneField = new TextField();

    private final Button saveButton = new Button("Save");
    private final Button cancelButton = new Button("Cancel");

    public Parent getView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.getStyleClass().add("main-container");

        // Header
        Text title = new Text("Find Customers");
        title.getStyleClass().add("page-title");

        HBox searchRow = new HBox(10, searchField, searchButton);
        searchRow.getStyleClass().add("search-row");
        searchRow.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(searchField, Priority.ALWAYS);

        // Footer with back and add buttons
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bottomRow = new HBox(10, backButton, spacer, addCustomerButton);
        bottomRow.setAlignment(Pos.CENTER);
        bottomRow.setMaxWidth(Double.MAX_VALUE);

        layout.getChildren().addAll(title, searchRow, bottomRow);

        return layout;
    }

    public Parent getAddView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.getStyleClass().add("main-container");

        // Title
        Text title = new Text("Add Customer");
        title.getStyleClass().add("page-title");

        // Form layout
        VBox form = new VBox(15);
        form.setMaxWidth(400);

        nameField.setPromptText("Full Name");
        emailField.setPromptText("Email");
        phoneField.setPromptText("Phone");

        dobPicker.setPromptText("Date of Birth");

        form.getChildren().addAll(nameField, dobPicker, emailField, phoneField);

        // Button row
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox buttons = new HBox(10, cancelButton, spacer, saveButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setMaxWidth(Double.MAX_VALUE);

        layout.getChildren().addAll(title, form, buttons);

        return layout;
    }

    public Button getBackButton() { return backButton; }
    public TextField getSearchField() { return searchField; }
    public Button getSearchButton() { return searchButton; }
    public Button getAddCustomerButton() { return addCustomerButton; }
    public Button getSaveButton() { return saveButton; }
    public Button getCancelButton() { return cancelButton; }

    public String getSearchFieldText() { return searchField.getText(); }
    public String getNameFieldContents() { return nameField.getText(); }
    public String getEmailFieldContents() { return emailField.getText(); }
    public String getPhoneFieldContents() { return phoneField.getText(); }
    public String getDobFieldContents() { return dobPicker.getValue().toString(); }
}
