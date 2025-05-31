package org.henrylightfoot.d2c.model;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DAO {
    //enter your credentials here after creating the seed database from the provided SQL scripts in the resources folder
    private final String jdbcUrl = "jdbc:postgresql://";
    private final String user = "";
    private final String password = "";
    private Connection conn;
    private Statement stmt;

    public DAO() {
        connect();
    }
    //make sure conenct method is private, to avoid methods going to use a potentially disconnected connection
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
    //used for queries like insert customer, task etc
    public void runQueryNoResponse(PreparedStatement query) {
        try {
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ResultSet object return type means can use for any type of eliciting-response query
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
