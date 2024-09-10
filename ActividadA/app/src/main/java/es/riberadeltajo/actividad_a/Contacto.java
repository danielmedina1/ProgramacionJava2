package es.riberadeltajo.actividad_a;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Contacto implements Parcelable {
    public String nombre;
    public String apellidos;
    public int edad;
    public String telefono;
    public boolean casado;
    public ArrayList<Contacto> familiares;

    public Contacto(String nombre, String apellidos, int edad, String telefono, boolean casado, ArrayList<Contacto> familiares) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.casado = casado;
        this.familiares = new ArrayList<Contacto>();
    }
    public Contacto(String nombre, String apellidos, int edad, String telefono, boolean casado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.casado = casado;
        this.familiares = new ArrayList<Contacto>();
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", casado=" + casado +
                ", familiares=" + familiares +
                '}';
    }

    protected Contacto(Parcel in) {
        nombre = in.readString();
        apellidos = in.readString();
        edad = in.readInt();
        telefono = in.readString();
        casado = in.readByte() != 0;
        familiares = in.createTypedArrayList(Contacto.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeInt(edad);
        dest.writeString(telefono);
        dest.writeByte((byte) (casado ? 1 : 0));
        dest.writeTypedList(familiares);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    public void addFamiliares (Contacto c) {
        familiares.add(c);
    }
}
