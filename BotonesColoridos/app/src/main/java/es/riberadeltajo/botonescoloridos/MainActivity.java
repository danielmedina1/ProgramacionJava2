package es.riberadeltajo.botonescoloridos;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout g = findViewById(R.id.gridLayout);
        g.setRowCount(6);
        g.setColumnCount(3);
        Random r = new Random();
        for (int i = 0; i < 18; i++) {
            Button b = new Button(this);
            if(i == 17) {
                b.setText("Reset");
            } else {
                b.setText("Btn "+i);
            }
            b.setBackgroundColor(Color.argb(128, r.nextInt(), r.nextInt(), r.nextInt()));
            g.addView(b);
        }
    }
}