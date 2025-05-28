package org.henrylightfoot.d2c.observer;

import org.henrylightfoot.d2c.model.object.Task;
import org.henrylightfoot.d2c.model.object.d2cObject;

import java.util.List;

public class ConsoleNotifier implements TaskDueObserver {
    //console version of FXTaskDueObserver
    @Override
    public void onTasksDueToday(int customerId, List<d2cObject> dueTasks) {
        System.out.println("Customer ID: " + customerId + " has the following tasks due today:");
        for (d2cObject task : dueTasks) {
            System.out.println("- " + task.dateProperty().get() + ": " + task.detailsProperty().get());
        }
    }
}


