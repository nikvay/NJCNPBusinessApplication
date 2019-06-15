package com.nikvay.cnp_master.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SuccessModel {
    private  String error_code;
    private  String msg;
    String company_name;
    String customer_name;

    @SerializedName("up_to_sale")
    ArrayList<CustomerUpToSaleModel> customerUpToSaleModelArrayList;


    @SerializedName("old_sale")
    ArrayList<CustomerSalesModel> customerSalesModelArrayList;



    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public ArrayList<CustomerUpToSaleModel> getCustomerUpToSaleModelArrayList() {
        return customerUpToSaleModelArrayList;
    }

    public void setCustomerUpToSaleModelArrayList(ArrayList<CustomerUpToSaleModel> customerUpToSaleModelArrayList) {
        this.customerUpToSaleModelArrayList = customerUpToSaleModelArrayList;
    }

    public ArrayList<CustomerSalesModel> getCustomerSalesModelArrayList() {
        return customerSalesModelArrayList;
    }

    public void setCustomerSalesModelArrayList(ArrayList<CustomerSalesModel> customerSalesModelArrayList) {
        this.customerSalesModelArrayList = customerSalesModelArrayList;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;




    }
}
