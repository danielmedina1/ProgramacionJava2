package es.riberadeltajo.videojuegos4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

public class Bicho {
    /* Responsabilidades
    *  moverse x, y
    *  velocidad vx, vy, deltax, deltay
    *  estado = 0 -> Arriba
    *  estado = 1 -> Abajo
    *  estado = 2 -> Derecha
    *  estado = 3 -> Izquierda
    *  pintarse en relación a su estado
    *
    * */
    public float x = 0, y = 0, vx, vy, deltax, deltay;
    public int estado = 0;
    public final int ARRIBA = 0;
    public final int ABAJO = 1;
    public final int IZQUIERDA = 3;
    public final int DERECHA = 2;

    //vx es el tiempo que tarda el bicho en cruzar la pantalla horizontalmente, vy es lo mismo pero en vertical
    Juego juego;

    Bitmap[] bitmapBicho;

    public Bicho(Juego juego) {
        this.juego = juego;
    }
    public  void inicializa() {
        bitmapBicho = new Bitmap[4];
        bitmapBicho[0] = BitmapFactory.decodeResource(juego.getResources(), R.drawable.bicho0);
        bitmapBicho[1] = BitmapFactory.decodeResource(juego.getResources(), R.drawable.bicho1);
        bitmapBicho[2] = BitmapFactory.decodeResource(juego.getResources(), R.drawable.bicho2);
        bitmapBicho[3] = BitmapFactory.decodeResource(juego.getResources(), R.drawable.bicho3);
        x = 0;
        y = juego.maxY - bitmapBicho[estado].getHeight();
        vx = 5;
        vy = 7;
        deltax = juego.maxX / vx / 30;
        deltay = juego.maxY / vy / 30;
    }
    public void pintar(Canvas c) {
        c.drawBitmap(bitmapBicho[estado], x, y, null);
    }
    public void mover() {
        switch (estado) {
            case ARRIBA:
                y -= deltay;
                break;
            case ABAJO:
                y += deltay;
                break;
            case IZQUIERDA:
                x -= deltax;
                break;
            case DERECHA:
               x += deltax;
                break;

        }
    }
    public  Bitmap getBitmapActual() {
        return bitmapBicho[estado];
    }
}
