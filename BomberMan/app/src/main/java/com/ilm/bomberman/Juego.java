package com.ilm.bomberman;

import android.app.Activity;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.Timer;

public class Juego extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private SurfaceHolder holder;
    private BucleJuego bucle;
    private final int X = 0;
    private final int Y = 1;
    private ArrayList<Toque> toques= new ArrayList<>();
    private Control[] controles= new Control[4];
    private final int IZQUIERDA=0;
    private final int DERECHA=1;
    private final int ARRIBA=2;
    private final int ABAJO=3;
    private  boolean hayToque;

    private float maxX, maxY, mapa_w, mapa_h, bomber_w, bomber_h;

    private Bitmap bm;
    float posicionBomber[] = new float[2];
    float posBMW, posBMH;
    float estadoBomber = 0;
    private Bitmap mapa;
    float posicionMapa[] = new float[2];

   
    private static final String TAG = Juego.class.getSimpleName();

    public Juego(Activity context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void inicializar() {
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.bomberman_spritesv2);
        bomber_h = bm.getHeight();
        bomber_w = bm.getWidth();

        mapa = BitmapFactory.decodeResource(getResources(), R.drawable.mapa);
        mapa_h = mapa.getHeight();
        mapa_w = mapa.getWidth();

        Canvas c = getHolder().lockCanvas();
        maxX = c.getWidth();
        maxY = c.getHeight();
        getHolder().unlockCanvasAndPost(c);

        posicionMapa[X] = 0;
        posicionMapa[Y] = (maxY - mapa_h)/2;

        posicionBomber[X] = 60 + mapa_w*0.05f;
        posicionBomber[Y] = posicionMapa[Y] + mapa_h*0.7f - posBMH;


        //H -> 458 a 36
        posBMH = (float)(bm.getHeight());
        //W -> 240 a 22
        posBMW = (float)(bm.getWidth()/12);

        //flecha_izda
        controles[IZQUIERDA]=new Control(getContext(),0, posicionMapa[Y]+mapa_h+10);
        controles[IZQUIERDA].cargar( R.drawable.flecha_izda);
        controles[IZQUIERDA].nombre="IZQUIERDA";
        //flecha_derecha
        controles[DERECHA]=new Control(getContext(),
                controles[0].ancho()+controles[0].coordenada_x+5,controles[0].coordenada_y);
        controles[DERECHA].cargar(R.drawable.flecha_dcha);
        controles[DERECHA].nombre="DERECHA";
        //flecha_arriba
        controles[ARRIBA]=new Control(getContext(),controles[0].ancho()+controles[0].coordenada_x-140, posicionMapa[Y]+mapa_h-130);
        controles[ARRIBA].cargar( R.drawable.flecha_up);
        controles[ARRIBA].nombre="ARRIBA";
        //flecha_abajo
        controles[ABAJO]=new Control(getContext(),
                controles[0].ancho()+controles[0].coordenada_x-140,posicionMapa[Y]+mapa_h+150);
        controles[ABAJO].cargar(R.drawable.flecha_down);
        controles[ABAJO].nombre="ABAJO";
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // se crea la superficie, creamos el game loop

        // Para interceptar los eventos de la SurfaceView
        getHolder().addCallback(this);

        inicializar();

        // creamos el game loop
        bucle = new BucleJuego(getHolder(), this);

        setOnTouchListener(this);

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
        if (controles[DERECHA].pulsado) {
            if (estadoBomber > 3) {
                estadoBomber = 0;
            }
            estadoBomber++;

            if (estadoBomber >= 3) {
                estadoBomber = 1;
            }
            posicionBomber[X] += 10;
        }
        if (controles[IZQUIERDA].pulsado) {
            if (estadoBomber < 6 && estadoBomber > 9) {
                estadoBomber = 6;
            }
            estadoBomber++;

            if (estadoBomber >= 9) {
                estadoBomber = 7;
            }
            posicionBomber[X] -= 10;
        }
        if (controles[ARRIBA].pulsado) {
            if (estadoBomber < 9) {
                estadoBomber = 9;
            }
            estadoBomber++;

            if (estadoBomber >= 12) {
                estadoBomber = 10;
            }
            posicionBomber[Y] -= 10;
        }
        if (controles[ABAJO].pulsado) {
            if (estadoBomber < 3 && estadoBomber > 6) {
                estadoBomber = 3;
            }
            estadoBomber++;

            if (estadoBomber >= 6) {
                estadoBomber = 4;
            }

            posicionBomber[Y] += 10;
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



        canvas.drawBitmap(mapa,
                new Rect((int)posicionMapa[X], 0, (int)(maxX + posicionMapa[X]), (int)mapa_h),
                new Rect(60, (int)70, (int)maxX*2, (int)(maxY*3/4)),
                p);

        canvas.drawBitmap(bm,
                new Rect((int)(estadoBomber*posBMW), 0, (int)(posBMW*estadoBomber)+(int)posBMW, (int)posBMH),
                new Rect((int)posicionBomber[X], (int)posicionBomber[Y],
                        (int)((int)posicionBomber[X]+posBMW), (int)(posicionBomber[Y]+posBMH)),
                p);

        for(int i = 0; i<controles.length;i++)
            controles[i].dibujar(canvas,p);
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
        int index;
        int x,y;

        // Obtener el pointer asociado con la acción
        index = event.getActionIndex();


        x = (int) event.getX(index);
        y = (int) event.getY(index);

        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                hayToque=true;

                synchronized(this) {
                    toques.add(index, new Toque(index, x, y));
                }

                //se comprueba si se ha pulsado
                for(int i=0;i<controles.length;i++)
                    controles[i].compruebaPulsado(x,y);
                break;

            case MotionEvent.ACTION_POINTER_UP:
                synchronized(this) {
                    toques.remove(index);
                }

                //se comprueba si se ha soltado el botón
                for(int i=0;i<controles.length;i++)
                    controles[i].compruebaSoltado(toques);
                break;

            case MotionEvent.ACTION_UP:
                synchronized(this) {
                    toques.clear();
                }
                hayToque=false;
                //se comprueba si se ha soltado el botón
                for(int i=0;i<controles.length;i++)
                    controles[i].compruebaSoltado(toques);
                break;
        }

        return true;
    }


}
