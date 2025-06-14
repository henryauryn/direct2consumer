package org.henrylightfoot.d2c.controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.view.ProductsView;


public class ProductsController implements Controller {
    private final Triage triage;
    private final Scene scene;
    private final ProductsView view;
    private DialogueEngine dialogueEngine;

    public ProductsController(Triage triage) {
        this.triage = triage;
        view = new ProductsView();
        //populating choiceBoxes with drop-down options
        view.getQuantityChoiceBox().getItems().addAll("g", "ml");
        view.getTypeChoiceBox().getItems().addAll("Candle", "Eau de Parfum", "Eau de Toilette","Scented soap", "Perfumed hand cream", "Perfumed body balm", "Refillable solid perfume", "Hair mist", "Glass vessel", "Refill for home fragrance diffuser");
        setButtons();
        //formatting textfields so only numbers can be typed
        TextFormatter<String> priceFormatter = new TextFormatter<>(triage.getFilter());
        TextFormatter<String> volumeFormatter = new TextFormatter<>(triage.getFilter());
        view.getPrice().setTextFormatter(priceFormatter);
        view.getVolume().setTextFormatter(volumeFormatter);
        scene = new Scene(view.getView(), 900, 450);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }
    private void populateTable() {
        view.getTableView().getItems().clear();
        view.getTableView().getItems().addAll(triage.getDbService().getAllProductTableData());
    }

    public Scene getScene() {
        populateTable();
        return scene;
    }
    private void setButtons() {
        view.getBackButton().setOnAction(event -> {triage.showPage("welcome");});
        view.getAddButton().setOnAction(event -> {openAdd();});
        view.getCancelButton().setOnAction(event -> {closeWindow(view.getCancelButton());});
        view.getSaveButton().setOnAction(event -> {saveProduct(view.getScentName(), view.getPrice().getText(), view.getVolume().getText(), view.getQuantity(), view.getType());});
    }
    private void saveProduct(String name, String price, String volume, String quantity, String form) {
        triage.getDbService().insertNewProduct(name, Double.parseDouble(price), Double.parseDouble(volume), quantity, form);
        populateTable();
        closeWindow(view.getSaveButton());
    }
    private void closeWindow(Button theButton) {
        Stage currentStage = (Stage) theButton.getScene().getWindow();
        currentStage.close();
    }
    //open add product pop-up
    private void openAdd() {
        dialogueEngine = new DialogueEngine(triage, new Scene(view.getAddView(), 500, 400), "Add Product");
        dialogueEngine.activate();
    }
}