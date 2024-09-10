package es.riberadeltajo.cronometro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout cl = findViewById(R.id.constraintLayout);
        cl.setBackgroundColor(Color.GREEN);
        TextView txtCont = findViewById(R.id.parote);
        CountDownTimer c = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtCont.setText("Iniciando el cronómetro");
            }

            @Override
            public void onFinish() {
                txtCont.setText("Se acabó el tiempo");
                cl.setBackgroundColor(Color.RED);
            }
        };
        c.start();
        Button stop = findViewById(R.id.btnStop);
        //stop.setOnClickListener(view);
    }
}