package org.henrylightfoot.d2c.model.object;

public class Task extends d2cObject {
    private int custID;
    private boolean done;

    public Task(int uniqueID, String name, String date, String details, int custID, boolean done) {
        super(uniqueID, name, date, details);
        this.custID = custID;
        this.done = done;
    }

    public int getCustID() {
        return custID;
    }
    public boolean isDone() {
        return done;
    }
}
