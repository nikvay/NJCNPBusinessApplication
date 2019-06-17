package com.nikvay.cnp_master.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nikvay.cnp_master.R;
import com.nikvay.cnp_master.activity.AddCustomerActivity;
import com.nikvay.cnp_master.activity.AddDepositActivity;
import com.nikvay.cnp_master.activity.CommonActivityForSalesAndUpToSales;
import com.nikvay.cnp_master.activity.CommonOustandingAndBudgetActivity;
import com.nikvay.cnp_master.activity.RequestQuotationActivity;
import com.nikvay.cnp_master.common.VibrateOnClick;
import com.nikvay.cnp_master.model.CustomerSalesModule;
import com.nikvay.cnp_master.model.CustomerUpToSaleModel;
import com.nikvay.cnp_master.model.MyCustomerModel;
import com.nikvay.cnp_master.utils.CustomerFieldListInterface;
import com.nikvay.cnp_master.utils.SelectCustomerInterface;
import com.nikvay.cnp_master.utils.StaticContent;

import java.util.ArrayList;

import static android.content.Context.VIBRATOR_SERVICE;


public class MyCustomerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private Context mContext;
    private ArrayList<MyCustomerModel> arrayList;
    private ArrayList<MyCustomerModel> arrayListFiltered;
    private boolean isDialog;
    private boolean isFirstLoad;
    private MyCustomerModel shareModel;
    private SelectCustomerInterface selectCustomerInterface;
    private CustomerFieldListInterface customerFieldListInterface;
    private boolean isNameSelect = false;

    public MyCustomerAdapter(Context mContext, ArrayList<MyCustomerModel> arrayList, boolean isDialog) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.arrayListFiltered = arrayList;
        this.isFirstLoad = true;
        this.isDialog = isDialog;
        this.isNameSelect = false;
        if (isDialog) {
            this.shareModel = new MyCustomerModel();
        }
    }

    public MyCustomerAdapter(Context mContext, ArrayList<MyCustomerModel> arrayList, boolean isDialog, SelectCustomerInterface selectCustomerInterface, boolean isNameSelect) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.arrayListFiltered = arrayList;
        this.isFirstLoad = true;
        this.isDialog = isDialog;
        if (isDialog) {
            this.shareModel = new MyCustomerModel();
        }
        this.selectCustomerInterface = (SelectCustomerInterface) selectCustomerInterface;
        this.isNameSelect = isNameSelect;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_my_customer, parent, false);
        return new Data(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Data hold = (Data) holder;
        if (arrayList.get(position).isSelected()) {
            hold.cardMyCustomerAdapter.setCardBackgroundColor(mContext.getResources().getColor(android.R.color.holo_green_light));
        } else {
            hold.cardMyCustomerAdapter.setCardBackgroundColor(mContext.getResources().getColor(R.color.cardview_light_background));
        }
        hold.textCustomerNameMCA.setText(arrayList.get(position).getBilling_contact_person());
        hold.textCustomerNumberMCA.setText("Customer Number:" + String.valueOf(position + 1));
        hold.textCompanyNameMCA.setText(arrayList.get(position).getCompany_name());
        hold.textPhoneNumberMCA.setText(arrayList.get(position).getTel_no());
        hold.textEmailIDMCA.setText(arrayList.get(position).getEmail_id());
        hold.textRegistrationDateMCA.setText(arrayList.get(position).getDate_of_registration());
        hold.textAddressMCA.setText(arrayList.get(position).getBilling_address());
       // hold.textOutstandingMCA.setText(arrayList.get(position).getOutstanding_amount());
       // hold.textBudget.setText(arrayList.get(position).getBudget());
        //hold.textSale.setText(arrayList.get(position).getSale());
        hold.ivEditMCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VibrateOnClick.vibrate();
                Intent intent = new Intent(mContext, AddCustomerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(StaticContent.IntentKey.CUSTOMER_DETAIL, arrayList.get(position));
                intent.putExtra(StaticContent.IntentKey.ACTIVITY_TYPE, StaticContent.IntentValue.ACTIVITY_EDIT_CUSTOMER);
                mContext.startActivity(intent);
            }
        });

        hold.iv_outstanding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, CommonOustandingAndBudgetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(StaticContent.IntentType.CUSTOMER_ID,arrayList.get(position).getC_id());
                intent.putExtra(StaticContent.ActivityType.ACTIVITY_TYPE,StaticContent.IntentValue.VIEW_UPTO_SALES);
                mContext.startActivity(intent);
            }
        });

        hold.ll_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, CommonOustandingAndBudgetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(StaticContent.IntentType.CUSTOMER_ID,arrayList.get(position).getC_id());
                intent.putExtra(StaticContent.ActivityType.ACTIVITY_TYPE,StaticContent.IntentValue.VIEW_UPTO_SALES);
                mContext.startActivity(intent);

            }
        });

        hold.textSale.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mContext, CommonActivityForSalesAndUpToSales.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(StaticContent.IntentType.CUSTOMER_ID,arrayList.get(position).getC_id());
                intent.putExtra(StaticContent.ActivityType.ACTIVITY_TYPE,StaticContent.IntentValue.VIEW_SALES);
                mContext.startActivity(intent);

            }
        });

        hold.textUptoSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, CommonActivityForSalesAndUpToSales.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(StaticContent.IntentType.CUSTOMER_ID,arrayList.get(position).getC_id());
                intent.putExtra(StaticContent.ActivityType.ACTIVITY_TYPE,StaticContent.IntentValue.VIEW_UPTO_SALES);
                mContext.startActivity(intent);
            }
        });
        if (isDialog) {
            hold.ll_sales.setVisibility(View.GONE);
            hold.ll_UptoSales.setVisibility(View.GONE);
            hold.ll_outstanding.setVisibility(View.GONE);
            hold.ll_outstanding.setVisibility(View.GONE);
            hold.ll_budget.setVisibility(View.GONE);
            hold.cardMyCustomerAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isNameSelect) {
                        if (!arrayList.get(position).isSelected()) {
                            for (int i = 0; i < arrayList.size(); i++) {
                                arrayList.get(i).setSelected(false);
                            }
                            arrayList.get(position).setSelected(true);
                            shareModel = arrayList.get(position);
                            RequestQuotationActivity.customerModel = shareModel;
                        } else {
                            RequestQuotationActivity.customerModel = null;
                            arrayList.get(position).setSelected(false);
                        }
                        notifyDataSetChanged();
                    } else {
                        if (!arrayList.get(position).isSelected()) {
                            for (int i = 0; i < arrayList.size(); i++) {
                                arrayList.get(i).setSelected(false);
                            }
                            arrayList.get(position).setSelected(true);
                            selectCustomerInterface.getCustomerName(arrayList.get(position).getBilling_contact_person());
                            selectCustomerInterface.getCustomerDetail(arrayList.get(position));
                        } else {
                            selectCustomerInterface.getCustomerName(null);
                            arrayList.get(position).setSelected(false);
                        }
                        notifyDataSetChanged();
                    }
                }
            });
        }
        if (isFirstLoad) {
            isFirstLoad = false;
            setScaleAnimation(hold.itemView);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Data extends RecyclerView.ViewHolder {
        private TextView textCustomerNameMCA,
                textCompanyNameMCA,
                textPhoneNumberMCA,
                textEmailIDMCA,
                textRegistrationDateMCA,
                textAddressMCA,
                textOutstandingMCA,
                textCustomerNumberMCA,
                textBudget,
                textEditMCA;
        private CardView cardMyCustomerAdapter;
        private LinearLayout ll_sales,ll_UptoSales,ll_outstanding,ll_budget;
        private ImageView textSale, ivEditMCA, textUptoSale,iv_outstanding,iv_Budget;

        public Data(View itemView) {
            super(itemView);
            VibrateOnClick.vibrator = (Vibrator) mContext.getSystemService(VIBRATOR_SERVICE);
            textCustomerNameMCA = itemView.findViewById(R.id.textCustomerNameMCA);
            textCustomerNumberMCA = itemView.findViewById(R.id.textCustomerNumberMCA);
            textCompanyNameMCA = itemView.findViewById(R.id.textCompanyNameMCA);
            textPhoneNumberMCA = itemView.findViewById(R.id.textPhoneNumberMCA);
            textEmailIDMCA = itemView.findViewById(R.id.textEmailIDMCA);
            textRegistrationDateMCA = itemView.findViewById(R.id.textRegistrationDateMCA);
            textAddressMCA = itemView.findViewById(R.id.textAddressMCA);
            ivEditMCA = itemView.findViewById(R.id.ivEditMCA);
            cardMyCustomerAdapter = itemView.findViewById(R.id.cardMyCustomerAdapter);
            iv_outstanding = itemView.findViewById(R.id.iv_outstanding);
            iv_Budget = itemView.findViewById(R.id.iv_Budget);
            textSale = itemView.findViewById(R.id.textSale);
            ll_sales = itemView.findViewById(R.id.ll_sales);
            ll_sales = itemView.findViewById(R.id.ll_sales);
            textUptoSale = itemView.findViewById(R.id.textUptoSale);
            ll_UptoSales = itemView.findViewById(R.id.ll_UptoSales);
            ll_outstanding = itemView.findViewById(R.id.ll_outstanding);
            ll_budget = itemView.findViewById(R.id.ll_budget);

        }
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(300);
        view.startAnimation(anim);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().replaceAll("\\s", "").toLowerCase().trim();
                if (charString.isEmpty() || charSequence.equals("")) {
                    arrayList = arrayListFiltered;
                } else {
                    ArrayList<MyCustomerModel> filteredList = new ArrayList<>();
                    for (int i = 0; i < arrayList.size(); i++) {
                        String contact_person = arrayList.get(i).getBilling_contact_person().replaceAll("\\s", "").toLowerCase().trim();
                        String address = arrayList.get(i).getAddress().toLowerCase().replaceAll("\\s", "").toLowerCase().trim();
                        String company_name = arrayList.get(i).getCompany_name().replaceAll("\\s", "").toLowerCase().trim();
                        if (contact_person.contains(charString) || address.contains(charString) || company_name.contains(charString)) {
                            filteredList.add(arrayList.get(i));
                        }
                    }
                    if (filteredList.size() > 0) {
                        arrayList = filteredList;
                    } else {
                        arrayList = arrayListFiltered;
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrayList = (ArrayList<MyCustomerModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}
