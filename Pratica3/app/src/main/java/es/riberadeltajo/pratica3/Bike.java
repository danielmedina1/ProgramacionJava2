package es.riberadeltajo.pratica3;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Bike implements Parcelable {
    public Bitmap photo;
    public String owner;
    public String description;
    public String city;
    public String location;
    public String email;

    protected Bike(Parcel in) {
        photo = in.readParcelable(Bitmap.class.getClassLoader());
        owner = in.readString();
        description = in.readString();
        city = in.readString();
        location = in.readString();
        email = in.readString();
    }

    public static final Creator<Bike> CREATOR = new Creator<Bike>() {
        @Override
        public Bike createFromParcel(Parcel in) {
            return new Bike(in);
        }

        @Override
        public Bike[] newArray(int size) {
            return new Bike[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public Bike(Bitmap photo, String owner, String description, String city, String location, String email) {
        this.photo = photo;
        this.owner = owner;
        this.description = description;
        this.city = city;
        this.location = location;
        this.email= email;
    }

    @Override
    public String toString() {
        return owner+" "+description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(photo, flags);
        dest.writeString(owner);
        dest.writeString(description);
        dest.writeString(city);
        dest.writeString(location);
        dest.writeString(email);
    }
}

