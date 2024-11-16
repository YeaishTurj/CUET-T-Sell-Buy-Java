package com.example.app;

public class SessionData {
    private static String sellerEmail;

    public static String getSellerEmail() {
        return sellerEmail;
    }

    public static void setSellerEmail(String email) {
        sellerEmail = email;
    }
}
