package org.henrylightfoot.d2c.view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProductsView {
    public Parent getView() {
        VBox root = new VBox();
        root.setSpacing(15);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(new Label("Products"));
        return root;
    }
}
