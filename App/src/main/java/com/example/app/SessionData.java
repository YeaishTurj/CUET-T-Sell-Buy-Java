package com.example.app;

public class SessionData {
    private static String sellerEmail;
    private static String buyerEmail;
    private static int productId;
    public static String getSellerEmail() {

        return sellerEmail;
    }
    public static String getBuyerEmail() {
        return buyerEmail;
    }
    public static int getProductId(){
        return productId;
    }

    public static void setSellerEmail(String email) {

        sellerEmail = email;
    }
    public static void setBuyerEmail(String email) {
        buyerEmail = email;
    }
    public static void setProductId(int id){
        productId = id;
    }
}
