package com.nikvay.cnp_master.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nikvay.cnp_master.R;
import com.nikvay.cnp_master.adapter.CustomerSalesAdapter;
import com.nikvay.cnp_master.apicallcommon.ApiClient;
import com.nikvay.cnp_master.apicallcommon.ApiInterface;
import com.nikvay.cnp_master.model.CustomerSalesModule;
import com.nikvay.cnp_master.model.SuccessModel;
import com.nikvay.cnp_master.utils.NetworkUtils;
import com.nikvay.cnp_master.utils.StaticContent;
import com.nikvay.cnp_master.utils.SuccessDialog;
import com.nikvay.cnp_master.utils.UserData;
import com.nikvay.cnp_master.volley_support.ShowLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonOustandingAndBudgetActivity extends AppCompatActivity {

    private ImageView iv_back_image_activity;
    private TextView textCustomerName, textCompanyName, textOutstanding, textBudget;
    private String cust_id, sale_person_id, TAG = getClass().getSimpleName();
    private UserData userData;
    private SuccessDialog successDialog;
    private ApiInterface apiInterface;
    ShowLoader showLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_oustanding_and_budget);


        initialize();
        events();
    }

    private void events() {
        iv_back_image_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void initialize() {
        userData = new UserData(getApplicationContext());
        showLoader = new ShowLoader(CommonOustandingAndBudgetActivity.this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        iv_back_image_activity = findViewById(R.id.iv_back_image_activity);
        textCustomerName = findViewById(R.id.textCustomerName);
        textCompanyName = findViewById(R.id.textCompanyName);
        textOutstanding = findViewById(R.id.textOutstanding);
        textBudget = findViewById(R.id.textBudget);

        sale_person_id = userData.getUserData(StaticContent.UserData.USER_ID);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            cust_id = bundle.getString(StaticContent.IntentType.CUSTOMER_ID);


            if (NetworkUtils.isNetworkAvailable(CommonOustandingAndBudgetActivity.this))
                callOutstandingAndBudget();
            else
                NetworkUtils.isNetworkNotAvailable(CommonOustandingAndBudgetActivity.this);


        }

    }

    private void callOutstandingAndBudget() {

        showLoader.showDialog();
        Call<SuccessModel> call = apiInterface.viewOustandingAndBudget(sale_person_id, cust_id);
        call.enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(Call<SuccessModel> call, Response<SuccessModel> response) {
                showLoader.dismissDialog();
                String str_response = new Gson().toJson(response.body());
                Log.e("" + TAG, "Response >>>>" + str_response);

                try {
                    if (response.isSuccessful()) {
                        SuccessModel successModel = response.body();

                        String message = null, code = null;
                        if (successModel != null) {
                            message = successModel.getMsg();
                            code = successModel.getError_code();

                            if (code.equalsIgnoreCase("1")) {
                                textCustomerName.setText(successModel.getCustomer_name());
                                textCompanyName.setText(successModel.getCompany_name());

                                String budget=successModel.getBudget();
                                String outstanding=successModel.getOutstanding_amount();
                                if(budget.equalsIgnoreCase("")||budget==null)
                                textBudget.setText("Not Updated");
                                else
                                    textBudget.setText(budget);

                                if(outstanding.equalsIgnoreCase("")||outstanding==null)
                                    textOutstanding.setText("Not Updated");
                                else
                                textOutstanding.setText(outstanding);
                            }

                        } else {
                            Toast.makeText(CommonOustandingAndBudgetActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();


                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<SuccessModel> call, Throwable t) {
                showLoader.dismissDialog();
            }
        });

    }


}
