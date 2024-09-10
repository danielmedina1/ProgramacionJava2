package es.riberadeltajo.multiidioma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int op = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton ib = findViewById(R.id.botonLlamada);


        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = findViewById(R.id.estadoLlamada);
                ImageView ww = findViewById(R.id.walter);
                if (op == 0) {
                    ib.setImageResource(R.drawable.br);
                    tv.setText("Llamando a Walter White...");
                    ww.setImageResource(R.drawable.walter11);
                    op = 1;
                } else if (op == 1) {
                    ib.setImageResource(R.drawable.bv);
                    tv.setText("Llamar a Walter White");
                    ww.setImageResource(R.drawable.walter00);
                    op = 0;
                }

            }
        });
    }
}