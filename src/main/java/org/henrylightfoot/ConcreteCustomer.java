package org.henrylightfoot;

public class ConcreteCustomer extends Customer {
    public ConcreteCustomer(String name, String DOB, String contact) {
        this.name = name;
        this.dateOfBirth = DOB;
        this.contactDetails = contact;
    }
}
