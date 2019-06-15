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

            }
        });

        hold.ll_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        hold.textSale.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               /* VibrateOnClick.vibrate();
                final Dialog selectSales = new Dialog(mContext);
                selectSales.requestWindowFeature(Window.FEATURE_NO_TITLE);
                selectSales.setContentView(R.layout.dialog_customer_sales);
                selectSales.setCancelable(false);


                TextView textCustomerNameMCADialog = selectSales.findViewById(R.id.textCustomerNameMCADialog);
                TextView textCompanyNameMCA = selectSales.findViewById(R.id.textCompanyNameMCADialog);

                TextView textSalesYear1 = selectSales.findViewById(R.id.textSalesYear1);
                TextView textSalesCount1 = selectSales.findViewById(R.id.textSalesCount1);
                TextView textSalesYear2 = selectSales.findViewById(R.id.textSalesYear2);
                TextView textSalesCount2 = selectSales.findViewById(R.id.textSalesCount2);
                TextView textSalesYear3 = selectSales.findViewById(R.id.textSalesYear3);
                TextView textSalesCount3 = selectSales.findViewById(R.id.textSalesCount3);
                ImageView textShowMessage = selectSales.findViewById(R.id.textShowMessage);


                ImageView textClose = selectSales.findViewById(R.id.textClose);


                String year1 = String.valueOf(arrayList.get(position).getYear1() == null ? "" : arrayList.get(position).getYear1());
                String year2 = String.valueOf(arrayList.get(position).getYear2() == null ? "" : arrayList.get(position).getYear2());
                String year3 = String.valueOf(arrayList.get(position).getYear3() == null ? "" : arrayList.get(position).getYear3());
                String sale_count1 = String.valueOf(arrayList.get(position).getSale_count1() == null ? "" : arrayList.get(position).getSale_count1());
                String sale_count2 = String.valueOf(arrayList.get(position).getSale_count2() == null ? "" : arrayList.get(position).getSale_count2());
                String sale_count3 = String.valueOf(arrayList.get(position).getSale_count3() == null ? "" : arrayList.get(position).getSale_count3());


                String hideYear1 = "NO", hideYear2 = "NO", hideYear3 = "NO";

                textCustomerNameMCADialog.setText(arrayList.get(position).getBilling_contact_person());
                textCompanyNameMCA.setText(arrayList.get(position).getCompany_name());


                if ((!year1.equalsIgnoreCase("")) || (!sale_count1.equalsIgnoreCase(""))) {
                    textSalesCount1.setText(sale_count1);
                    textSalesYear1.setText(year1);
                } else {
                    hideYear1 = "YES";
                    textSalesCount1.setVisibility(View.GONE);
                    textSalesYear1.setVisibility(View.GONE);
                }
                if ((!year2.equalsIgnoreCase("")) || (!sale_count2.equalsIgnoreCase(""))) {
                    textSalesCount2.setText(sale_count2);
                    textSalesYear2.setText(year2);
                } else {
                    hideYear2 = "YES";
                    textSalesCount2.setVisibility(View.GONE);
                    textSalesYear2.setVisibility(View.GONE);
                }
                if ((!year3.equalsIgnoreCase("")) || (!sale_count3.equalsIgnoreCase(""))) {
                    textSalesCount3.setText(sale_count3);
                    textSalesYear3.setText(year3);
                } else {
                    hideYear3 = "YES";
                    textSalesCount3.setVisibility(View.GONE);
                    textSalesYear3.setVisibility(View.GONE);
                }
                if (hideYear1.equalsIgnoreCase("YES") && hideYear2.equalsIgnoreCase("YES") && hideYear3.equalsIgnoreCase("YES")) {
                    textShowMessage.setVisibility(View.VISIBLE);
                }

                selectSales.show();


                textClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectSales.dismiss();
                    }
                });*/



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
                VibrateOnClick.vibrate();




               /* textCustomerNameUptoSaleDialog.setText(arrayList.get(position).getBilling_contact_person());
                textCompanyNameUptoSaleDialog.setText(arrayList.get(position).getCompany_name());


                String month1 = String.valueOf(arrayList.get(position).getMonth1() == null ? "" : arrayList.get(position).getMonth1());
                String month1_count = String.valueOf(arrayList.get(position).getMonth1_count() == null ? "" : arrayList.get(position).getMonth1_count());

                String month2 = String.valueOf(arrayList.get(position).getMonth2() == null ? "" : arrayList.get(position).getMonth2());
                String month2_count = String.valueOf(arrayList.get(position).getMonth2_count() == null ? "" : arrayList.get(position).getMonth2_count());

                String month3 = String.valueOf(arrayList.get(position).getMonth3() == null ? "" : arrayList.get(position).getMonth3());
                String month3_count = String.valueOf(arrayList.get(position).getMonth3_count() == null ? "" : arrayList.get(position).getMonth3_count());

                String month4 = String.valueOf(arrayList.get(position).getMonth4() == null ? "" : arrayList.get(position).getMonth4());
                String month4_count = String.valueOf(arrayList.get(position).getMonth4_count() == null ? "" : arrayList.get(position).getMonth4_count());

                String month5 = String.valueOf(arrayList.get(position).getMonth5() == null ? "" : arrayList.get(position).getMonth5());
                String month5_count = String.valueOf(arrayList.get(position).getMonth5_count() == null ? "" : arrayList.get(position).getMonth5_count());

                String month6 = String.valueOf(arrayList.get(position).getMonth6() == null ? "" : arrayList.get(position).getMonth6());
                String month6_count = String.valueOf(arrayList.get(position).getMonth6_count() == null ? "" : arrayList.get(position).getMonth6_count());

                String month7 = String.valueOf(arrayList.get(position).getMonth7() == null ? "" : arrayList.get(position).getMonth7());
                String month7_count = String.valueOf(arrayList.get(position).getMonth7_count() == null ? "" : arrayList.get(position).getMonth7_count());

                String month8 = String.valueOf(arrayList.get(position).getMonth7() == null ? "" : arrayList.get(position).getMonth7());
                String month8_count = String.valueOf(arrayList.get(position).getMonth8_count() == null ? "" : arrayList.get(position).getMonth8_count());

                String month9 = String.valueOf(arrayList.get(position).getMonth9() == null ? "" : arrayList.get(position).getMonth9());
                String month9_count = String.valueOf(arrayList.get(position).getMonth9_count() == null ? "" : arrayList.get(position).getMonth9_count());

                String month10 = String.valueOf(arrayList.get(position).getMonth10() == null ? "" : arrayList.get(position).getMonth10());
                String month10_count = String.valueOf(arrayList.get(position).getMonth10_count() == null ? "" : arrayList.get(position).getMonth10_count());

                String month11 = String.valueOf(arrayList.get(position).getMonth11() == null ? "" : arrayList.get(position).getMonth11());
                String month11_count = String.valueOf(arrayList.get(position).getMonth11_count() == null ? "" : arrayList.get(position).getMonth11_count());

                String month12 = String.valueOf(arrayList.get(position).getMonth12() == null ? "" : arrayList.get(position).getMonth12());
                String month12_count = String.valueOf(arrayList.get(position).getMonth12_count() == null ? "" : arrayList.get(position).getMonth12_count());

                if (!month1.equalsIgnoreCase("")) {
                    CustomerUpToSaleModel customerUpToSaleModel1 = new CustomerUpToSaleModel(month1, month1_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel1);
                }
                if (!month2.equalsIgnoreCase("")) {
                    CustomerUpToSaleModel customerUpToSaleModel2 = new CustomerUpToSaleModel(month2, month2_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel2);
                }
                if (!month3.equalsIgnoreCase("")) {
                    CustomerUpToSaleModel customerUpToSaleModel3 = new CustomerUpToSaleModel(month3, month3_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel3);

                }
                if (!month4.equalsIgnoreCase("")) {
                    CustomerUpToSaleModel customerUpToSaleModel4 = new CustomerUpToSaleModel(month4, month4_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel4);
                }
                if(!month5.equalsIgnoreCase("")) {
                    CustomerUpToSaleModel customerUpToSaleModel5 = new CustomerUpToSaleModel(month5, month5_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel5);
                }
                if(!month6.equalsIgnoreCase("")) {
                    CustomerUpToSaleModel customerUpToSaleModel6 = new CustomerUpToSaleModel(month6, month6_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel6);
                }
                if(!month7.equalsIgnoreCase("")) {

                    CustomerUpToSaleModel customerUpToSaleModel7 = new CustomerUpToSaleModel(month7, month7_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel7);
                }
                if(!month8.equalsIgnoreCase("")) {
                    CustomerUpToSaleModel customerUpToSaleModel8 = new CustomerUpToSaleModel(month8, month8_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel8);
                }
                if(!month9.equalsIgnoreCase("")) {
                    CustomerUpToSaleModel customerUpToSaleModel9 = new CustomerUpToSaleModel(month9, month9_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel9);
                }
                if(!month10.equalsIgnoreCase("")) {
                    CustomerUpToSaleModel customerUpToSaleModel10 = new CustomerUpToSaleModel(month10, month10_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel10);
                }
                if(!month11.equalsIgnoreCase("")) {
                    CustomerUpToSaleModel customerUpToSaleModel11 = new CustomerUpToSaleModel(month11, month11_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel11);
                }
                if(!month12.equalsIgnoreCase("")) {
                    CustomerUpToSaleModel customerUpToSaleModel12 = new CustomerUpToSaleModel(month12, month12_count);
                    customerUpToSaleModelArrayList.add(customerUpToSaleModel12);
                }


                if(customerUpToSaleModelArrayList.size()==0)
                {
                    iv_list_not_found_.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }


              //  textTotalSale.setText("10");

                customerUptosaleAdapter = new CustomerUptosaleAdapter(mContext, customerUpToSaleModelArrayList);
                recyclerView.setAdapter(customerUptosaleAdapter);
                customerUptosaleAdapter.notifyDataSetChanged();
                selectUptoSales.show();


                textClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectUptoSales.dismiss();
                    }
                });
*/

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
