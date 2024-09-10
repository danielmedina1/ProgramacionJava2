package es.riberadeltajo.videojuegos3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MiJuego extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder sh;
    public MiJuego(Context context) {
        super(context);
        sh = getHolder();
        sh.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Canvas canvas = sh.lockCanvas();
        float max_X = canvas.getWidth(); //X
        float max_Y = canvas.getHeight(); //Y
        //Se dibuja
        Paint miPincel = new Paint();
        Paint miPincel2 = new Paint();
        Paint miPincel3 = new Paint();
        Paint miPincel4 = new Paint();
        Paint miPincel5 = new Paint();
        Paint miPincel6 = new Paint();
        Paint miPincel7 = new Paint();
        miPincel.setStyle(Paint.Style.STROKE);
        miPincel.setColor(Color.rgb(255, 125, 0));
        miPincel.setStrokeWidth(20);

        miPincel2.setStyle(Paint.Style.STROKE);
        miPincel2.setColor(Color.WHITE);
        miPincel2.setStrokeWidth(20);

        miPincel3.setStyle(Paint.Style.STROKE);
        miPincel3.setColor(Color.RED);
        miPincel3.setStrokeWidth(20);

        miPincel4.setStyle(Paint.Style.STROKE);
        miPincel4.setColor(Color.YELLOW);
        miPincel4.setStrokeWidth(20);

        miPincel6.setStyle(Paint.Style.STROKE);
        miPincel6.setColor(Color.rgb(255, 0, 255));
        miPincel6.setStrokeWidth(20);

        miPincel5.setStyle(Paint.Style.STROKE);
        miPincel5.setColor(Color.GREEN);
        miPincel5.setStrokeWidth(20);

        miPincel7.setStyle(Paint.Style.STROKE);
        miPincel7.setColor(Color.rgb(150, 255, 255));
        miPincel7.setStrokeWidth(20);

        for (int i = 0; i < 200; i++) {
            canvas.drawCircle(250, 250, 200-i, miPincel2);
        }


        canvas.drawLine(-100, -100, -500, -500, miPincel);
        //canvas.drawText("Hola Mundo", 120, 120, miPincel);
        //canvas.drawCircle(max_X/2, max_Y/2, 200, miPincel);
        //Bitmap robot = BitmapFactory.decodeResource(getResources(), R.drawable.robot1);
        //canvas.drawBitmap(robot,max_X/2, max_Y-550, null);


        sh.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
