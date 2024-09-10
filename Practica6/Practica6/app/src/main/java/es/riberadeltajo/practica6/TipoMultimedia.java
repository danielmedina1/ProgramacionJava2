package es.riberadeltajo.practica6;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TipoMultimedia implements Parcelable {
    String titulo;
    String autor;
    int portada;
    int tipo;
    String uri;
    Bitmap bmp1;
    Bitmap bmp2;


    protected TipoMultimedia(Parcel in) {
        titulo = in.readString();
        autor = in.readString();
        portada = in.readInt();
        tipo = in.readInt();
        uri = in.readString();
    }

    public static final Creator<TipoMultimedia> CREATOR = new Creator<TipoMultimedia>() {
        @Override
        public TipoMultimedia createFromParcel(Parcel in) {
            return new TipoMultimedia(in);
        }

        @Override
        public TipoMultimedia[] newArray(int size) {
            return new TipoMultimedia[size];
        }
    };

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPortada() {
        return portada;
    }

    public void setPortada(int portada) {
        this.portada = portada;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public TipoMultimedia(String titulo, String autor, int portada, int tipo, String uri) {
        this.titulo = titulo;
        this.autor = autor;
        this.portada = portada;
        this.tipo = tipo;
        this.uri = uri;
    }

    public TipoMultimedia() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(autor);
        dest.writeInt(portada);
        dest.writeInt(tipo);
        dest.writeString(uri);
    }
}
