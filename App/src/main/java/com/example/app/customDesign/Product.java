package com.example.app.customDesign;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class Product {
    public int productId;
    public String sellerEmail;
    public String productTitle;
    public int quantity;
    public double price;
    public String description;
    private byte[] mainImg=null;
    private byte[] addImg1=null;
    private byte[] addImg2=null;
    private byte[] addImg3=null;
    private byte[] addImg4=null;

    public Product(int productId, String sellerEmail, String productTitle, int quantity, double price,
                   String description, byte[] mainImg, byte[] addImg1, byte[] addImg2, byte[] addImg3, byte[] addImg4) {
        this.productId = productId;
        this.sellerEmail = sellerEmail;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.mainImg = mainImg;
        this.addImg1 = addImg1;
        this.addImg2 = addImg2;
        this.addImg3 = addImg3;
        this.addImg4 = addImg4;
    }
    public List<Image> getImageList() {
        List<Image> imageList = new ArrayList<>();

        if (mainImg != null && mainImg.length > 0) {
            Image image = new Image(new ByteArrayInputStream(mainImg));
            if (!image.isError()) { // Ensures the image is valid
                imageList.add(image);
            }
        }

        if (addImg1 != null && addImg1.length > 0) {
            Image image = new Image(new ByteArrayInputStream(addImg1));
            if (!image.isError()) {
                imageList.add(image);
            }
        }

        if (addImg2 != null && addImg2.length > 0) {
            Image image = new Image(new ByteArrayInputStream(addImg2));
            if (!image.isError()) {
                imageList.add(image);
            }
        }

        if (addImg3 != null && addImg3.length > 0) {
            Image image = new Image(new ByteArrayInputStream(addImg3));
            if (!image.isError()) {
                imageList.add(image);
            }
        }
        if (addImg4 != null && addImg4.length > 0) {
            Image image = new Image(new ByteArrayInputStream(addImg4));
            if (!image.isError()) {
                imageList.add(image);
            }
        }
        return imageList;
    }


    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public String getProductTitle() {
        return productTitle;
    }
}

