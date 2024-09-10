package es.riberadeltajo.actividad_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent segundaActividad = new Intent(this, MainActivity2.class);
        String original = "Te envio un contacto";
        Integer codigoMensaje = 1;
        int otrosValores[] = new int[] {5, 29, 3, 786, 1354, 23};
        Button b = findViewById(R.id.btnEnviar);
        Contacto carlos = new Contacto("Carlos", "Perez", 25, "673492843", false);
        Contacto juana = new Contacto("Juana", "Arcos", 24, "67353452843", true);
        Contacto francis = new Contacto("Francisco", "Perez", 23, "8979492843", true);
        francis.addFamiliares(juana);
        francis.addFamiliares(carlos);
        segundaActividad.putExtra("mensaje", original);
        segundaActividad.putExtra("codigo", codigoMensaje);
        segundaActividad.putExtra("otros_valores", otrosValores);
        segundaActividad.putExtra("contacto", francis);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(segundaActividad);
            }
        });
    }
}