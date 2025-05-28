package org.henrylightfoot.d2c.model.object;

import javafx.beans.property.ReadOnlyIntegerWrapper;

public class TestD2CObject extends d2cObject {
    public TestD2CObject(int uniqueID, String name, String date, String details, int custID) {
        super(uniqueID, name, date, details);
        this.custID = new ReadOnlyIntegerWrapper(this, "custID", custID);
    }
}

