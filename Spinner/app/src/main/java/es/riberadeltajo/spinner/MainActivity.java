package es.riberadeltajo.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String [] misAlumnos = new String[] {"Elia", "Rodrigo", "Carlos", "Marcos", "Fco. Javier", "Andrea", "Sergio", "Paco", "Pepe", "Pipo"};

        Spinner miSpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, misAlumnos);
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(miAdaptador);

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                //if (tv.get)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}