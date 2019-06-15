package com.nikvay.cnp_master.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikvay.cnp_master.R;

public class CommonOustandingAndBudgetActivity extends AppCompatActivity {

    private ImageView iv_back_image_activity;
    private TextView  textCustomerName,textCompanyName,textOutstanding,textBudget;
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
        iv_back_image_activity=findViewById(R.id.iv_back_image_activity);
        textCustomerName=findViewById(R.id.textCustomerName);
        textCompanyName=findViewById(R.id.textCompanyName);
        textOutstanding=findViewById(R.id.textOutstanding);
        textBudget=findViewById(R.id.textBudget);

    }
}
