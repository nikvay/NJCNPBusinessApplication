package com.nikvay.cnp_master.common;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class VibrateOnClick {
    public static Vibrator vibrator;

    public static void vibrate()
    {
        if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
    } else {
        vibrator.vibrate(200);
    }
    }
}
