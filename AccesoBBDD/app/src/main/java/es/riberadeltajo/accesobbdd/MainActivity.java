package es.riberadeltajo.accesobbdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteClosable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    android.database.sqlite.SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = findViewById(R.id.lc);
        ArrayList <Discos> discos = new ArrayList<Discos>() ;

        db = openOrCreateDatabase("MisDiscos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS misDiscos(Grupo VARCHAR, Disco VARCHAR);");

        ImageButton ad = findViewById(R.id.btnAñadir);
        ImageButton del = findViewById(R.id.btnBorrar);
        EditText edGrupo = findViewById(R.id.etGrupo);
        EditText edDisco = findViewById(R.id.etDisco);
        //actualizarLista();


        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edGrupo.getText().toString() != null && edDisco.getText().toString() != null) {
                    db.execSQL("INSERT INTO MisDiscos (Grupo, Disco) VALUES ('"+edGrupo.getText().toString()+"', '"+edDisco.getText().toString()+"');");
                }
                //actualizarLista();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edGrupo.getText().toString() != null && edDisco.getText().toString() != null) {
                    db.execSQL("DELETE FROM MisDiscos WHERE Grupo LIKE '"+edGrupo.getText().toString()+"' AND Disco LIKE '"+edDisco.getText().toString()+"';");
                }
                //actualizarLista();
            }

        });
        MiAdaptadorDiscos miadaptadordiscos = new MiAdaptadorDiscos(this, R.layout.discos_xml, discos);
        lv.setAdapter(new MiAdaptadorDiscos(this, R.layout.discos_xml, discos));
    }

    public class MiAdaptadorDiscos extends ArrayAdapter<Discos> {
        ArrayList <Discos> discos = new ArrayList<>() ;

        public void actLista() {

            Cursor c =  db.rawQuery("SELECT * FROM MisDiscos;", null);
            if (c.getCount() == 0) {
                discos.add(new Discos("", ""));
            } else {
                while (c.moveToNext()) {
                    discos.add(new Discos(c.getString(0), c.getString(1)));
                }
            }
        }

        public MiAdaptadorDiscos(@NonNull Context context, int resource, @NonNull List<Discos> objects) {
            super(context, resource, objects);
            discos = (ArrayList<Discos>) objects;
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
            View mifila = miInflador.inflate(R.layout.discos_xml, parent, false);

            TextView tv = mifila.findViewById(R.id.txt);
            TextView tv2 = mifila.findViewById(R.id.txt2);

            tv.setText(discos.get(position).getGrupo());
            tv2.setText(discos.get(position).getTitulo());





            return mifila;
        }


    }

   /** private void actualizarLista() {
        Cursor c =  db.rawQuery("SELECT * FROM MisDiscos;", null);
        if (c.getCount() == 0) {
            discos.add("No hay discos")
        }


    }*/


    public final class SQLiteDatabase extends SQLiteClosable {

        @Override
        protected void onAllReferencesReleased() {



        }
    }
}