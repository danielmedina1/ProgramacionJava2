package es.riberadeltajo.furbo;

import androidx.appcompat.app.AppCompatActivity;

public class Equipos extends AppCompatActivity {
    public String nombre;
    public int imgEquipo;
    public int puntos;

    public String getNombre() {
        return nombre;
    }

    public int getImgEquipo() {
        return imgEquipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImgEquipo(int imgEquipo) {
        this.imgEquipo = imgEquipo;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Equipos(String nombre, int imgEquipo, int puntos) {
        this.nombre = nombre;
        this.imgEquipo = imgEquipo;
        this.puntos = puntos;
    }
}
