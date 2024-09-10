package com.ilm.examenmarzo24danielmedinaalcolea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.incJuego);
        ListaBichos.listaBichos = inicializarLista();
        ListaBichos.contextobicho = getApplicationContext();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActividadJuego.class);
                startActivity(i);
            }
        });
    }



    private ArrayList<Bicho> inicializarLista() {
        ArrayList<Bicho> listaBichos = new ArrayList<>();
        listaBichos.add(new Bicho(1, "Mariquita", R.drawable.bicho1));
        listaBichos.add(new Bicho(2, "Mariposa", R.drawable.bicho2));
        listaBichos.add(new Bicho(3, "Libelula", R.drawable.bicho3));
        listaBichos.add(new Bicho(4, "Avispa", R.drawable.bicho4));
        return listaBichos;
    }
}