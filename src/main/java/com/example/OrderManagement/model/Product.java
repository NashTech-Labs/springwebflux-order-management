package com.example.OrderManagement.model;

public class Product {

    int productId;
    String item;
    long price;

    public Product(int productId, String item, long price) {
        this.productId = productId;
        this.item = item;
        this.price = price;
    }

    public Product() {}

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{"
                + "productId="
                + productId
                + ", item='"
                + item
                + '\''
                + ", price="
                + price
                + '}';
    }
}
