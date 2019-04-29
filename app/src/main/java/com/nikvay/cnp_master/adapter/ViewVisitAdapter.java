package com.nikvay.cnp_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.nikvay.cnp_master.R;
import com.nikvay.cnp_master.model.VisitListModel;

import java.util.ArrayList;


/**
 * Created by Param3 on 2/24/2016.
 */

public class ViewVisitAdapter extends RecyclerView.Adapter<ViewVisitAdapter.MyDataHolder> {

    ArrayList<VisitListModel> arrayList;
    Context context;

    public ViewVisitAdapter(Context context, ArrayList<VisitListModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList==null?0:arrayList.size();
    }

    @Override
    public MyDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_visit_list, parent, false);
        MyDataHolder myDataHolder = new MyDataHolder(v);
        return myDataHolder;
    }

    @Override
    public void onBindViewHolder(final MyDataHolder holder, final int position) {
        final MyDataHolder hold = (MyDataHolder) holder;
        hold.textCountVA.setText(String.valueOf(position+1));
        hold.textCustomerNameVA.setText(arrayList.get(position).getCust_name());
        hold.textContactPerVA.setText(arrayList.get(position).getContact_person());
        hold.textPhoneNumberVA.setText(arrayList.get(position).getTel());
        hold.textEmailIDVA.setText(arrayList.get(position).getEmail());
        hold.textRegistrationDateVA.setText(arrayList.get(position).getDate());
        hold.textMessageVA.setText(arrayList.get(position).getMsg());

        setScaleAnimation(holder.itemView);
    }

    public static class MyDataHolder extends RecyclerView.ViewHolder {

        TextView textCountVA,
                textCustomerNameVA,
                textContactPerVA,
                textPhoneNumberVA,
                textEmailIDVA,
                textRegistrationDateVA,
                textMessageVA;

        public MyDataHolder(View v) {
            super(v);
            textCountVA =  v.findViewById(R.id.textCountVA);
            textCustomerNameVA =  v.findViewById(R.id.textCustomerNameVA);
            textContactPerVA =  v.findViewById(R.id.textContactPerVA);
            textPhoneNumberVA =  v.findViewById(R.id.textPhoneNumberVA);
            textEmailIDVA =  v.findViewById(R.id.textEmailIDVA);
            textRegistrationDateVA = v.findViewById(R.id.textRegistrationDateVA);
            textMessageVA = v.findViewById(R.id.textMessageVA);
        }
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(300);
        view.startAnimation(anim);
    }
}
