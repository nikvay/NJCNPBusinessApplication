package com.nikvay.cnp_master.model;

public class CustomerSalesModule {

    String year1;
    String sale_count1;

    public CustomerSalesModule(String year1, String sales_count1) {
        this.year1=year1;
        this.sale_count1=sales_count1;
    }


    public String getYear1() {
        return year1;
    }

    public void setYear1(String year1) {
        this.year1 = year1;
    }



    public String getSale_count1() {
        return sale_count1;
    }

    public void setSale_count1(String sale_count1) {
        this.sale_count1 = sale_count1;
    }





}
