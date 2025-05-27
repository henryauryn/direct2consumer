package org.henrylightfoot.d2c.model;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DAO {
    private final String jdbcUrl = "jdbc:postgresql://localhost:5432/direct2consumer";
    private final String user = "henrylightfoot";
    private final String password = "";
    private Connection conn;
    private Statement stmt;

    public DAO() {
        connect();
    }

    private void connect() {
        try {
            conn = DriverManager.getConnection(jdbcUrl, user, password);
            Statement stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void disconnect() throws SQLException {
        conn.close();
    }

    public Connection getConn() {
        return conn;
    }

    public void runQueryNoResponse(PreparedStatement query) {
        try {
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet runQueryWithResponse(PreparedStatement query) {
        ResultSet placeholder = null;
        try {
            placeholder = query.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return placeholder;
    }
}
