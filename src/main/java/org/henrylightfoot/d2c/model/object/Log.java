package org.henrylightfoot.d2c.model.object;

public class Log extends d2cObject {
    private int custID;
    public Log(int uniqueID, String name, String date, String details, int custID) {
        super(uniqueID, name, date, details);
        this.custID = custID;
    }
    public int getCustID() {
        return custID;
    }
}
