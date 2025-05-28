package org.henrylightfoot.d2c.view;


import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.henrylightfoot.d2c.model.ProductInList;
import org.henrylightfoot.d2c.model.object.d2cObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullCustomerViewTest {

    private static FullCustomerView view;

    @BeforeAll
    static void setup() throws Exception {
        // Initialize JavaFX environment
        Platform.startup(() -> {});
        view = new FullCustomerView();
    }

    @Test
    void testCustomerLabelsAreInitialized() {
        assertNotNull(view.getCustomerHeader());
        assertNotNull(view.getCustomerEmail());
        assertNotNull(view.getCustomerDOB());
    }

    @Test
    void testAddTaskViewInitializesTaskChoice() {
        Parent parent = view.getAddTaskView();
        assertFalse(view.getTaskChoice().getItems().isEmpty(), "Task choices should be initialized");
    }

    @Test
    void testAddPurchaseViewInitializesFields() {
        Parent parent = view.getAddPurchaseView();
        assertNotNull(view.getProductChoice(), "Product choice should be initialized");
        assertNotNull(view.getQuantityField(), "Quantity field should be initialized");
        assertNotNull(view.getPurchaseDatePicker(), "Purchase date picker should be initialized");
    }

    @Test
    void testAlertViewStructure() {
        Parent parent = view.getAlert();
        assertTrue(parent instanceof VBox, "Alert should be a VBox");
        assertNotNull(view.getOkButton());
    }



    @Test
    void testSaveAndCancelButtonsAreShared() {
        Button save = view.getSaveButton();
        Button cancel = view.getCancelButton();
        assertNotNull(save);
        assertNotNull(cancel);
        assertSame(save, view.getSaveButton());
        assertSame(cancel, view.getCancelButton());
    }
}
