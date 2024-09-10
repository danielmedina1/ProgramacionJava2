package com.ilm.examenmarzo24danielmedinaalcolea;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Bicho implements Parcelable {
    int identificador;
    String nombre;
    int imagen;

    protected Bicho(Parcel in) {
        identificador = in.readInt();
        nombre = in.readString();
        imagen = in.readInt();
    }

    public static final Creator<Bicho> CREATOR = new Creator<Bicho>() {
        @Override
        public Bicho createFromParcel(Parcel in) {
            return new Bicho(in);
        }

        @Override
        public Bicho[] newArray(int size) {
            return new Bicho[size];
        }
    };

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public Bicho(int identificador, String nombre, int imagen) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(identificador);
        dest.writeString(nombre);
        dest.writeInt(imagen);
    }
}
