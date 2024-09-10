package es.riberadeltajo.videojuegos1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = findViewById(R.id.imageView);
        iv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.meteorito_rota));
        //iv.setOnClickListener(this);
        Button b = findViewById(R.id.botonJuego);
        animarBoton(b);

    }
    public void animarBoton(Button botonJuego){
        AnimatorSet animadorBoton = new AnimatorSet();
        //1ª animación, trasladar desde la izquierda (800 pixeles menos hasta la posición
        //inicial (0)
        ObjectAnimator trasladar=ObjectAnimator.ofFloat(botonJuego,"translationX",-800,0);
        trasladar.setDuration(5000); //duración 5 segundos
        //2ª Animación fade in de 8 segundos
        ObjectAnimator fade = ObjectAnimator.ofFloat(botonJuego, "alpha", 0f, 1f);
        fade.setDuration(8000);
        //se visualizan las dos animaciones a la vez
        animadorBoton.play(trasladar).with(fade);
        //comenzar animación
        animadorBoton.start();
    }
}