package es.riberadeltajo.pruebapermisos;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int SOLICITUD_PERMISO_SMS = 0;


    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    enviarSMS();
                } else {
                    Toast.makeText(this, "Cagaste Master", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.btn);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                 if (wifi.isWifiEnabled()) {
                 Toast.makeText(getApplicationContext(), "Wifi Activado", Toast.LENGTH_SHORT).show();
                 } else {
                 Toast.makeText(getApplicationContext(), "Wifi Desctivado", Toast.LENGTH_SHORT).show();
                 }*/
                /** Antes de enviar un sms:
                 *  1. Comprobar que tenemos permisos
                 *  2. Si tenemos permisos -> ENVIAR
                 *  3. Si mo tenemos permisos -> SOLICITAR
                 * */


                if(checkSelfPermission("android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED) {
                    //No tengo permisos, hay que solicitarlos
                    requestPermissions(new String[] {"android.permission.SEND_SMS"}, SOLICITUD_PERMISO_SMS);

                } else {
                    //Tengo permisos, se envia
                    enviarSMS();
                }




            }


        });





    }

    private void enviarSMS() {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("66657565", null, "¿Quedamos a comer?", null, null);
    }
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == SOLICITUD_PERMISO_SMS) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED ) {
                enviarSMS();
            } else {

            }
        }

    }
}