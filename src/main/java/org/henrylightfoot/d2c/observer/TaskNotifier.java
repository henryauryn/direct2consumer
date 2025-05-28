package org.henrylightfoot.d2c.observer;

import org.henrylightfoot.d2c.model.object.Task;
import org.henrylightfoot.d2c.model.object.d2cObject;

import java.util.ArrayList;
import java.util.List;

public class TaskNotifier {
    private final List<TaskDueObserver> observers = new ArrayList<>();

    public void addObserver(TaskDueObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskDueObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(int customerId, List<d2cObject> dueTasks) {
        for (TaskDueObserver observer : observers) {
            observer.onTasksDueToday(customerId, dueTasks);
        }
    }
}
