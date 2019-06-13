package com.nikvay.cnp_master.model;

public class VisitListAddModel {
    private String name;
    private String contact_person;
    private String mobile_number;
    private String email;
    private String message;

    public VisitListAddModel(String name, String contact_person, String mobile_number, String email, String message) {
        this.name = name;
        this.contact_person = contact_person;
        this.mobile_number = mobile_number;
        this.email = email;
        this.message = message;
    }

    public VisitListAddModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


