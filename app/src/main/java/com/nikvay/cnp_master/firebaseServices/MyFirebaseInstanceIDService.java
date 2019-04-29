package com.nikvay.cnp_master.firebaseServices;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.nikvay.cnp_master.utils.SharedUtil;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
  
    private static final String TAG = "FirebaseIDService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        SharedUtil.putDeviceToken(getApplicationContext(),refreshedToken);

    }
}
