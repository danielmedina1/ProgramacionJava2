package es.riberadeltajo.accesobbdd;

public class Discos {
    public String grupo;
    public String titulo;

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Discos(String grupo, String titulo) {
        this.grupo = grupo;
        this.titulo = titulo;
    }
}
