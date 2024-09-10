package com.ilm.examenmarzo24danielmedinaalcolea;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Juego extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private SurfaceHolder holder;
    private BucleJuego bucle;
    public Bitmap explosion;

    Explosion exp;
    Bichardo elBicho;
    public final int X = 0;
    public final int Y = 1;
    public double[] posicionBicho = new double[2];
    public int movimientoBichoX = 0;
    public int movimientoBichoY = 0;
    public int velocidadBicho = 0;
    public boolean tocado = false;




    private ArrayList<Toque> toques = new ArrayList<Toque>();

    Control controles[]=new Control[3];
    boolean hayToque = false;
    boolean hayExplosion = false;
    Bitmap bicho;


   
    private static final String TAG = Juego.class.getSimpleName();

    public Juego(Activity context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
    }

    public void inicializar () {


//        exp = new Explosion(this, (float)posicionBicho[X], (float)posicionBicho[Y]);

        Random rW = new Random();
        Random rH = new Random();
        Random rVX = new Random();
        Random rVY = new Random();
        Random rV = new Random();
        velocidadBicho = rV.nextInt(20);
        movimientoBichoX = rVX.nextInt(20);
        movimientoBichoY = rVY.nextInt(20);
        bicho = BitmapFactory.decodeResource(getResources(), ListaBichos.imagenbicho);
        explosion = BitmapFactory.decodeResource(getResources(), R.drawable.animacion);
//        posicionBicho[X] = rW.nextDouble();
//        posicionBicho[Y] = rH.nextDouble();
        posicionBicho[X] = rW.nextInt(bucle.maxX - bicho.getWidth());
        posicionBicho[Y] = rH.nextInt(bucle.maxY - bicho.getHeight());
//        posicionBicho[X] = bucle.maxX/2;
//        posicionBicho[Y] = bucle.maxY/2;
//        elBicho = new Bichardo(getContext().getApplicationContext(), (float)posicionBicho[X], (float)posicionBicho[Y]);


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // se crea la superficie, creamos el game loop

        // Para interceptar los eventos de la SurfaceView
        getHolder().addCallback(this);

        // creamos el game loop
        bucle = new BucleJuego(getHolder(), this);

        setOnTouchListener(this);

        inicializar();
        // Hacer la Vista focusable para que pueda capturar eventos
        setFocusable(true);

        //comenzar el bucle
        bucle.start();

    }

    /**
     * Este método actualiza el estado del juego. Contiene la lógica del videojuego
     * generando los nuevos estados y dejando listo el sistema para un repintado.
     */
    public void actualizar() {
        Random rW = new Random();
        Random rH = new Random();

        if (!tocado) {
            posicionBicho[X] += movimientoBichoX;
            posicionBicho[Y] += movimientoBichoY;
            Random r = new Random();
            if (posicionBicho[X] < 0) {
                movimientoBichoX = movimientoBichoX * (-1);
            }
            if (posicionBicho[X]+bicho.getWidth() > bucle.maxX) {
                movimientoBichoX = movimientoBichoX * (-1);
            }
            if (posicionBicho[Y] < 0) {
                movimientoBichoY = movimientoBichoY * (-1);
            }
            if (posicionBicho[Y] + bicho.getHeight() > bucle.maxY) {
                movimientoBichoY = movimientoBichoY * (-1);
            }
            if (bucle.iteraciones % 30 == 0) {
                velocidadBicho = r.nextInt(20);
                if (movimientoBichoX < 0) {
                    movimientoBichoX = (velocidadBicho* -1);
                } else {
                    movimientoBichoX = velocidadBicho ;
                }
                if (movimientoBichoY < 0) {
                    movimientoBichoY = (velocidadBicho* -1);
                } else {
                    movimientoBichoY = velocidadBicho ;
                }

            }

        } else {
            if (exp != null) {
                exp.actualizarEstado();
                if(exp.haTerminado()) {
                    tocado = false;
                }
            }
            posicionBicho[X] = rW.nextInt(bucle.maxX - bicho.getWidth());
            posicionBicho[Y] = rH.nextInt(bucle.maxY - bicho.getHeight());
        }

        if (exp == null) {
            exp = new Explosion(this, (float)(posicionBicho[X] + bicho.getWidth()/2), (float)(posicionBicho[Y] + bicho.getHeight()/2)) ;
        }
        if (!tocado) {
            exp.setEstado(-1);
            exp.setCoordenada_x((float)(posicionBicho[X] + bicho.getWidth()/2));
            exp.setCoordenada_y((float)(posicionBicho[Y] + bicho.getHeight()/2));
        }


    }

    /**
     * Este método dibuja el siguiente paso de la animación correspondiente
     */
    public void renderizar(Canvas canvas) {

        canvas.drawColor(Color.BLACK);

        //pintar mensajes que nos ayudan
        Paint p=new Paint();
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setColor(Color.RED);
        p.setTextSize(50);
        canvas.drawText("Frame "+bucle.iteraciones+";"+"Tiempo "+bucle.tiempoTotal + " ["+bucle.maxX+","+bucle.maxY+"]",50,150,p);
        canvas.drawText("Movimiento X = "+movimientoBichoX+" Movimiento Y = "+movimientoBichoY,50,200,p);
        canvas.drawText("Estado = " + exp.getEstado(),50,250,p);


        if(!tocado) {
            canvas.drawBitmap(bicho,
                    new Rect(0,0, bicho.getWidth(), bicho.getHeight()),
                    new Rect((int)posicionBicho[X], (int)posicionBicho[Y], ((int)posicionBicho[X]+bicho.getWidth()), ((int)posicionBicho[Y]  + bicho.getHeight())),
                    p);

        } else {
            if (exp != null)
                exp.dibujar(canvas, p);

        }




    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Juego destruido!");
        // cerrar el thread y esperar que acabe
        boolean retry = true;
        while (retry) {
            try {
                bucle.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //TODO : Cambiar el estado del bicho dependiendo de las coordenadaas
        float x = event.getX(), y = event.getY();

        if (x >= posicionBicho[X] && x <= ((int)posicionBicho[X]+bicho.getWidth()) &&  y >= posicionBicho[Y] && x <= ((int)posicionBicho[Y]+bicho.getHeight())) {
            System.out.println("Hay Toque");
            tocado = true;

        }


        return false;
    }


}
