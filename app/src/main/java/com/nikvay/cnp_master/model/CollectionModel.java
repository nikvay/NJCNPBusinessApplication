package com.nikvay.cnp_master.model;

public class CollectionModel {
     String id;
    String sales_person_id;
    String cust_name;
    String amount;
    String bill_no;
    String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSales_person_id() {
        return sales_person_id;
    }

    public void setSales_person_id(String sales_person_id) {
        this.sales_person_id = sales_person_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
