package org.henrylightfoot.d2c.model.object;

import org.henrylightfoot.d2c.model.object.Task;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testConstructorWithoutCustName() {
        Task task = new Task(1, "Task1", "2025-05-28", "Description", 42, true);

        assertEquals(1, task.uniqueIDProperty().get());
        assertEquals("Task1", task.nameProperty().get());
        assertEquals("2025-05-28", task.dateProperty().get());
        assertEquals("Description", task.detailsProperty().get());
        assertEquals(42, task.custIdProperty().get());
        assertTrue(task.doneProperty().get());

        assertNull(task.custNameProperty()); // not set in this constructor
    }

    @Test
    public void testConstructorWithCustName() {
        Task task = new Task(2, "Task2", "2025-06-01", "More work", 99, false, "Henry");

        assertEquals(2, task.uniqueIDProperty().get());
        assertEquals("Task2", task.nameProperty().get());
        assertEquals("2025-06-01", task.dateProperty().get());
        assertEquals("More work", task.detailsProperty().get());
        assertEquals(99, task.custIdProperty().get());
        assertFalse(task.doneProperty().get());
        assertEquals("Henry", task.custNameProperty().get());
    }

    @Test
    public void testDonePropertyBinding() {
        Task task = new Task(3, "Task3", "2025-07-01", "Something", 5, false);
        BooleanProperty done = task.doneProperty();

        done.set(true);
        assertTrue(done.get());
        assertTrue(task.doneProperty().get());
    }



    @Test
    public void testNonNullProperties() {
        Task task = new Task(5, "Task5", "2025-09-01", "Details", 77, false, "Alice");

        assertNotNull(task.uniqueIDProperty());
        assertNotNull(task.nameProperty());
        assertNotNull(task.dateProperty());
        assertNotNull(task.detailsProperty());
        assertNotNull(task.custIdProperty());
        assertNotNull(task.doneProperty());
        assertNotNull(task.custNameProperty());
    }
}
