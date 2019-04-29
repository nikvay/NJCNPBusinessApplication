package com.nikvay.cnp_master.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikvay.cnp_master.R;
import com.nikvay.cnp_master.model.CustomerSalesModule;

import java.util.ArrayList;

public class CustomerSalesAdapter extends RecyclerView.Adapter<CustomerSalesAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<CustomerSalesModule> customerSalesModuleArrayList;


    public CustomerSalesAdapter(Context mContext, ArrayList<CustomerSalesModule> customerSalesModuleArrayList) {
        this.mContext=mContext;
        this.customerSalesModuleArrayList=customerSalesModuleArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_customer_sales,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
           final CustomerSalesModule customerSalesModule=customerSalesModuleArrayList.get(position);

           holder.textSalesYear.setText(customerSalesModule.getYear1());
           holder.textSalesCount.setText(customerSalesModule.getSale_count1());

    }

    @Override
    public int getItemCount() {
        return customerSalesModuleArrayList==null?0:customerSalesModuleArrayList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        private TextView textSalesYear,textSalesCount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textSalesCount=itemView.findViewById(R.id.textSalesCount);
            textSalesYear=itemView.findViewById(R.id.textSalesYear);
        }
    }
}
