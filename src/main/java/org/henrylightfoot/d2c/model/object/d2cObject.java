package org.henrylightfoot.d2c.model.object;

public abstract class d2cObject {
    private int uniqueID;
    private String name;
    private String date;
    private String details;

    protected d2cObject(int uniqueID, String name, String date, String details) {
        this.uniqueID = uniqueID;
        this.name = name;
        this.date = date;
        this.details = details;
    }
    public int getUniqueID() {
        return uniqueID;
    }
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String getDetails() {
        return details;
    }
}
