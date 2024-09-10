package es.riberadeltajo.checkboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    CheckBox cajas[] = new CheckBox[8] ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int [] checkBoxesIds = new int[] {R.id.chk1, R.id.chk2, R.id.chk3, R.id.chk4, R.id.chk5, R.id.chk6, R.id.chk7, R.id.chk8};

        for (int i = 0; i < 8; i++) {
            cajas[i] = findViewById(checkBoxesIds[i]);
            cajas[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    TextView tv = findViewById(R.id.resultado);
                    int r = 0;
                    for (int j = 0; j < 8; j++) {
                        if (cajas[j].isChecked()) {
                            r = (int) (r+Math.pow(2, j));
                        }
                        tv.setText(""+r);
                    }

                }
            });
        }

    }



}