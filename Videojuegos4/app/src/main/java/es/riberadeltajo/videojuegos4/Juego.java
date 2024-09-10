package es.riberadeltajo.videojuegos4;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class Juego extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private SurfaceHolder holder;
    public BucleJuego bucle;

    public int maxX, maxY;

    Bitmap explosion;
    boolean cazado = false;
    Bicho bicho;
    Robot robot;
    Explosion ex;


    private static final String TAG = Juego.class.getSimpleName();

    public Juego(Activity context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // se crea la superficie, creamos el game loop

        // Para interceptar los eventos de la SurfaceView
        getHolder().addCallback(this);

        Canvas c = getHolder().lockCanvas();
        maxX = c.getWidth();
        maxY = c.getHeight();
        getHolder().unlockCanvasAndPost(c);



        // creamos el game loop
        bucle = new BucleJuego(getHolder(), this);

        // Hacer la Vista focusable para que pueda capturar eventos
        setFocusable(true);
        inicializar();
        //comenzar el bucle
        bucle.start();

        setOnTouchListener(this::onTouch);
    }

    private void inicializar() {
        explosion = BitmapFactory.decodeResource(getResources(), R.drawable.explosion);
        robot = new Robot(this);
        bicho = new Bicho(this);
        robot.inicializa();
        bicho.inicializa();
    }

    /**
     * Este método actualiza el estado del juego. Contiene la lógica del videojuego
     * generando los nuevos estados y dejando listo el sistema para un repintado.
     */
    public void actualizar() {

        if (robot.cazado(bicho.getBitmapActual(), bicho.x, bicho.y)){
            cazado = true;
            if (ex == null) {
                ex = new Explosion(this, robot.x + robot.bitmapRobot.getWidth()/2, robot.y + robot.bitmapRobot.getHeight()/2) ;
            }


        }
        else {
            robot.mover();
            bicho.mover();
        }
        if (ex != null) {
            ex.actualizarEstado();
            if(ex.haTerminado()) {
                finJuego();
            }
        }

    }

    private void finJuego() {
        bucle.JuegoEnEjecucion = false;
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
        //Cuidado con el orden
        bicho.pintar(canvas);
        if (!cazado) {
            robot.pintar(canvas);
        }

        if (cazado) {
            canvas.drawText("CAZADO FIN DE LA PARTIDA", 50, 250, p);
        }
        if (ex != null) {
            ex.dibujar(canvas, p);
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

        if (x >= 0 && x < maxX/2) {
            if (y >= 0 && y < maxY/2) {
                // Hacia Arriba
                bicho.estado = 0;
            } else {
                bicho.estado = 3;
            }
        } else {
            if (y <= maxY && y > maxY/2) {
                // Hacia Arriba
                bicho.estado = 1;
            } else {
                bicho.estado = 2;
            }
        }
        return false;
    }
}
