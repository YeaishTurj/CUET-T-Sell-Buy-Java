package com.example.app.customDesign;

import java.util.List;

public class Item {
    private String ownerEmail;
    private String title;
    private int price;
    private int quantity;
    private String imagePath;
    private String description;
    private List<String> imagesPath;
    public void setItem(String title, int price, int quantity, String imagePath,String description){
        this.imagePath=imagePath;
        this.price=price;
        this.quantity=quantity;
        this.title=title;
        this.description=description;
    }
    public String getImagePath() {
        return imagePath;
    }
    public int getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getTitle() {
        return title;
    }
    public  String  getDescription(){
        return  description;
    }
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
    public String getOwnerEmail() {
        return ownerEmail;
    }
}
