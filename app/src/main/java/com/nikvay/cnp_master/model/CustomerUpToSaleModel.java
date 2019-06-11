package com.nikvay.cnp_master.model;

public class CustomerUpToSaleModel {
    String month;
    String sales;
    String total_sales;


    public CustomerUpToSaleModel(String month, String sales) {
        this.month = month;
        this.sales = sales;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(String total_sales) {
        this.total_sales = total_sales;
    }
}
