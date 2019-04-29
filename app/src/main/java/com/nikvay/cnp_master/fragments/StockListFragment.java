package com.nikvay.cnp_master.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.nikvay.cnp_master.R;
import com.nikvay.cnp_master.activity.SearchActivity;
import com.nikvay.cnp_master.adapter.ProductStockRecyclerAdapter;
import com.nikvay.cnp_master.common.ServerConstants;
import com.nikvay.cnp_master.model.CNP;
import com.nikvay.cnp_master.model.Product;
import com.nikvay.cnp_master.utils.Logs;
import com.nikvay.cnp_master.utils.ResponseUtil;
import com.nikvay.cnp_master.utils.SharedUtil;
import com.nikvay.cnp_master.volley_support.MyVolleyPostFragmentMethod;
import com.nikvay.cnp_master.volley_support.VolleyCompleteListener;

import java.util.ArrayList;
import java.util.HashMap;


public class StockListFragment extends Fragment {

    private static final String TAG = StockListFragment.class.getSimpleName();
    TextView txt_no_data_found;
    RecyclerView recyclerView;
    ArrayList<Product> products;
    ProductStockRecyclerAdapter adapter;
    SharedUtil sharedUtil;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_price_list, container, false);
        initView(view);
        //events();
        callWebServices();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            intent.putExtra("type", "stock");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView(View view) {

        sharedUtil = new SharedUtil(getActivity());
        products = new ArrayList<>();



        //Product list
        txt_no_data_found = (TextView) view.findViewById(R.id.txt_no_data_found);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearlayout = new LinearLayoutManager(getActivity());
        linearlayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearlayout);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        fab=view.findViewById(R.id.fab);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                if(!recyclerView.canScrollVertically(1))
                {
                    fab.hide();
                }
                else if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {


                if(dy==0)
                {
                    fab.show();

                }
                else if(dy>0){

                    fab.show();
                }
                else if(dy<0)
                {
                    fab.hide();
                }



                super.onScrolled(recyclerView,dx,dy);



            }
        });


    }


    private void callWebServices(){
        VolleyCompleteListener volleyCompleteListener = new VolleyCompleteListener() {
            @Override
            public void onTaskCompleted(String response, int serviceCode) {
                switch (serviceCode) {
                    case ServerConstants.ServiceCode.STOCK_LIST:
                        try {
                            Logs.showLogE(TAG,  response);
                            CNP cnp = ResponseUtil.getStockList(response);
                            if (cnp.getError_code() == 1) {

                                products.clear();
                                products.addAll(cnp.getProducts());

                                if (products.size() != 0) {
                                    fab.show();
                                    txt_no_data_found.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    adapter = new ProductStockRecyclerAdapter(getActivity(), products);
                                    recyclerView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    txt_no_data_found.setVisibility(View.VISIBLE);
                                    txt_no_data_found.setText("No data found");
                                    recyclerView.setVisibility(View.GONE);
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onTaskFailed(String response, int serviceCode) {
                switch (serviceCode) {
                    case ServerConstants.ServiceCode.STOCK_LIST:
                        Logs.showLogE(TAG, response);
                        break;
                }
            }
        };

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(ServerConstants.URL, ServerConstants.serverUrl.STOCK_LIST);
        new MyVolleyPostFragmentMethod(getActivity(), volleyCompleteListener, map, ServerConstants.ServiceCode.STOCK_LIST, true);
    }

}
