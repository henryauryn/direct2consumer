package org.henrylightfoot;

import java.sql.*;

public class databaseThings {
    private static String jdbcUrl = "jdbc:postgresql://localhost:5432/direct2consumer";
    private static String user = "henrylightfoot";
    private static String password = "";
    private static String results = "";

    public static void viewAllCustomers() {
        try
        {
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customer");
            while (rs.next()) {
                System.out.printf("ID: %s, Name: %s, Date-of-Birth: %s, Email Address: %s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            rs.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void insertCustomer(String name, String dateOfBirth, String email) {
        try {
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            String insertCustomer = "INSERT INTO customer VALUES (DEFAULT, ?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCustomer);
            stmt.setString(1, name);
            stmt.setDate(2, java.sql.Date.valueOf(dateOfBirth));
            stmt.setString(3, email);
            stmt.executeUpdate();
            System.out.println("Successfully inserted customer.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
