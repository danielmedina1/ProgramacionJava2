package es.riberadeltajo.videojuegos4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

public class Robot {
    /* Responsabilidades
    *  Moverse: -> Va a tener coordenadas X e Y
    *  conoce su velocidad: va a tener vx y vy
    *  pintarse: se pinta en las coordenadas x e y en cada interacción
    * detectar colision
    * */

    public float x = 0, y = 0, vx, vy, deltax, deltay;
    Juego juego;
    public Robot(Juego juego) {
        this.juego = juego;
    }
    Bitmap bitmapRobot;

    public  void inicializa() {
        x = 0;
        y = 0;
        Random r = new Random();
        vx = r.nextInt(7)+3;
        vy = r.nextInt(10)+5;
        deltax = juego.maxX / vx / 30;
        deltay = juego.maxY / vy / 30;

        bitmapRobot = BitmapFactory.decodeResource(juego.getResources(), R.drawable.robot1);


    }
    public void pintar(Canvas c) {
        c.drawBitmap(bitmapRobot, x, y, null);
    }

    public void mover() {
        x += deltax;
        y += deltay;

        if (x >= juego.maxX - bitmapRobot.getWidth()) {
            deltax = deltax * (-1);
        }
        if (y >= juego.maxY - bitmapRobot.getHeight()) {
            deltay = deltay * (-1);
        }
        if (x <= 0) {
            deltax = deltax * (-1);
        }
        if (y <= 0) {
            deltay = deltay * (-1);
        }
    }

    public boolean cazado(Bitmap bicho, float x_bicho, float y_bicho) {
        //Bitmap y coordenadas de los objetos implicados: robot, x_robot, y_robot, bicho, x_bicho, y_bicho
        return Colision.hayColision(bitmapRobot,(int) x,(int)y,
                bicho,(int)x_bicho,(int)y_bicho);
    }
}
