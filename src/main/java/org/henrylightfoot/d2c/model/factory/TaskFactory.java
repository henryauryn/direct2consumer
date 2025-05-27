package org.henrylightfoot.d2c.model.factory;

import org.henrylightfoot.d2c.model.object.Task;
import org.henrylightfoot.d2c.model.object.d2cObject;

public class TaskFactory extends d2cFactory {
    public d2cObject create(int uniqueID, String name, String date, String details, int custID, boolean done) {
        return new Task(uniqueID, name, date, details, custID, done);
    }

    public d2cObject create(int uniqueID, String name, String date, String details, int custID, boolean done, String custName) {
        return new Task(uniqueID, name, date, details, custID, done, custName);
    }
}
