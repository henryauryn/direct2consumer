package org.henrylightfoot;

public class ConcreteCustomerFactory implements CustomerFactory {
    public Customer createCustomer(String name) {
        return new ConcreteCustomer(name);
    }
}
