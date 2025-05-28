package org.henrylightfoot.d2c.model.factory;

import org.henrylightfoot.d2c.model.object.Customer;
import org.henrylightfoot.d2c.model.object.d2cObject;
//factory for the factory design pattern implementation
public class CustomerFactory extends d2cFactory {
    public d2cObject create(int uniqueID, String name, String date, String details, int custID, boolean done) {
        return new Customer(uniqueID, name, date, details);
    }
}
