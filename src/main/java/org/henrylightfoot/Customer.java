package org.henrylightfoot;

public abstract class Customer {
    protected String name;
    protected String dateOfBirth;
    protected String contactDetails;

    @Override
    public String toString() {
        return name;
    }
}
