package com.nikvay.cnp_master.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikvay.cnp_master.R;
import com.nikvay.cnp_master.activity.AddDepositActivity;
import com.nikvay.cnp_master.model.CollectionModel;
import com.nikvay.cnp_master.utils.StaticContent;

import java.util.ArrayList;


/**
 * Created by Param3 on 2/24/2016.
 */

public class ViewCollectionAdapter extends RecyclerView.Adapter<ViewCollectionAdapter.MyDataHolder> {

    ArrayList<CollectionModel> arrayList;
    Context context;
    public ViewCollectionAdapter(Context context, ArrayList<CollectionModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList==null?0:arrayList.size();
    }

    @Override
    public MyDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_collection_list, parent, false);
        MyDataHolder myDataHolder = new MyDataHolder(v);
        return myDataHolder;

    }

    @Override
    public void onBindViewHolder(final MyDataHolder holder, final int position) {
        final MyDataHolder hold = (MyDataHolder) holder;
        hold.textCustomerNameVC.setText(arrayList.get(position).getCust_name());
        hold.textCountVC.setText(String.valueOf(position + 1));
        hold.textAmountVC.setText(arrayList.get(position).getAmount());
        hold.textDateVC.setText(arrayList.get(position).getDate());
        hold.textBillNumberVC.setText(arrayList.get(position).getBill_no());
        hold.textCompanyNameVC.setText(arrayList.get(position).getCompany_name());
        String is_deposit_amount=arrayList.get(position).getIs_deposit_amount();


        if(is_deposit_amount.equalsIgnoreCase("1"))
        {
            hold.textDeposit.setText("Yes");
            hold.iv_deposit.setVisibility(View.GONE);
        }


        hold.iv_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AddDepositActivity.class);
                intent.putExtra(StaticContent.IntentType.COLLECTION_NUMBER,arrayList.get(position).getId());
                context.startActivity(intent);
            }
        });

        setScaleAnimation(holder.itemView);
    }

    public static class MyDataHolder extends RecyclerView.ViewHolder {

        TextView textCountVC,
                textCustomerNameVC,
                textAmountVC,
                textBillNumberVC,
                textDateVC,
                textDeposit,
                textCompanyNameVC;
        ImageView iv_deposit;

        public MyDataHolder(View v) {
            super(v);
            textCountVC =  v.findViewById(R.id.textCountVC);
            textCustomerNameVC =  v.findViewById(R.id.textCustomerNameVC);
            textAmountVC =  v.findViewById(R.id.textAmountVC);
            textBillNumberVC =  v.findViewById(R.id.textBillNumberVC);
            textDateVC = v.findViewById(R.id.textDateVC);
            textCompanyNameVC = v.findViewById(R.id.textCompanyNameVC);
            iv_deposit = v.findViewById(R.id.iv_deposit);
            textDeposit = v.findViewById(R.id.textDeposit);

        }
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(300);
        view.startAnimation(anim);
    }
}
