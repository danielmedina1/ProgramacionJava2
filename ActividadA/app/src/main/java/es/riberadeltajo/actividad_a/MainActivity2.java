package es.riberadeltajo.actividad_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tv = findViewById(R.id.resultados);
        Intent i = getIntent();
        String mensajeOriginal = i.getStringExtra("mensaje");
        int codigoMensaje = i.getIntExtra("codigo", 88888);
        int [] otrosValores = i.getIntArrayExtra("otros_valores");
        Contacto c = i.getParcelableExtra("contacto");

        tv.setText(c.toString());
    }
}