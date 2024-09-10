package es.riberadeltajo.contentprovider;

public class Contacto {
    String nombre;
    int tiene_telefono;

    long id;



    public Contacto(String nombre, int tiene_telefono, long id) {
        this.nombre = nombre;
        this.tiene_telefono = tiene_telefono;
        this.id = id;
    }
}
