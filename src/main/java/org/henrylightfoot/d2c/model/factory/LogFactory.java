package org.henrylightfoot.d2c.model.factory;

import org.henrylightfoot.d2c.model.object.Log;
import org.henrylightfoot.d2c.model.object.d2cObject;

public class LogFactory extends d2cFactory {
    public d2cObject create(int uniqueID, String name, String date, String details, int custID, boolean done) {
        return new Log(uniqueID, name, date, details, custID);
    }
    public Log create(String name, String date, String details, int custID) {
        return new Log(0, name, date, details, custID);
    }
}
