package com.nikvay.cnp_master.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nikvay.cnp_master.R;
import com.nikvay.cnp_master.activity.LoginActivity;

public class Logout_Application {
    private Context mContext;
    private Dialog dialog;
    private TextView txt_cancel,txt_logout;


    public Logout_Application(Context mContext) {
        this.mContext = mContext;
        this.dialog = new Dialog(mContext);
        this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.dialog.setContentView(R.layout.dialog_logout);
        this.txt_cancel =dialog.findViewById(R.id.txt_cancel);
        this.txt_logout =dialog.findViewById(R.id.txt_logout);
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    public void showDialog() {
        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        txt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedUtil sharedUtil = new SharedUtil(mContext);
                boolean isromved = sharedUtil.clearShareUtils();
                if (isromved) {
                    Toast.makeText(mContext, "Logout successfull", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(i);
                    ((Activity)mContext).finish();
                }
            }
        });

    }

}
