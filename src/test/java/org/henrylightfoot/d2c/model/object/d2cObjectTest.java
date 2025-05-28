package org.henrylightfoot.d2c.model.object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.henrylightfoot.d2c.model.object.d2cObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class d2cObjectTest {

    @Test
    public void testPropertyInitialization() {
        TestD2CObject obj = new TestD2CObject(1, "Test Name", "2025-05-28", "Details here", 99);

        assertEquals(1, obj.uniqueIDProperty().get());
        assertEquals("Test Name", obj.nameProperty().get());
        assertEquals("2025-05-28", obj.dateProperty().get());
        assertEquals("Details here", obj.detailsProperty().get());
        assertEquals(99, obj.getCustID());
    }

    @Test
    public void testPropertyBinding() {
        TestD2CObject obj = new TestD2CObject(2, "Old Name", "2025-01-01", "Old details", 88);

        obj.nameProperty().set("New Name");
        obj.dateProperty().set("2025-12-31");
        obj.detailsProperty().set("Updated details");

        assertEquals("New Name", obj.nameProperty().get());
        assertEquals("2025-12-31", obj.dateProperty().get());
        assertEquals("Updated details", obj.detailsProperty().get());
    }

    @Test
    public void testPropertyObjectsNotNull() {
        TestD2CObject obj = new TestD2CObject(3, "Check", "2025-06-01", "Non-null test", 77);

        assertNotNull(obj.uniqueIDProperty());
        assertNotNull(obj.nameProperty());
        assertNotNull(obj.dateProperty());
        assertNotNull(obj.detailsProperty());
    }
}
