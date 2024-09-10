package es.riberadeltajo.autocmp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] paises = new String[] {"Albania", "Alemania", "Andorra", "España","Eslovaquia", "Etiopia"};

        ArrayAdapter<String> miAdaptadorPais = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, paises
        );
        AutoCompleteTextView edPaises = findViewById(R.id.maiTV);
        edPaises.setAdapter(miAdaptadorPais);
        edPaises.setThreshold(1);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                Toast.makeText(getApplicationContext(), btn.getText().toString()+"-"+btn.getId(), Toast.LENGTH_LONG);
            }
        };

        LinearLayout l = findViewById(R.id.linearlayout);
        for (int i = 0; i < 3; i++) {
            Button b = new Button(this);
            b.setText("Boton "+i); 
            b.setId(View.generateViewId());
            b.setOnClickListener(listener);
            l.addView(b);

        }
    }
}