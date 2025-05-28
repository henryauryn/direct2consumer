package org.henrylightfoot.d2c.model.object;

public class Customer extends d2cObject {
    //no additional functionality needed so calling super d2cObject constructor fine
    public Customer(int uniqueID, String name, String date, String details) {
        super(uniqueID, name, date, details);
    }
}
