package com.nikvay.cnp_master.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikvay.cnp_master.R;
import com.nikvay.cnp_master.model.CustomerUpToSaleModel;

import java.util.ArrayList;

public class CustomerUptosaleAdapter extends RecyclerView.Adapter<CustomerUptosaleAdapter.MyViewHolder> {

    private  Context mContext;
    private  ArrayList<CustomerUpToSaleModel> customerUpToSaleModelArrayList;
    public CustomerUptosaleAdapter(Context mContext, ArrayList<CustomerUpToSaleModel> customerUpToSaleModelArrayList) {
        this.mContext=mContext;
        this.customerUpToSaleModelArrayList=customerUpToSaleModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_item_csale_adapter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final CustomerUpToSaleModel customerUpToSaleModel=customerUpToSaleModelArrayList.get(position);

        holder.textMonth.setText(customerUpToSaleModel.getMonth());
        holder.textSales.setText(customerUpToSaleModel.getSales());

    }

    @Override
    public int getItemCount() {
        return customerUpToSaleModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textMonth,textSales;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textMonth=itemView.findViewById(R.id.textMonth);
            textSales=itemView.findViewById(R.id.textSales);
        }
    }
}
