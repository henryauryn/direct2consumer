package org.henrylightfoot.d2c.model.object;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class d2cObject {
    //JavaFX Beans Properties are used because of their lovely seamless integration with TableViews and CellValueFactories
    private ReadOnlyIntegerWrapper uniqueID;
    private StringProperty name;
    private StringProperty date;
    private StringProperty details;
    //custID protected so Task can easily access it and can set it even in situations where I use the d2cObject interface as an object type so any subsequent Task methods wouldn't be accessible
    protected ReadOnlyIntegerWrapper custID;
    //protected so only first-generation inheritees can access
    protected d2cObject(int uniqueID, String name, String date, String details) {
        this.uniqueID = new ReadOnlyIntegerWrapper(this, "uniqueID", uniqueID);
        this.name = new SimpleStringProperty(this, "name", name);
        this.date = new SimpleStringProperty(this, "date", date);
        this.details = new SimpleStringProperty(this, "details", details);
    }
    public int getCustID() {
        return custID.get();
    }
    //JavaFX TableView CellValueFactories look for the Property methods below, they have to be strictly named, case-sensitive [name]Property() otherwise the TableView won't see them
    public ReadOnlyIntegerProperty uniqueIDProperty() {
        return uniqueID;
    }
    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty dateProperty() {
        return date;
    }
    public StringProperty detailsProperty() {
        return details;
    }
}
