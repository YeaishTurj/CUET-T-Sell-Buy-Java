package com.example.app.customDesign;

public class Owner {
    private String name;
    private String wAppNumber;
    private String phoneNumber;
    private String fbLink;
    private String emailId;
    public Owner(String name,String wAppNumber,String phoneNumber,String fbLink,String emailId){
        this.name=name;
        this.wAppNumber=wAppNumber;
        this.phoneNumber=phoneNumber;
        this.fbLink=fbLink;
        this.emailId=emailId;
    }
    public String getName() {
        return name;
    }
    public String getEmailId() {
        return emailId;
    }
    public String getFbLink() {
        return fbLink;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getWAppNumber() {
        return wAppNumber;
    }
}
