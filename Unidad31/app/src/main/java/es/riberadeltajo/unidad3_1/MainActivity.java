package es.riberadeltajo.unidad3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d("Ribera del Tajo", "MainActivity.onStart()");
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                arrancaActividadLogin();

            }
        });
    }

    private void arrancaActividadLogin() {
        Intent intentLogin = new Intent(this, LoginActivity.class);

        startActivity(intentLogin);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Ribera del Tajo", "MainActivity.onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Ribera del Tajo", "MainActivity.onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Ribera del Tajo", "MainActivity.onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Ribera del Tajo", "MainActivity.onRestart()");
    }

    @Override
    protected void onDestroy() {
        Log.d("Ribera del Tajo", "MainActivity.onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Ribera del Tajo", "MainActivity.onPause()");
    }

}