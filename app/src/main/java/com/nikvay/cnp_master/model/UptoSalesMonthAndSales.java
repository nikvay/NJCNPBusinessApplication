package com.nikvay.cnp_master.model;

public class UptoSalesMonthAndSales {

    String month;
    String sales;

    public UptoSalesMonthAndSales(String month, String sales) {
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
}
