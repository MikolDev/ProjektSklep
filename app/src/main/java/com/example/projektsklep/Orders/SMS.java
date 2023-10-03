package com.example.projektsklep.Orders;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Klasa odpowiada za wysyłanie SMSa.
 */
public class SMS {
    String phoneNumber;
    String message;
    Context context;
    SmsManager smsManager;

    public SMS(String phoneNumber, String message, Context context) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.context = context;
        this.smsManager = SmsManager.getDefault();
    }

    /**
     * Metoda wysyła SMSa.
     */
    public void sendSms() {
        if (checkPermission(Manifest.permission.SEND_SMS, 1)) {
            if (checkData()) {
                smsManager.sendTextMessage(
                        phoneNumber,
                        null,
                        message,
                        null,
                        null
                );
                Log.v("SMS", "SMS sent");
            }
        }
        Log.v("SMS","test" + phoneNumber + " " + message);
    }

    /**
     * Metoda sprawdza, czy na urządzeniu jest zezwolenie na wysyłanie SMSów przez aplikację.
     *
     * @param permission permisja
     * @param requestCode
     * @return permisja, jeśli jest
     */
    private boolean checkPermission(String permission, int requestCode) {
        if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions((Activity) context, new String[] { permission }, requestCode);
        }

        return context.checkSelfPermission(permission) != PackageManager.PERMISSION_DENIED;
    }


    /**
     * Metoda sprawdza, czy numer telefonu i wiadomość nie są puste.
     *
     * @return true, jeśli nie są puste, w przeciwnym wypadku false
     */
    public boolean checkData() {
        if (phoneNumber.equals("") || message.equals("")) return false;
        return true;
    }

}
