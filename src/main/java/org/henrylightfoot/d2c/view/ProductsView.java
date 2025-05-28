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
import javafx.scene.text.Text;
import org.henrylightfoot.d2c.model.Product;

import java.util.function.UnaryOperator;

public class ProductsView {
    private Button addButton = new Button("Add Product");
    private Button backButton = new Button("Back");
    private TableView<Product> tableView = new TableView<>();

    private final TextField scentNameField = new TextField();
    private final TextField volumeField = new TextField();
    private final TextField priceField = new TextField();
    private ChoiceBox<String> typeChoiceBox = new ChoiceBox<>();
    private ChoiceBox<String> quantityChoiceBox = new ChoiceBox<>();
    private Button cancelButton = new Button("Cancel");
    private Button saveButton = new Button("Save");

    public Parent getView() {
        Label header = new Label("Products");
        header.getStyleClass().add("page-title");


        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Product, Integer> idCol = new TableColumn<>("Product ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("productID"));

        TableColumn<Product, String> nameCol = new TableColumn<>("Product");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("scentName"));

        TableColumn<Product, Double> priceCol = new TableColumn<>("Price (£)");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Double> volCol = new TableColumn<>("Volume");
        volCol.setCellValueFactory(new PropertyValueFactory<>("volume"));

        TableColumn<Product, Double> quantCol = new TableColumn<>("Quantity");
        quantCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Product, String> purchasedCol = new TableColumn<>("Purchase Revenue (£)");
        purchasedCol.setCellValueFactory(new PropertyValueFactory<>("revenue"));



        tableView.getColumns().addAll(idCol, nameCol, priceCol, volCol, quantCol, purchasedCol);

        TableView.TableViewSelectionModel<Product> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        tableView.setPlaceholder(new Label("No products found"));


        addButton.getStyleClass().add("button");


        backButton.getStyleClass().add("button");



        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        buttonBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(buttonBox, Priority.ALWAYS);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        buttonBox.getChildren().addAll(backButton, spacer, addButton);


        VBox layout = new VBox(20, header, tableView, buttonBox);
        layout.getStyleClass().add("main-container");
        return layout;
    }
    public Parent getAddView() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.getStyleClass().add("main-container");

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?\\d*(\\.\\d*)?")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> volumeFormatter = new TextFormatter<>(filter);
        TextFormatter<String> priceFormatter = new TextFormatter<>(filter);
        volumeField.setTextFormatter(volumeFormatter);
        priceField.setTextFormatter(priceFormatter);


        // Title
        Text title = new Text("Add Product");
        title.getStyleClass().add("page-title");

        // Form layout
        VBox form = new VBox(15);
        form.setMaxWidth(400);

        Label productLabel = new Label("Product form:");
        productLabel.getStyleClass().add("choiceLabel");
        typeChoiceBox.getStyleClass().add("choice-box");

        typeChoiceBox.setValue("Candle");

        Label quantityLabel = new Label("Quantity:");
        quantityLabel.getStyleClass().add("choiceLabel");
        quantityChoiceBox.getStyleClass().add("choice-box");

        quantityChoiceBox.setValue("g");

        scentNameField.setPromptText("Scent Name");
        volumeField.setPromptText("Volume");
        priceField.setPromptText("Price");

        form.getChildren().addAll(scentNameField, priceField, typeChoiceBox, volumeField, quantityChoiceBox);

        // Button row
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox buttons = new HBox(10, cancelButton, spacer, saveButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setMaxWidth(Double.MAX_VALUE);

        layout.getChildren().addAll(title, form, buttons);

        return layout;
    }

    public Button getBackButton() {
        return backButton;
    }
    public Button getAddButton() {
        return addButton;
    }
    public TableView<Product> getTableView() {
        return tableView;
    }
    public String getScentName() {
        return scentNameField.getText();
    }
    public String getVolume() {
        return volumeField.getText();
    }
    public String getPrice() {
        return priceField.getText();
    }
    public String getType() {
        return typeChoiceBox.getValue();
    }
    public String getQuantity() {
        return quantityChoiceBox.getValue();
    }
    public Button getSaveButton() {
        return saveButton;
    }
    public Button getCancelButton() {
        return cancelButton;
    }
    public ChoiceBox<String> getTypeChoiceBox() {
        return typeChoiceBox;
    }
    public ChoiceBox<String> getQuantityChoiceBox() {
        return quantityChoiceBox;
    }


}
