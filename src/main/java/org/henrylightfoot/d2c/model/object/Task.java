package org.henrylightfoot.d2c.model.object;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task extends d2cObject {
    private ReadOnlyIntegerWrapper custID;
    private StringProperty custName;
    private boolean done;

    public Task(int uniqueID, String name, String date, String details, int custID, boolean done) {
        super(uniqueID, name, date, details);
        this.custID = new ReadOnlyIntegerWrapper(this, "custId", custID);
        this.done = done;
    }

    public Task(int uniqueID, String name, String date, String details, int custID, boolean done, String custName) {
        super(uniqueID, name, date, details);
        this.custID = new ReadOnlyIntegerWrapper(this, "custId", custID);
        this.done = done;
        this.custName = new SimpleStringProperty(this, "custName", custName);
    }

    public ReadOnlyIntegerProperty custIdProperty() {
        return custID;
    }
    public StringProperty custNameProperty() {
        return custName;
    }
    public boolean isDone() {
        return done;
    }
}
