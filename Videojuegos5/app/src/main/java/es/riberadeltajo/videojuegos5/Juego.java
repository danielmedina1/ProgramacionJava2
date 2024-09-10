package es.riberadeltajo.videojuegos5;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Juego extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener
{

    private SurfaceHolder holder;
    private BucleJuego bucle;

    int estado_mario=0;
    //vectores:
    private final int IZQUIERDA=0;
    private final int DERECHA=1;
    private final int SALTO=2;
    private final int X=0;
    private final int Y=1;
    float posicionMapa[]=new float[2];
    float posicionMario[]=new float[2];  //Vector M de posición
    float posicionInicialMario[]=new float[2];  //Vector M de posición
    float velocidadMario[]=new float[2]; //Vector V (vx, vy)

    float gravedadMario[]=new float[2]; // Vector G (gx, gy)
    float deltaT;


    //Dimensiones del canvas
    private float maxX,maxY;
    private Bitmap mapa;
    private float mapa_w,mapa_h,mario_w,mario_h;
    private final int tiempoCrucePantalla=5;
    private boolean saltoIniciado=false  ;
    private MediaPlayer mp, jump, fin;
    private boolean hayToque;
    private boolean finNivel=false  ;
    private Control[] controles= new Control[3];
    private ArrayList<Toque> toques= new ArrayList<>();
    private Bitmap mario;

    private static final String TAG = Juego.class.getSimpleName();

    private AppCompatActivity actividad;
    public Juego(AppCompatActivity context) {
        super(context);
        actividad=context;
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void inicializar(){

        mp=MediaPlayer.create(actividad,R.raw.mario);
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        jump=MediaPlayer.create(actividad,R.raw.marojump);

        mapa=BitmapFactory.decodeResource(getResources(),R.drawable.mapamario);
        mapa_h=mapa.getHeight();
        mapa_w=mapa.getWidth();

        mario=BitmapFactory.decodeResource(getResources(),R.drawable.mario);
        mario_h=mario.getHeight();
        mario_w=mario.getWidth();

        Canvas c=getHolder().lockCanvas();
        maxY=c.getHeight();
        maxX=c.getWidth();
        getHolder().unlockCanvasAndPost(c);

        //deltaT = 1/MAX_FPS
        deltaT=1.0f/BucleJuego.MAX_FPS;

        //coordenadas iniciales del mapa
        posicionMapa[X]=0;
        posicionMapa[Y]=(maxY-mapa_h)/2;

        velocidadMario[X]=maxX/tiempoCrucePantalla; //espacio/tiempo (en segundos)
        velocidadMario[Y]=-velocidadMario[X]*2;
        gravedadMario[X]=0;
        gravedadMario[Y]=-velocidadMario[Y];



        posicionInicialMario[X]=maxX*0.1f;
        posicionInicialMario[Y]=posicionMapa[Y]+mapa_h*0.9f-mario_h*2.0f/3.0f;
        posicionMario[X]=posicionInicialMario[X];
        posicionMario[Y]=posicionInicialMario[Y];

        //flecha_izda
        controles[IZQUIERDA]=new Control(getContext(),0, posicionMapa[Y]+mapa_h+10);
        controles[IZQUIERDA].cargar( R.drawable.flecha_izda);
        controles[IZQUIERDA].nombre="IZQUIERDA";
        //flecha_derecha
        controles[DERECHA]=new Control(getContext(),
                controles[0].ancho()+controles[0].coordenada_x+5,controles[0].coordenada_y);
        controles[DERECHA].cargar(R.drawable.flecha_dcha);
        controles[DERECHA].nombre="DERECHA";

        controles[SALTO]=new Control(getContext(),
                maxX-controles[IZQUIERDA].ancho(),controles[IZQUIERDA].coordenada_y);

        controles[SALTO].cargar(R.drawable.flecha_up);
        controles[SALTO].nombre="SALTO";
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
        if(finNivel)
            return; //de hecho acaba la partida

        if(saltoIniciado) {
            velocidadMario[Y] += deltaT * gravedadMario[Y];
            posicionMario[Y]+=deltaT*velocidadMario[Y];
        }

        if (controles[DERECHA].pulsado) {
            if(posicionMario[X]>0.7*maxX){
                posicionMapa[X]+=deltaT*velocidadMario[X]; //se mueve el mapa
            }
            else {
                posicionMario[X] += deltaT * velocidadMario[X]; //se mueve mario
            }

            estado_mario++;
            if (estado_mario > 3)
                estado_mario = 1;
        }

        if(posicionMario[Y]>posicionMapa[Y]+mapa_h*0.9-mario_h*2/3) {
            posicionMario[Y]=posicionInicialMario[Y];
            saltoIniciado=false;
        }

        if (controles[IZQUIERDA].pulsado) {
            posicionMario[X] -= deltaT * velocidadMario[X];
            estado_mario--;
            if (estado_mario < 0)
                estado_mario = 3;
        }
        //if(!saltoIniciado)  -> activar para permitir un solo salto
        if(controles[SALTO].pulsado){
            //posicionMario[Y] += deltaT * velocidadMario[Y];
            saltoIniciado=true;
            velocidadMario[Y] = -velocidadMario[X]*2; // reinicio el salto
            jump.start();
        }

        //Comprobar fin de nivel
        float proporcionMapa=posicionMapa[X]/mapa_w*100;
        if(proporcionMapa > 81 & proporcionMapa < 82){
            finNivel=true;
            mp.stop();
            MediaPlayer fin=MediaPlayer.create(actividad,R.raw.levelcomplete);
            fin.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        }



    }

    /**
     * Este método dibuja el siguiente paso de la animación correspondiente
     */
    public void renderizar(Canvas canvas) {
        if(canvas==null)
            return;
        canvas.drawColor(Color.BLACK);
        Log.d("RIBERADELTAJO",canvas.getHeight()+";"+canvas.getWidth());
        //pintar mensajes que nos ayudan
        Paint p=new Paint();
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setColor(Color.RED);
        p.setTextSize(50);
        canvas.drawText("Frame "+bucle.iteraciones+";"+"Tiempo "+bucle.tiempoTotal + " ["+bucle.maxX+","+bucle.maxY+"]",50,150,p);
        //canvas.drawCircle(150,150,200,p);


        canvas.drawBitmap(mapa,
                new Rect((int) posicionMapa[X],0, (int) (maxX+posicionMapa[X]),(int)mapa_h),  //rectangulo origen (recorte del mapa)
                new Rect((int)0,(int)posicionMapa[Y],(int)maxX,(int)(posicionMapa[Y]+mapa_h)) //destino en la pantalla
                ,p);

        Log.d("RIBERADELTAJO","Recortando mapa en "+posicionMapa[X]/mapa_w*100);

        canvas.drawBitmap(mario,
                new Rect((int) (estado_mario*mario_w/21),0,(int)(estado_mario*mario_w/21+mario_w/21),(int)(mario_h*2.0f/3.0f)),
                new Rect((int)posicionMario[X],(int)posicionMario[Y],
                        (int)(posicionMario[X]+mario_w/21),(int) (posicionMario[Y]+mario_h*2/3.0f)),p);

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