package es.riberadeltajo.radiogroup;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rb = findViewById(R.id.radioGroup);
        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ImageView im = findViewById(R.id.iV);
                if (checkedId == R.id.radioButton2) {
                    //Toast.makeText(MainActivity.this, "Smoth Operator", );
                    im.setImageResource(R.drawable.sevilla);
                } else if (checkedId == R.id.radioButton3) {
                    im.setImageResource(R.drawable.madrid);
                } else if (checkedId == R.id.radioButton4) {
                    im.setImageResource(R.drawable.albacete);
                } else if (checkedId == R.id.radioButton5) {
                    im.setImageResource(R.drawable.barsa);
                } else {

                }
            }
        });



        }

    }
