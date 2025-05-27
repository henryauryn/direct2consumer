module org.henrylightfoot.d2c {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.henrylightfoot.d2c to javafx.fxml;
    exports org.henrylightfoot.d2c;
    exports org.henrylightfoot.d2c.controller;
    opens org.henrylightfoot.d2c.controller to javafx.fxml;
    exports org.henrylightfoot.d2c.view;
    opens org.henrylightfoot.d2c.view to javafx.fxml;
    exports org.henrylightfoot.d2c.model.object;
    opens org.henrylightfoot.d2c.model.object to javafx.fxml;
    exports org.henrylightfoot.d2c.model.factory;
    opens org.henrylightfoot.d2c.model.factory to javafx.fxml;
    exports org.henrylightfoot.d2c.model;
    opens org.henrylightfoot.d2c.model to javafx.fxml;

}