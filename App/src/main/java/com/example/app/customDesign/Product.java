package com.example.app.customDesign;

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

    public Product(int productId, String sellerEmail, String productTitle, int quantity, double price, String description){
        this.description=description;
        this.productId=productId;
        this.productTitle=productTitle;
        this.sellerEmail=sellerEmail;
        this.quantity=quantity;
        this.price=price;
    }

    public void setMainImg(byte[] mainImg) {
        this.mainImg = mainImg;
    }
    public void setAddImg1(byte[] addImg1) {
        this.addImg1 = addImg1;
    }
    public void setAddImg2(byte[] addImg2) {
        this.addImg2 = addImg2;
    }
    public void setAddImg3(byte[] addImg3) {
        this.addImg3 = addImg3;
    }
    public void setAddImg4(byte[] addImg4) {
        this.addImg4 = addImg4;
    }

    public byte[] getMainImg() {
        return mainImg;
    }

    public byte[] getAddImg1() {
        return addImg1;
    }

    public byte[] getAddImg2() {
        return addImg2;
    }

    public byte[] getAddImg3() {
        return addImg3;
    }

    public byte[] getAddImg4() {
        return addImg4;
    }
}

