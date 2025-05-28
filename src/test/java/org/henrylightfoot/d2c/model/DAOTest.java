package org.henrylightfoot.d2c.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DAOTest {

    static DAO dao;

    @BeforeAll
    static void setup() {
        dao = new DAO();
    }

    @Test
    @Order(1)
    public void testConnectionIsNotNull() {
        assertNotNull(dao.getConn(), "Connection should not be null");
    }

    @Test
    @Order(2)
    public void testRunQueryNoResponseInsert() throws SQLException {
        String sql = "INSERT INTO customer (name) VALUES (?)";
        PreparedStatement stmt = dao.getConn().prepareStatement(sql);
        stmt.setString(1, "Alice");
        dao.runQueryNoResponse(stmt);

        // Check that the row was inserted
        ResultSet rs = dao.getConn().prepareStatement("SELECT * FROM customer WHERE name='Alice'")
                .executeQuery();
        assertTrue(rs.next(), "Inserted row should be found");
    }

    @Test
    @Order(3)
    public void testRunQueryWithResponse() throws SQLException {
        String sql = "SELECT * FROM customer WHERE name = ?";
        PreparedStatement stmt = dao.getConn().prepareStatement(sql);
        stmt.setString(1, "George Santos");

        ResultSet rs = dao.runQueryWithResponse(stmt);
        assertNotNull(rs, "ResultSet should not be null");
        assertTrue(rs.next(), "ResultSet should contain results");
        assertEquals("George Santos", rs.getString("name"));
    }

    @Test
    @Order(4)
    public void testRunQueryWithResponseInvalidSQL() throws SQLException {
        String invalidSQL = "SELECT * FROM non_existent_table";
        PreparedStatement stmt = dao.getConn().prepareStatement(invalidSQL);

        ResultSet rs = dao.runQueryWithResponse(stmt);
        assertNull(rs, "ResultSet should be null for invalid query");
    }


}
