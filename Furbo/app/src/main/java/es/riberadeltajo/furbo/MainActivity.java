package es.riberadeltajo.furbo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Equipos [] misEquipos = new Equipos[5];
        misEquipos[0] = new Equipos("Real Madrid", R.drawable.realmadrid, 1);
        misEquipos[1] = new Equipos("Manchester", R.drawable.manchester, 2);
        misEquipos[2] = new Equipos("Rayo", R.drawable.rayo, 3);
        misEquipos[3] = new Equipos("Chelsea", R.drawable.chelsea, 4);
        misEquipos[4] = new Equipos("Barcelona", R.drawable.barcelona, 5);

        ListView lstEquipos = findViewById(R.id.lc);

        MiAdaptadorEquipos miAdaptadorEquipos = new MiAdaptadorEquipos(this, R.layout.fila_equipos, misEquipos);

        lstEquipos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                misEquipos[position].puntos = misEquipos[position].puntos + 1;
                miAdaptadorEquipos.notifyDataSetChanged();
//                TextView txt = view.findViewById(R.id.puntosEquipo);
//                txt.setText(misEquipos[position].puntos+"");
            }
        });
        lstEquipos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                misEquipos[position].puntos = misEquipos[position].puntos + 3;
                miAdaptadorEquipos.notifyDataSetChanged();
                return true;
            }
        });
        lstEquipos.setAdapter(new MiAdaptadorEquipos(this, R.layout.fila_equipos, misEquipos));

    }
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
       // if(item.getItemId() == R.id.settings_menu) {

        //}
        return false;
    }

    public class MiAdaptadorEquipos extends ArrayAdapter<Equipos> {
        Equipos [] misEquipos;
        public MiAdaptadorEquipos(@NonNull Context context, int resource,  @NonNull Equipos[] objects) {
            super(context, resource, objects);
            misEquipos = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position, convertView, parent);
        }

        public View crearFila (int position, View convertView, ViewGroup parent) {
            LayoutInflater miInflador = getLayoutInflater();
            View mifila = miInflador.inflate(R.layout.fila_equipos, parent, false);

            TextView txtNombre = mifila.findViewById(R.id.nombreEquipo);
            ImageView imgEquipo = mifila.findViewById(R.id.imgEquipo);
            TextView puntosEquipo = mifila.findViewById(R.id.puntosEquipo);

            txtNombre.setText(misEquipos[position].nombre);
            imgEquipo.setImageResource(misEquipos[position].imgEquipo);
            puntosEquipo.setText(misEquipos[position].puntos+"");



            return mifila;
        }


    }
}