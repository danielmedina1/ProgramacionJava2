package es.riberadeltajo.danielmedinaalcolea_examen2324;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AgregarPelicula extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pelicula);

        EditText nom = findViewById(R.id.nombrePelicula);
        EditText dp = findViewById(R.id.directorPelicula);
        EditText año = findViewById(R.id.añoPelicula);
        EditText cod = findViewById(R.id.codigoPelicula);
        ImageView iv = findViewById(R.id.imgnPortada);
        Button b = findViewById(R.id.btnPortada);
        ImageButton ib = findViewById(R.id.guardarPelicula);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AgregarPelicula.class);
                startActivity(i);
            }
        });

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int c = cod.getText();
                //String nombre = nom.getText();
                //Pelicula p = new Pelicula(cod.getText(), nom.getText(), dp.getText(), año.getText(), iv.getImageAlpha(), null, 0.0f);
                Pelicula pe = getIntent().getParcelableExtra("");
            }
        });


    }
}