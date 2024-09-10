package es.riberadeltajo.numerosprimos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.calPrimo);
        ArrayList<Integer> ap= new ArrayList<>();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText n = findViewById(R.id.n);
                 int num = Integer.parseInt(n.getText().toString());
                 int num2 = 0;
                for (int i = 2; i < num; i++) {
                    if (num2%i == 0) {

                    }
                }
            }
        });
    }

}