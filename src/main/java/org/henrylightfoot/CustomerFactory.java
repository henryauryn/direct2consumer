package org.henrylightfoot;

public interface CustomerFactory {
    Customer createCustomer(String name, String DOB, String contact);
}
