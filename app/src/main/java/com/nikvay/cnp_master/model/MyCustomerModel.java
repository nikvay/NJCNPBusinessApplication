package com.nikvay.cnp_master.model;

import java.io.Serializable;

public class MyCustomerModel implements Serializable {
    String c_id;
    String sale_person_id;
    String date_of_registration;
    String company_name;
    String billing_address;
    String location;
    String state;
    String pincode;
    String billing_contact_person;
    String tel_no;
    String cell_no;
    String email_id;
    String billing_GST_no;
    String packing_charges;
    String insurance_charges;
    String term_of_payment;
    String freight_terms;
    String discount;




    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    String budget;
    String sale;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getOutstanding_amount() {
        return outstanding_amount;
    }

    public void setOutstanding_amount(String outstanding_amount) {
        this.outstanding_amount = outstanding_amount;
    }

    String outstanding_amount;
    boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String address;

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getSale_person_id() {
        return sale_person_id;
    }

    public void setSale_person_id(String sale_person_id) {
        this.sale_person_id = sale_person_id;
    }

    public String getDate_of_registration() {
        return date_of_registration;
    }

    public void setDate_of_registration(String date_of_registration) {
        this.date_of_registration = date_of_registration;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getBilling_contact_person() {
        return billing_contact_person;
    }

    public void setBilling_contact_person(String billing_contact_person) {
        this.billing_contact_person = billing_contact_person;
    }

    public String getTel_no() {
        return tel_no;
    }

    public void setTel_no(String tel_no) {
        this.tel_no = tel_no;
    }

    public String getCell_no() {
        return cell_no;
    }

    public void setCell_no(String cell_no) {
        this.cell_no = cell_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getBilling_GST_no() {
        return billing_GST_no;
    }

    public void setBilling_GST_no(String billing_GST_no) {
        this.billing_GST_no = billing_GST_no;
    }

    public String getPacking_charges() {
        return packing_charges;
    }

    public void setPacking_charges(String packing_charges) {
        this.packing_charges = packing_charges;
    }

    public String getInsurance_charges() {
        return insurance_charges;
    }

    public void setInsurance_charges(String insurance_charges) {
        this.insurance_charges = insurance_charges;
    }

    public String getTerm_of_payment() {
        return term_of_payment;
    }

    public void setTerm_of_payment(String term_of_payment) {
        this.term_of_payment = term_of_payment;
    }

    public String getFreight_terms() {
        return freight_terms;
    }

    public void setFreight_terms(String freight_terms) {
        this.freight_terms = freight_terms;
    }
}
