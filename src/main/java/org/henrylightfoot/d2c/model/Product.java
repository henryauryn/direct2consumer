package org.henrylightfoot.d2c.model;

import javafx.beans.property.*;

public class Product {
    private StringProperty scentName = new SimpleStringProperty(this, "scentName", null);
    private ReadOnlyDoubleWrapper price = new ReadOnlyDoubleWrapper(this, "price", 0);
    private ReadOnlyDoubleWrapper volume = new ReadOnlyDoubleWrapper(this, "volume", 0);
    private StringProperty quantity = new SimpleStringProperty(this, "quantity", null);
    private ReadOnlyIntegerWrapper productID = new ReadOnlyIntegerWrapper(this, "productID", 0);
    private StringProperty revenue = new SimpleStringProperty(this, "revenue", null);

    public Product(String scentName, double price, double volume, String quantity, int productID, String revenue) {
        this.scentName.set(scentName);
        this.price.set(price);
        this.volume.set(volume);
        this.quantity.set(quantity);
        this.productID.set(productID);
        this.revenue.set(revenue);
    }
    public StringProperty scentNameProperty() {
        return scentName;
    }
    public ReadOnlyDoubleProperty priceProperty() {
        return price;
    }
    public ReadOnlyDoubleProperty volumeProperty() {
        return volume;
    }
    public StringProperty quantityProperty() {
        return quantity;
    }
    public ReadOnlyIntegerProperty productIDProperty() {
        return productID;
    }
    public StringProperty revenueProperty() {
        return revenue;
    }
}
