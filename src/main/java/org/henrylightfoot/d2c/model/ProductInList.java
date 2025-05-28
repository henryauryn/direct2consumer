package org.henrylightfoot.d2c.model;

public class ProductInList {
    private final String name;
    private final int ID;
    //this exists as well as the Product class to provide convenient, lightweight objects for product ListViews on Customer profiles and in drop-down menus
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
