package org.henrylightfoot.d2c.observer;

import org.henrylightfoot.d2c.model.object.Task;
import org.henrylightfoot.d2c.model.object.d2cObject;

import java.util.List;

public interface TaskDueObserver {
    void onTasksDueToday(int customerId, List<d2cObject> dueTasks);
}

