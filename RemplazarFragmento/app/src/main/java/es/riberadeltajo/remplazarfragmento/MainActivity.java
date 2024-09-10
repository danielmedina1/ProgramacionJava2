package es.riberadeltajo.remplazarfragmento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences misPreferencias = getSharedPreferences("Perfs_empleados", MODE_PRIVATE);

        String nombre = misPreferencias.getString("nombre", "Joe");
        String empresas = misPreferencias.getString("empresa", "Ribera del Tajo");
        String email = misPreferencias.getString("email", "xxx@xxx.xxx");
        int edad = misPreferencias.getInt("edad", 18);
        float sueldo = misPreferencias.getFloat("sueldo", 35000);

        EditText edNombre = findViewById(R.id.edNombre);
        EditText edEmpresa = findViewById(R.id.edEmpresa);
        EditText edEmail = findViewById(R.id.edEmail);
        EditText edEdad = findViewById(R.id.edEdad);
        EditText edSueldo = findViewById(R.id.edSueldo);

        edNombre.setText(nombre);
        edEdad.setText(edad+"");
        edEmail.setText(email);
        edEmpresa.setText(empresas);
        edSueldo.setText(sueldo+"");

        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences misPreferencias = getSharedPreferences("Perfs_empleados", MODE_PRIVATE);
                SharedPreferences.Editor miEditor = misPreferencias.edit();
                miEditor.putString("nombre", edNombre.getText().toString());
                miEditor.putString("empresa", edEmpresa.getText().toString());
                miEditor.putString("email", edEmail.getText().toString());

                try {
                    miEditor.putInt("edad", Integer.parseInt(edEdad.getText().toString()));
                    miEditor.putFloat("sueldo", Float.parseFloat(edSueldo.getText().toString()));
                } catch (Exception e) {
                    miEditor.commit();
                }


            }
        });
        Button btnSiguiente = findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);
            }
        });
    }
}