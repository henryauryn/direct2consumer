package org.henrylightfoot;

public abstract class Customer {
    protected String name;
    protected String dateOfBirth;
    protected String contactDetails;

    @Override
    public String toString() {
        return String.format("Hi! I'm a customer. My name is %s, my date of birth is %s and my email is %s. I'm excited to be here!", name, dateOfBirth, contactDetails);
    }
}
