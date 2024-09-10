package es.riberadeltajo.contentprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.EditText;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    public final int PERMISO_CONTACTOS = 1;
    EditText nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.edFiltro);
        ConstraintLayout cl = findViewById(R.id.cl);
        cl.post(new Runnable() {
            @Override
            public void run() {
                if (checkSelfPermission("android.permission.READ_CONTACTS") != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{"android.permission.READ_CONTACTS"}, PERMISO_CONTACTOS);

                } else {
                    cargarContacto(nombre.getText().toString());
                }
            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISO_CONTACTOS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                cargarContacto("");
            }
        }
    }

    private void cargarContacto(String texto) {
        //Log.d("RIBERA DEL TAJO");

        //SELECT _ID, HAS_PHONE_NUMBER, DISPLAYU_NAME FROM ContactsContract.Contracts.CONTENT_URI
        //WHERE DISPLAY_NAME LIKE %nombre%
        //Selection_args=like %nombre%
        ExecutorService executor = Executors.newSingleThreadExecutor();

        String projection[] = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};
        String selection = ContactsContract.Contacts.DISPLAY_NAME + " like ?";
        String selection_args[] = {"%" + texto + "%"};

        ContentResolver miCr = getContentResolver();
        Cursor miCursor = miCr.query(ContactsContract.Contacts.CONTENT_URI, projection, selection, selection_args, null);
        Log.d("Ribera del Tajo", "Cursor Cargado");
        if (miCursor.getCount() > 0) {
            while (miCursor.moveToNext()) {
                long id = Long.parseLong(miCursor.getString(0));
                String nombre = miCursor.getString(1);
                int tiene_telefono = Integer.parseInt(miCursor.getString(2));
                ListaContactos.contactos.add(new Contacto(nombre, tiene_telefono, id));
            }
            ListaContactos.miAdaptador.notifyDataSetChanged();
        }

    }
}