package es.riberadeltajo.miprimerproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("mensaje_depurativo","OnCreate");
        /**TextView mitexto=findViewById(R.id.btn);
        mitexto.setText("Actividad Creada");*/
        Button btnPulsame=findViewById(R.id.btn);
        btnPulsame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView miTexto = findViewById(R.id.btn);
                miTexto.setText("Pulsao");
                Toast.makeText(getApplicationContext()),T "Lo has pulsao", Toast.LENGTH_SHORT.show();
            }
        });

    }
    protected void onStart(Bundle savedInstanceState) {
        super.onStart();
        Log.d("mensaje_depurativo1","OnStart");
    }
    protected void onResume(Bundle savedInstanceState) {
        super.onResume();
        Log.d("mensaje_depurativo2","OnResume");
    }
    protected void onPause(Bundle savedInstanceState) {
        super.onPause();
        Log.d("mensaje_depurativo3","OnPause");
    }
    protected void onStop(Bundle savedInstanceState) {
        super.onStop();
        Log.d("mensaje_depurativo4","OnStop");
    }
    protected void onDestroy(Bundle savedInstanceState) {
        super.onDestroy();
        Log.d("mensaje_depurativo5","OnDestroy");
    }
}