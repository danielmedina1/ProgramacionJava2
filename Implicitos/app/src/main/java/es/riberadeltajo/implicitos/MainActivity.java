package es.riberadeltajo.implicitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import es.riberadeltajo.implicitos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        binding.btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("maito"+binding.email.getText().toString()));

                String [] destinatarios = new String[] {binding.email.getText().toString()};

                i.putExtra(Intent.EXTRA_EMAIL, destinatarios);
                i.putExtra(Intent.EXTRA_SUBJECT, "Mensaje Automatico");
                i.putExtra(Intent.EXTRA_TEXT, "Buenos dias");
                i.setType("message/rfc882");
                startActivity(i);

            }
        });


    }
}