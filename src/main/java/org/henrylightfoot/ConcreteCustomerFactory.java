package org.henrylightfoot;

public class ConcreteCustomerFactory implements CustomerFactory {
    public Customer createCustomer(String name, String DOB, String contact) {
        return new ConcreteCustomer(name, DOB, contact);
    }
}
