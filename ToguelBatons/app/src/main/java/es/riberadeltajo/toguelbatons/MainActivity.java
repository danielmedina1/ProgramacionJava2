package es.riberadeltajo.toguelbatons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int indice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int codImg [] = {R.drawable.walter00, R.drawable.walter01, R.drawable.walter10, R.drawable.walter11};
        Switch s1 = findViewById(R.id.cambioSombrero);
        Switch s2 = findViewById(R.id.cambioBigote);

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                
            }
        });






    }
}