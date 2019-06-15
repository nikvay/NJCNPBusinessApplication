package com.nikvay.cnp_master.apicallcommon;


import com.nikvay.cnp_master.model.CustomerUpToSaleModel;
import com.nikvay.cnp_master.model.SuccessModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST(EndApi.ADD_COLLECTION)
    @FormUrlEncoded
    Call<SuccessModel> addCollection(@Field("sales_person_id") String sales_person_id,
                                     @Field("cust_name") String cust_name,
                                     @Field("company_name") String company_name,
                                     @Field("amount") String amount,
                                     @Field("bill_no") String bill_no,
                                     @Field("date") String date,
                                     @Field("remark") String remark,
                                     @Field("photo") String photo);


    @POST(EndApi.COLLECTION_DEPOSIT)
    @FormUrlEncoded
    Call<SuccessModel> addCollectionDeposit(@Field("sales_person_id") String sales_person_id,
                                            @Field("description") String description,
                                            @Field("collection_id") String collection_id,
                                            @Field("photo") String photo);

    @POST(EndApi.VIEW_UPTO_SALE)
    @FormUrlEncoded
    Call<SuccessModel> viewUptoSales(@Field("sale_person_id") String sale_person_id,
                                     @Field("cust_id") String cust_id);
}
