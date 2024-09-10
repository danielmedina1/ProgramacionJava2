package es.riberadeltajo.intentoargo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();

        String action = i.getAction();

        ImageView iv = findViewById(R.id.iv);



        try {
            Uri uri = i.getParcelableExtra(Intent.EXTRA_STREAM);
            if (uri!=null) {
                InputStream  is = getContentResolver().openInputStream(uri);
                iv.setImageBitmap(BitmapFactory.decodeStream(is));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }




        /**
        if (action.equals(Intent.ACTION_SEND)) {
            String textoRecibido = i.getStringExtra(Intent.EXTRA_TEXT);
            Toast.makeText(this, "HOLA: "+textoRecibido, Toast.LENGTH_SHORT).show();
        } else {

        }*/
    }
}