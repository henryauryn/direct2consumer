package org.henrylightfoot.d2c.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.henrylightfoot.d2c.Triage;
import org.henrylightfoot.d2c.model.factory.CustomerFactory;
import org.henrylightfoot.d2c.model.factory.LogFactory;
import org.henrylightfoot.d2c.model.factory.TaskFactory;
import org.henrylightfoot.d2c.model.object.Customer;
import org.henrylightfoot.d2c.model.object.Log;
import org.henrylightfoot.d2c.model.object.Task;
import org.henrylightfoot.d2c.model.object.d2cObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class dbBroker {
    public final DAO accessService;
    public dbBroker(Triage triage) {
        this.accessService = triage.getAccessService();
    }

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

    public ArrayList<d2cObject> getAllTasks(int custID) {
        TaskFactory taskFactory = new TaskFactory();
        ArrayList<d2cObject> results = new ArrayList<d2cObject>();
        String query = """
        select
        	*
        from
        	task
        where
        	customer_id = ?;
        """;
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement(query);
            stmt.setInt(1, custID);
            ResultSet rs = accessService.runQueryWithResponse(stmt);
            while (rs.next()) {
                results.add(taskFactory.create(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public ArrayList<d2cObject> getAllLogs(int custID) {
        LogFactory logFactory = new LogFactory();
        ArrayList<d2cObject> results = new ArrayList<d2cObject>();
        String query = """
        select
        	*
        from
        	log
        where
        	customer_id = ?;
        """;
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement(query);
            stmt.setInt(1, custID);
            ResultSet rs = accessService.runQueryWithResponse(stmt);
            while (rs.next()) {
                results.add(logFactory.create(rs.getInt(3), rs.getString(1), rs.getString(5), rs.getString(2), rs.getInt(4), false));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
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

    public void insertTask(Task task) {
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement("INSERT INTO task VALUES (DEFAULT, ?, ?, ?, ?, ?)");
            stmt.setString(1, task.nameProperty().get());
            stmt.setDate(2, java.sql.Date.valueOf(task.dateProperty().get()));
            stmt.setString(3, task.detailsProperty().get());
            stmt.setInt(4, task.custIdProperty().get());
            stmt.setBoolean(5, task.doneProperty().get());

            accessService.runQueryNoResponse(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertLog(Log log) {
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement("INSERT INTO log VALUES (?,?, DEFAULT, ?, ?)");
            stmt.setString(1, log.nameProperty().get());
            stmt.setString(2, log.detailsProperty().get());
            stmt.setInt(3, log.getCustID());
            stmt.setDate(4, java.sql.Date.valueOf(log.dateProperty().get()));
            accessService.runQueryNoResponse(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<ProductInList> getProductsToPickFrom() {
        ArrayList<ProductInList> results = new ArrayList<>();
        String query = """
        select
        	product_id,CONCAT(scent_name, ' ', product_form, ' ', volume, quantity, ' £', price)
        from
        	product;
        """;
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement(query);
            ResultSet rs = accessService.runQueryWithResponse(stmt);
            while (rs.next()) {
                results.add(new ProductInList(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public void addPurchase(int customerId, ProductInList chosenProduct, String pDate, int quantity) {
        for (int i = 0; i < quantity; i++) {
            try {
                PreparedStatement stmt = accessService.getConn().prepareStatement("insert into \"customer_product\" values (DEFAULT,?,?,?);");
                stmt.setInt(1,customerId);
                stmt.setInt(2,chosenProduct.getID());
                stmt.setDate(3,java.sql.Date.valueOf(pDate));
                accessService.runQueryNoResponse(stmt);
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public ArrayList<ProductInList> getCustPurchases(int custID) {
        ArrayList<ProductInList> results = new ArrayList<>();
        String query = """
        select
            "customer_product".pc_id, CONCAT(scent_name, ' ', product_form, ' ', volume, quantity, ' on ', purchase_date, ' for £', price)
        from
            "customer_product"
        inner join product on
            product.product_id = "customer_product".product_id
            and "customer_product".customer_id = ?;
        """;
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement(query);
            stmt.setInt(1, custID);
            ResultSet rs = accessService.runQueryWithResponse(stmt);
            while (rs.next()) {
                results.add(new ProductInList(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public String getCustPurchaseHeadline(int custID) {
        String result = "";
        String query = """
       select
        CONCAT('Bought ', COUNT(*), ' items totalling £', SUM(price))
       from
        "customer_product"
       inner join product on "customer_product".product_id = product.product_id and "customer_product".customer_id=?;
        """;
        try {
            PreparedStatement stmt = accessService.getConn().prepareStatement(query);
            stmt.setInt(1, custID);
            ResultSet rs = accessService.runQueryWithResponse(stmt);
            while (rs.next()) {
                result = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }







}
