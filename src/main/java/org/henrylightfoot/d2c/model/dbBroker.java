package org.henrylightfoot.d2c.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.henrylightfoot.d2c.model.factory.CustomerFactory;
import org.henrylightfoot.d2c.model.object.Customer;
import org.henrylightfoot.d2c.model.object.d2cObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class dbBroker {
    DAO accessService = new DAO();
    public void insertNewCustomer(String name, String dateOfBirth, String email) {
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement("INSERT INTO customer VALUES (DEFAULT, ?,?,?)");
            stmt.setString(1, name);
            stmt.setDate(2, java.sql.Date.valueOf(dateOfBirth));
            stmt.setString(3, email);
            accessService.runQueryNoResponse(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<d2cObject> searchCustomers(String query) {
        ArrayList<d2cObject> customers = new ArrayList<d2cObject>();
        CustomerFactory customerFactory = new CustomerFactory();

        String sql = "SELECT customer_id, name, date_of_birth, contact_details FROM customer WHERE " +
                "LOWER(name) LIKE ? OR LOWER(contact_details) LIKE ?";

        try (
             PreparedStatement stmt = accessService.getConn().prepareStatement(sql)) {

            String wildcardQuery = "%" + query.toLowerCase() + "%";
            stmt.setString(1, wildcardQuery);
            stmt.setString(2, wildcardQuery);

            ResultSet rs = accessService.runQueryWithResponse(stmt);
            while (rs.next()) {
                customers.add(customerFactory.create(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 0, false));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return customers;
    }

}
