package org.henrylightfoot.d2c.model.object;

import javafx.beans.property.*;

public class Task extends d2cObject {
    //additional properties beyond d2cObject declared
    private StringProperty custName;
    private BooleanProperty done;

    public Task(int uniqueID, String name, String date, String details, int custID, boolean done) {
        super(uniqueID, name, date, details);
        this.custID = new ReadOnlyIntegerWrapper(this, "custId", custID);
        this.done = new SimpleBooleanProperty(this, "done", done);
    }
    //two constructors, one including a custName for ease when displaying Tables
    public Task(int uniqueID, String name, String date, String details, int custID, boolean done, String custName) {
        super(uniqueID, name, date, details);
        this.custID = new ReadOnlyIntegerWrapper(this, "custId", custID);
        this.done = new SimpleBooleanProperty(this, "done", done);
        this.custName = new SimpleStringProperty(this, "custName", custName);
    }

    public ReadOnlyIntegerProperty custIdProperty() {
        return custID;
    }
    public StringProperty custNameProperty() {
        return custName;
    }
    public BooleanProperty doneProperty() {
        return done;
    }

}
