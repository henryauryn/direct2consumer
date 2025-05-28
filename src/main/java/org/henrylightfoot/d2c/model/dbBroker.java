package org.henrylightfoot.d2c.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.henrylightfoot.d2c.model.factory.CustomerFactory;
import org.henrylightfoot.d2c.model.factory.TaskFactory;
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

    public void insertNewProduct(String name, double price, double volume, String quantity, String form) {
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement("insert into product values (default, ?, ?, ?, ?, ?);");
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setDouble(3, volume);
            stmt.setString(4, quantity);
            stmt.setString(5, form);
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

    public ArrayList<d2cObject> getAllTaskTableData() {
        TaskFactory taskFactory = new TaskFactory();
        ArrayList<d2cObject> results = new ArrayList<d2cObject>();
        String query = """
        select
            task_id,
            type,
            deadline,
            further_description,
            task.customer_id,
            customer."name"
        from
            task
        inner join customer on
            customer.customer_id = task.customer_id
            and done = false;
        """;
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement(query);
            ResultSet rs = accessService.runQueryWithResponse(stmt);
            while (rs.next()) {
                results.add(taskFactory.create(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), false, rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public d2cObject getCustomer(int custID) {
        CustomerFactory customerFactory = new CustomerFactory();
        ArrayList<d2cObject> results = new ArrayList<d2cObject>();
        d2cObject customer = null;
        String query = """
        select
        	*
        from
        	customer
        where
        	customer_id = ?;
        """;
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement(query);
            stmt.setInt(1, custID);
            ResultSet rs = accessService.runQueryWithResponse(stmt);
            while (rs.next()) {
                customer = customerFactory.create(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 0, false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void taskCompleted(int taskId) {
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement("update task set done = true where task_id = ?;");
            stmt.setInt(1, taskId);
            accessService.runQueryNoResponse(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getAllProductTableData() {
        ArrayList<Product> results = new ArrayList<Product>();
        String query = """
        
        select
            product.product_id,
            CONCAT(scent_name, ' ', product_form) as name,
            price,
            volume,
            quantity,
            CONCAT('£', coalesce(SUM(case when "customer_product".product_id is not null then price else 0 end), 0), ' from ', COUNT(case when "customer_product".product_id is not null then "customer_product".product_id else null end), ' units') as total_spent
           from
            product
           left join "customer_product" on
            product.product_id = "customer_product".product_id
           group by
            product.product_id,
            product.price;
        """;
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement(query);
            ResultSet rs = accessService.runQueryWithResponse(stmt);
            while (rs.next()) {
                results.add(new Product(rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getString(5), rs.getInt(1), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }



}
