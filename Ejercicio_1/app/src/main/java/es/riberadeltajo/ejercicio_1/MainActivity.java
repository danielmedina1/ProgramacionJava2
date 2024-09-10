package es.riberadeltajo.ejercicio_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.sum);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText n1 = findViewById(R.id.num1);
                EditText n2 = findViewById(R.id.num2);
                int a= Integer.parseInt(n1.getText().toString());
                int a2= Integer.parseInt(n2.getText().toString());
                int r = a + a2;

                TextView rtd = findViewById(R.id.result);
                rtd.setText(""+r);
            }
        });

        Button b2 = findViewById(R.id.resta);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText n1 = findViewById(R.id.num1);
                EditText n2 = findViewById(R.id.num2);
                int a= Integer.parseInt(n1.getText().toString());
                int a2= Integer.parseInt(n2.getText().toString());
                int r = a - a2;

                TextView rtd = findViewById(R.id.result);
                rtd.setText(""+r);
            }
        });

        Button b3 = findViewById(R.id.mult);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText n1 = findViewById(R.id.num1);
                EditText n2 = findViewById(R.id.num2);
                int a= Integer.parseInt(n1.getText().toString());
                int a2= Integer.parseInt(n2.getText().toString());
                int r = a * a2;

                TextView rtd = findViewById(R.id.result);
                rtd.setText(""+r);
            }
        });

        Button b4 = findViewById(R.id.div);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText n1 = findViewById(R.id.num1);
                EditText n2 = findViewById(R.id.num2);
                int a= Integer.parseInt(n1.getText().toString());
                int a2= Integer.parseInt(n2.getText().toString());
                int r = a / a2;

                TextView rtd = findViewById(R.id.result);
                rtd.setText(""+r);
            }
        });
    }
}