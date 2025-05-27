package org.henrylightfoot.d2c.model.object;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class d2cObject {
    private ReadOnlyIntegerWrapper uniqueID;
    private StringProperty name;
    private StringProperty date;
    private StringProperty details;

    protected d2cObject(int uniqueID, String name, String date, String details) {
        this.uniqueID = new ReadOnlyIntegerWrapper(this, "uniqueID", uniqueID);
        this.name = new SimpleStringProperty(this, "name", name);
        this.date = new SimpleStringProperty(this, "date", date);
        this.details = new SimpleStringProperty(this, "details", details);
    }
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
