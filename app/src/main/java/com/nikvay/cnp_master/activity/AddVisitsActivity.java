package com.nikvay.cnp_master.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Vibrator;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.nikvay.cnp_master.R;
import com.nikvay.cnp_master.adapter.MyCustomerAdapter;
import com.nikvay.cnp_master.common.ServerConstants;
import com.nikvay.cnp_master.common.VibrateOnClick;
import com.nikvay.cnp_master.helper.DatabaseHelper;
import com.nikvay.cnp_master.model.MyCustomerModel;
import com.nikvay.cnp_master.model.VisitListAddModel;
import com.nikvay.cnp_master.utils.MyCustomerResponse;
import com.nikvay.cnp_master.utils.SelectCustomerInterface;
import com.nikvay.cnp_master.utils.StaticContent;
import com.nikvay.cnp_master.utils.SuccessDialog;
import com.nikvay.cnp_master.utils.SuccessDialogClosed;
import com.nikvay.cnp_master.utils.UserData;
import com.nikvay.cnp_master.volley_support.MyVolleyPostMethod;
import com.nikvay.cnp_master.volley_support.VolleyCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddVisitsActivity extends AppCompatActivity implements VolleyCompleteListener, SelectCustomerInterface, SuccessDialogClosed {
    private AutoCompleteTextView textCustomerNameVisit,
            textContactPersonVisits,
            textPhoneVisits,
            textEmailIdVisits,
            textMessageVisit;
    private Button btnSubmit,
            btnDialogSubmit,
            btnCancelVisit;
    private EditText editHodDetail,
            editSearchC;
    private UserData userData;
    private TextView textSelectCustomerVisit;
    private MyCustomerResponse myCustomerResponse;
    private ArrayList<MyCustomerModel> arrayListC = new ArrayList<>();
    private MyCustomerAdapter adapterC;
    private Dialog selectCustomerDialog, hoDetailDialog;
    private Button btnOkDialogSC, btnCancelDialogSC, btnSave;
    private RecyclerView recyclerDialogSC;
    private String mCustomerName;
    private SuccessDialog successDialog;
    // private Spinner spinnerTypeVisit;
    private DatabaseHelper databaseHelper;
    private JSONArray jsonArrayVisitList = new JSONArray();
    ArrayList<VisitListAddModel> visitListModelArrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visits);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        localBrodcastInitialize();
        initialize();
    }

    private void localBrodcastInitialize() {
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(StaticContent.LocalBrodcastReceiverCode.CLOSE_ACTIVITY));
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(StaticContent.LocalBrodcastReceiverCode.CLOSE_ACTIVITY);
            if (message.equals(StaticContent.LocalBrodcastReceiverCode.CLOSE_ACTIVITY)) {
                finish();
            }
        }
    };

    private void initialize() {
        VibrateOnClick.vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        userData = new UserData(getApplicationContext());
        databaseHelper = new DatabaseHelper(AddVisitsActivity.this);
        successDialog = new SuccessDialog(this, true);
        hoDetailDialog = new Dialog(this);
        myCustomerResponse = new MyCustomerResponse(getApplicationContext());
        selectCustomerDialog = new Dialog(this);
        selectCustomerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        selectCustomerDialog.setContentView(R.layout.dialog_select_customer);
        selectCustomerDialog.setCancelable(false);

        // spinnerTypeVisit = findViewById(R.id.spinnerTypeVisit);
        btnOkDialogSC = selectCustomerDialog.findViewById(R.id.btnOkDialogSC);
        btnCancelDialogSC = selectCustomerDialog.findViewById(R.id.btnCancelDialogSC);
        editSearchC = selectCustomerDialog.findViewById(R.id.editSearchC);
        recyclerDialogSC = selectCustomerDialog.findViewById(R.id.recyclerDialogSC);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerDialogSC.setLayoutManager(manager);


        hoDetailDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        hoDetailDialog.setContentView(R.layout.dialog_hod_detail);
        hoDetailDialog.setCancelable(false);
        editHodDetail = hoDetailDialog.findViewById(R.id.editHodDetail);
        btnDialogSubmit = hoDetailDialog.findViewById(R.id.btnDialogSubmit);
        btnCancelVisit = hoDetailDialog.findViewById(R.id.btnCancelVisit);
        textCustomerNameVisit = findViewById(R.id.textCustomerNameVisit);
        btnSave = findViewById(R.id.btnSave);

        textContactPersonVisits = findViewById(R.id.textContactPersonVisits);
        textPhoneVisits = findViewById(R.id.textPhoneVisits);
        textEmailIdVisits = findViewById(R.id.textEmailIdVisits);
        textMessageVisit = findViewById(R.id.textMessageVisit);
        btnSubmit = findViewById(R.id.btnSubmit);
        textSelectCustomerVisit = findViewById(R.id.textSelectCustomerVisit);
        events();
    }

    private void events() {
        editSearchC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapterC.getFilter().filter(editSearchC.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnCancelVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoDetailDialog.dismiss();

            }
        });
        btnDialogSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editHodDetail.getText().toString().isEmpty()) {
                    callHODetailWS(editHodDetail.getText().toString());
                    editHodDetail.setError(null);
                } else {
                    editHodDetail.setError("Enter HO detail");
                }
            }
        });
        hoDetailDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                editHodDetail.setText("");
                editHodDetail.setError(null);

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VibrateOnClick.vibrate();

                visitListModelArrayList = databaseHelper.getAllContacts();
                databaseHelper.deleteVisit();

                customerName();
                if(visitListModelArrayList.size()!=0) {
                    callVisitWS();
                }
                else
                {
                    Toast.makeText(AddVisitsActivity.this,"Please Save Customer Then Submit",Toast.LENGTH_LONG).show();
                }
            }
        });
        textSelectCustomerVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VibrateOnClick.vibrate();
                callMyCustomerWS();
            }
        });


        btnCancelDialogSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomerName = null;
                textCustomerNameVisit.setText(null);
                selectCustomerDialog.dismiss();
            }
        });
        btnOkDialogSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCustomerName != null) {
                    textCustomerNameVisit.setText(mCustomerName);
                }
                selectCustomerDialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VibrateOnClick.vibrate();
                if (isValid()) {
                    String name = textCustomerNameVisit.getText().toString();
                    String contact_person = textContactPersonVisits.getText().toString();
                    String phone = textPhoneVisits.getText().toString();
                    String email = textEmailIdVisits.getText().toString();
                    String message = textMessageVisit.getText().toString();
                    int insert;
                    insert = databaseHelper.addVisit(new VisitListAddModel(name, contact_person, phone, email, message));
                    if (insert == 1) {
                        textCustomerNameVisit.setText("");
                        textContactPersonVisits.setText("");
                        textPhoneVisits.setText("");
                        textEmailIdVisits.setText("");
                        textMessageVisit.setText("");
                        Toast.makeText(AddVisitsActivity.this, "Save Data Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddVisitsActivity.this, "No Data Save", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    private void callHODetailWS(String msg) {
        HashMap<String, String> map = new HashMap<>();
        map.put(ServerConstants.URL, ServerConstants.serverUrl.ADD_HO_DETAIL);
        map.put("sale_person_id", userData.getUserData(StaticContent.UserData.USER_ID));
        map.put("msg", msg);
        new MyVolleyPostMethod(this, map, ServerConstants.ServiceCode.ADD_HO_DETAIL, true);
        hoDetailDialog.dismiss();
    }

    private void callMyCustomerWS() {
        HashMap<String, String> map = new HashMap<>();
        map.put(ServerConstants.URL, ServerConstants.serverUrl.MY_CUSTOMER_LIST);
        map.put("sale_person_id", userData.getUserData(StaticContent.UserData.USER_ID));
        new MyVolleyPostMethod(this, map, ServerConstants.ServiceCode.MY_CUSTOMER_LIST, true);
    }

    private void callVisitWS() {
        HashMap<String, String> map = new HashMap<>();
        map.put(ServerConstants.URL, ServerConstants.serverUrl.ADD_VISITS);
        map.put("sales_person_id", userData.getUserData(StaticContent.UserData.USER_ID));
        map.put("cust_name",  String.valueOf(customerName()));
        map.put("contact_person",String.valueOf(contact_person()));
        map.put("tel",String.valueOf(mobile_number()));
        map.put("email", String.valueOf(email()));
        map.put("msg", String.valueOf(message()));
        //  map.put("type ", spinnerTypeVisit.getSelectedItemPosition() == 1 ? "1" : "2");
        new MyVolleyPostMethod(this, map, ServerConstants.ServiceCode.ADD_VISITS, true);
    }

    private boolean isValid() {
        if (!isEmpty(textCustomerNameVisit)) {
            textCustomerNameVisit.setError("Enter Customer Name");
            textCustomerNameVisit.requestFocus();
            return false;
        } else {
            textCustomerNameVisit.setError(null);
            textCustomerNameVisit.clearFocus();
        }
        if (!isEmpty(textContactPersonVisits)) {
            textContactPersonVisits.setError("Enter Contact Person Name");
            textContactPersonVisits.requestFocus();
            return false;
        } else {
            textContactPersonVisits.setError(null);
            textContactPersonVisits.requestFocus();
        }
        if (!isEmpty(textPhoneVisits) || textPhoneVisits.getText().toString().length() != 10) {
            textPhoneVisits.setError("Enter Valid Phone Number");
            textPhoneVisits.requestFocus();
            return false;
        } else {
            textPhoneVisits.setError(null);
            textPhoneVisits.requestFocus();
        }
        if (!textEmailIdVisits.getText().toString().isEmpty()) {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(textEmailIdVisits.getText().toString()).matches()) {
                textEmailIdVisits.setError("Enter Valid Email Id");
                textEmailIdVisits.requestFocus();
                return false;
            } else {
                textEmailIdVisits.setError(null);
                textEmailIdVisits.requestFocus();
            }
        }
       /* if (spinnerTypeVisit.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select type", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        return true;
    }

    private boolean isEmpty(AutoCompleteTextView mTextView) {
        if (mTextView.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void onTaskCompleted(String response, int serviceCode) {
        switch (serviceCode) {
            case ServerConstants.ServiceCode.ADD_VISITS: {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String error_code = jsonObject.getString("error_code");
                    String msg = jsonObject.getString("msg");
                    if (error_code.equals(StaticContent.ServerResponseValidator.ERROR_CODE) && msg.equals(StaticContent.ServerResponseValidator.MSG)) {
                        CommonVisitCollectionActivity.isAdded = true;

                        successDialog.showDialog("Visit added successfully", true);

                    } else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
            case ServerConstants.ServiceCode.MY_CUSTOMER_LIST: {
                arrayListC.clear();
                arrayListC = myCustomerResponse.getCustomerResponse(response);
                if (arrayListC != null) {
                    adapterC = new MyCustomerAdapter(getApplicationContext(), arrayListC, true, this, true);
                    selectCustomerDialog.show();
                    Window window = selectCustomerDialog.getWindow();
                    window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    recyclerDialogSC.setAdapter(adapterC);
                }
                break;
            }
            case ServerConstants.ServiceCode.ADD_HO_DETAIL: {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String error_code = jsonObject.getString("error_code");
                    String msg = jsonObject.getString("msg");
                    if (error_code.equals(StaticContent.ServerResponseValidator.ERROR_CODE) && msg.equals(StaticContent.ServerResponseValidator.MSG)) {
                        CommonVisitCollectionActivity.isAdded = true;

                        successDialog.showDialog("HO Detail added successfully", true);

                    } else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.visit_HodDetail) {
            hoDetailDialog.show();
            Window window = hoDetailDialog.getWindow();
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_visit_new_customer, menu);
        return true;
    }

    @Override
    public void onTaskFailed(String response, int serviceCode) {

    }


    @Override
    public void getCustomerName(String mCustomerNamee) {
        mCustomerName = mCustomerNamee;
    }

    @Override
    public void getCustomerDetail(MyCustomerModel customerModel) {


        textContactPersonVisits.setText(customerModel.getBilling_contact_person());
        textCustomerNameVisit.setText(customerModel.getCompany_name());
        textEmailIdVisits.setText(customerModel.getEmail_id());
        textPhoneVisits.setText(customerModel.getCell_no());


    }

    @Override
    public void dialogClosed(boolean mClosed) {
        finish();
    }


    private JSONArray customerName() {
        List<String> custName = new ArrayList<>();
        for (int i = 0; i < visitListModelArrayList.size(); i++) {
            custName.add(visitListModelArrayList.get(i).getName());
        }
        JSONArray ppJsonArray = new JSONArray(custName);
       // Toast.makeText(this, ppJsonArray.toString(), Toast.LENGTH_SHORT).show();
        Log.d("NAME",ppJsonArray.toString());
        return ppJsonArray;
    }


    private JSONArray contact_person() {
        List<String> contact_person = new ArrayList<>();
        for (int i = 0; i < visitListModelArrayList.size(); i++) {
            contact_person.add(visitListModelArrayList.get(i).getContact_person());
        }
        JSONArray ppJsonArray = new JSONArray(contact_person);
       // Toast.makeText(this, ppJsonArray.toString(), Toast.LENGTH_SHORT).show();
        Log.d("CONTACT PERSON",ppJsonArray.toString());
        return ppJsonArray;
    }

    private JSONArray mobile_number() {
        List<String> contact_person = new ArrayList<>();
        for (int i = 0; i < visitListModelArrayList.size(); i++) {
            contact_person.add(visitListModelArrayList.get(i).getMobile_number());
        }
        JSONArray ppJsonArray = new JSONArray(contact_person);
       // Toast.makeText(this, ppJsonArray.toString(), Toast.LENGTH_SHORT).show();
        Log.d("MOBILE",ppJsonArray.toString());
        return ppJsonArray;
    }

    private JSONArray email() {
        List<String> email = new ArrayList<>();
        for (int i = 0; i < visitListModelArrayList.size(); i++) {
            email.add(visitListModelArrayList.get(i).getEmail());
        }
        JSONArray ppJsonArray = new JSONArray(email);
       // Toast.makeText(this, ppJsonArray.toString(), Toast.LENGTH_SHORT).show();
        Log.d("Email",ppJsonArray.toString());
        return ppJsonArray;
    }

    private JSONArray message() {
        List<String> message = new ArrayList<>();
        for (int i = 0; i < visitListModelArrayList.size(); i++) {
            message.add(visitListModelArrayList.get(i).getMessage());
        }
        JSONArray ppJsonArray = new JSONArray(message);
       // Toast.makeText(this, ppJsonArray.toString(), Toast.LENGTH_SHORT).show();
        Log.d("Message",ppJsonArray.toString());
        return ppJsonArray;
    }

    @Override
    public void onBackPressed() {
        databaseHelper.deleteVisit();
        super.onBackPressed();
    }
}
