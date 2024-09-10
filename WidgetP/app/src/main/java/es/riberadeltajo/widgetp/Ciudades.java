package es.riberadeltajo.widgetp;

import androidx.appcompat.app.AppCompatActivity;

public class Ciudades extends AppCompatActivity {
    public String nombre;
    public String descripcion;
    public int imagenes;

    public Ciudades(String nombre, String descripcion, int imagenes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
    }
}
