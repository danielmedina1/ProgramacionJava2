package es.riberadeltajo.sacardialogo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogoS.OnRespuestaS {

    public void setRespuestaUsuario (boolean respuestaUsuario) {
        if (respuestaUsuario) {
            Toast.makeText(this, "Ai ba la ostia", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ai ba la torta", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.btn);
        b.setOnClickListener(view ->{
                DialogoS miDialogo = new DialogoS();
                miDialogo.show(getSupportFragmentManager(),"Dialogo Nuevo");
    });


        ListView lstAlumnos = findViewById(R.id.lv);
        String [] misAlumnos = new String[] {"Elia", "Rodrigo", "Carlos", "Marcos", "Fco. Javier", "Andrea", "Sergio", "Paco", "Pepe", "Pipo"};

        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, misAlumnos);

        lstAlumnos.setAdapter(miAdaptador);
        lstAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView a = (TextView) view;
                //if (a.getText().toString().startsWith("P")) {
                //    Toast.makeText(MainActivity.this, "Has sacado un 7", Toast.LENGTH_SHORT).show();
                //}
                SparseBooleanArray sba = lstAlumnos.getCheckedItemPositions();
                if (sba != null) {
                    for (int i = 0; i < sba.size(); i++) {
                        Log.d("Ribera del tajo", lstAlumnos.getItemAtPosition(sba.keyAt(i)).toString());
                    }
                }
            }
        });

    }

    @Override
    public void OnRespuesta(boolean respuesta) {

        setRespuestaUsuario(respuesta);
    }




}