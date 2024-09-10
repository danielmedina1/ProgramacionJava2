package es.riberadeltajo.unidad3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String [] provincias = new String[] {"Toledo", "Ciudad Real", "Albacete", "Cuenca", "Murcia", "Torre Molinos"};
        Spinner miSpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, provincias);
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(miAdaptador);

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent resultado = new Intent();
                resultado.putExtra("ciudad_elegida", provincias[position]);
                resultado.putExtra("posicion_ciudad_elegida", provincias[position]);
                setResult(RESULTADO_SELECCION_PROVINCIA, resultado);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Ribera del Tajo", "MainActivity.onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Ribera del Tajo", "MainActivity.onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Ribera del Tajo", "MainActivity.onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Ribera del Tajo", "MainActivity.onStart()");
    }

    @Override
    protected void onDestroy() {
        Log.d("Ribera del Tajo", "MainActivity.onStart()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Ribera del Tajo", "MainActivity.onStart()");
    }
}