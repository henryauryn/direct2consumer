package org.henrylightfoot.d2c.model;

public class ProductInList {
    private final String name;
    private final int ID;

    ProductInList(int ID, String name) {
        this.name = name;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return name;
    }
    public int getID() {
        return ID;
    }
}
