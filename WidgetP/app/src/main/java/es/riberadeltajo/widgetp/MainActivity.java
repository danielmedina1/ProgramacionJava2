package es.riberadeltajo.widgetp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Ciudades [] miArrayCiudades = new Ciudades[5];
        miArrayCiudades[0] = new Ciudades("Toledo", "La ciudad imperial", R.drawable.toledo);
        miArrayCiudades[1] = new Ciudades("Ciudad Real", "Una ciudad muy bonita", R.drawable.ciudadreal);
        miArrayCiudades[2] = new Ciudades("Cuenca", "La ciudad de las casas colgantes", R.drawable.cuenca);
        miArrayCiudades[3] = new Ciudades("Guadalajara", "La ciudad bonita 2", R.drawable.guadalajara);
        miArrayCiudades[4] = new Ciudades("Albacete", "Mucha feria no se", R.drawable.albacete);
        ListView lstCiudades  =findViewById(R.id.lc);
        lstCiudades.setAdapter(new MiAdaptadorCiudades(this, R.layout.fila_ciudad, miArrayCiudades));
    }
    public class MiAdaptadorCiudades extends ArrayAdapter<Ciudades>  {
        Ciudades [] misObjetos;
        public MiAdaptadorCiudades(@NonNull Context context, int resource, @NonNull Ciudades[] objects) {
            super(context, resource, objects);
            misObjetos = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFile(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFile(position, convertView, parent);
        }

        public View crearFile (int position, View convertView, ViewGroup parent) {
            //1. Inflamos el xml con nuestra vista personalizada
            LayoutInflater miInflador = getLayoutInflater();
            View mifila = miInflador.inflate(R.layout.fila_ciudad, parent, false);
            //2. Encontramos referencias a los objetos de cada una
            TextView txtNombre = mifila.findViewById(R.id.nombreCiudad);
            TextView txtDescripcion = mifila.findViewById(R.id.descripcionCiudad);
            ImageView imgCiudad = mifila.findViewById(R.id.imgCiudad);
            //3. Rellenar
            txtNombre.setText(misObjetos[position].nombre);
            txtDescripcion.setText(misObjetos[position].descripcion);
            imgCiudad.setImageResource(misObjetos[position].imagenes);
            return mifila;
        }
    }



}