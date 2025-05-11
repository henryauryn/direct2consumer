module org.henrylightfoot.d2c {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.henrylightfoot.d2c to javafx.fxml;
    exports org.henrylightfoot.d2c;
}