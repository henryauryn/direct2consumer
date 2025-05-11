package org.henrylightfoot;

import java.sql.*;

public class databaseThings {
    public String getStuff() {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/direct2consumer";
        String user = "henrylightfoot";
        String password = "";
        String results = "";
        try
        {
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customer");
            while (rs.next()) {
                results = results.concat(" ID: ");
                results = results.concat(rs.getString(1));
                results = results.concat(" Name: ");
                results = results.concat(rs.getString(2));
                results = results.concat(" Date Of Birth: ");
                results = results.concat(rs.getString(3));
                results = results.concat(" Email: ");
                results = results.concat(rs.getString(4));
            }
            rs.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return results;
    }

}
