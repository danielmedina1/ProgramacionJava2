package es.riberadeltajo.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageButton;
import android.widget.MediaController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.net.URI;

public class MainActivity extends AppCompatActivity implements  MediaController.MediaPlayerControl {
    MediaController mc;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ListaMultimedia.tm.isEmpty()) {
            ListaMultimedia.tm.add(new TipoMultimedia("Uno", "A", R.drawable.emborracharme, 0, "eltiempopasara"));
            ListaMultimedia.tm.add(new TipoMultimedia("Dos", "B", R.drawable.entersandman, 1, "eltiempopasara"));
            ListaMultimedia.tm.add(new TipoMultimedia("Tres", "C", R.drawable.eltiempo, 2, "eltiempopasara"));
        }
    }
    public void playAudio(String uri, Context c) {
        Uri ur = Uri.parse(uri+".mp3");

            mp = MediaPlayer.create(c, ur);
            mp.start();




    }
    public void playVideo(String uri, Context c) {

    }
    public void playStreaming(String uri, Context c) {

    }

    @Override
    public void start() {
        if (!mp.isPlaying()) {
            mp.start();
        }
    }

    @Override
    public void pause() {
        if (mp.isPlaying()) {
            mp.pause();
        }
    }

    @Override
    public int getDuration() {
        return mp.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mp.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mp.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mp.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return mp.getAudioSessionId();
    }
}