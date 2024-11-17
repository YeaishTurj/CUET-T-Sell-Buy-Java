package com.example.app;

public class SessionData {
    private static String sellerEmail;
    private static String buyerEmail;
    public static String getSellerEmail() {

        return sellerEmail;
    }
    public static String getBuyerEmail() {
        return buyerEmail;
    }

    public static void setSellerEmail(String email) {

        sellerEmail = email;
    }
    public static void setBuyerEmail(String email) {
        buyerEmail = email;
    }
}
