package org.henrylightfoot.d2c.observer;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.henrylightfoot.d2c.model.object.d2cObject;

import java.util.List;

public class FXTaskDueObserver implements TaskDueObserver {
    //GUI observer implementation that creates JavaFX Alerts for any tasks matching the system's current date
    @Override
    public void onTasksDueToday(int customerId, List<d2cObject> dueTasks) {
        //Platform.runLater used to avoid multi-threading problems
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Task Notification");
            alert.setHeaderText("Customer ID: " + customerId + " has tasks due today");

            StringBuilder content = new StringBuilder();
            for (d2cObject task : dueTasks) {
                content.append("- ").append(task.nameProperty().get())
                        .append(": ").append(task.detailsProperty().get())
                        .append("\n");
            }

            alert.setContentText(content.toString());
            alert.showAndWait();
        });
    }
}

