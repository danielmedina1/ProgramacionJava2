package es.riberadeltajo.receptorbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MiReceptorSMS extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECIVED")) {
            leerSMS(context);
        }
    }

    private void leerSMS(Context context) {

    }
}