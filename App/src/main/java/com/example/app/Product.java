package com.example.app;

public class Product {
    private int serialNumber; // Added for serial numbers
    private int productId;
    private String productTitle;
    private int quantity;
    private double price;

    public Product(int serialNumber, int productId, String productTitle, int quantity, double price) {
        this.serialNumber = serialNumber;
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters for all attributes
    public int getSerialNumber() {
        return serialNumber;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
