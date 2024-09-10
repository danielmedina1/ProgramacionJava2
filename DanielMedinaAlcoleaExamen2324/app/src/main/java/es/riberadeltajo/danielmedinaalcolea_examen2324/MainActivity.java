package es.riberadeltajo.danielmedinaalcolea_examen2324;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random r = new Random();
        TextView titulo = findViewById(R.id.titulo);
        TextView dir = findViewById(R.id.director);
        TextView año = findViewById(R.id.año);
        TextView rt = findViewById(R.id.raiting);
        ImageView iv = findViewById(R.id.portada);
        RatingBar rb = findViewById(R.id.ratingBar);
        Button b = findViewById(R.id.agregarPelicula);
        CardView cv = findViewById(R.id.cardView);

        peliculas.add(new Pelicula(1, "Gremnlins", "Joe Dante", 1984, R.drawable.gremlins, null, r.nextFloat()));
        peliculas.add(new Pelicula(2, "Dune", "Denis Villanueve", 2021, R.drawable.dune, null, r.nextFloat()));
        peliculas.add(new Pelicula(3, "El Marciano", "Ridley Scott", 2015, R.drawable.elmarciano, null, r.nextFloat()));
        peliculas.add(new Pelicula(4, "Encuentros en la tercera fase", "Steven Spilberg", 1978, R.drawable.encuentro_tercera_fase, null, r.nextFloat()));
        peliculas.add(new Pelicula(5, "Cazafantasmas", "Ivan Reitman", 1984, R.drawable.cazafantasmas, null, r.nextFloat()));
        peliculas.add(new Pelicula(6, "Indiana Jones", "Steven Spilberg", 1984, R.drawable.indiana_jones, null, r.nextFloat()));

        RecyclerView rv = new RecyclerView(this);
        iv.setImageResource(R.drawable.gremlins);
        recalcularRatings(peliculas);
        rb.setRating(peliculas.get(0).getRating());



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AgregarPelicula.class);
                startActivity(i);
            }
        });
    }

    private void recalcularRatings(ArrayList<Pelicula> peliculas) {
        float f [][] = getRandomRatings(peliculas.size());
        float rating = 0;
        for (int i = 0; i < peliculas.size(); i++) {
            for (int j = 0; j < 6; j++) {
                rating = rating + f[i][j];
            }
            rating = rating / 6;
            peliculas.get(i).setRating(rating);
        }
    }



    public float [][] getRandomRatings (int numPeliculas) {
        float f [][] = new float[numPeliculas][6];
        Random r = new Random();
        for (int i = 0; i < numPeliculas; i++) {
            for (int j = 0; j < 6; j++) {
                f[i][j] = r.nextFloat();
            }

        }
        return f;
    }


}