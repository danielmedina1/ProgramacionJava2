package es.riberadeltajo.practica6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.MediaController;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  MediaController.MediaPlayerControl {
    MediaController mc;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (ListaMultimedia.tm.isEmpty()) {
//            ListaMultimedia.tm.add(new TipoMultimedia("Uno", "A", R.drawable.emborracharme, 0, "eltiempopasara"));
//            ListaMultimedia.tm.add(new TipoMultimedia("Dos", "B", R.drawable.entersandman, 1, "eltiempopasara"));
//            ListaMultimedia.tm.add(new TipoMultimedia("Tres", "C", R.drawable.eltiempo, 2, "eltiempopasara"));
//        }
        ListaMultimedia.tm = inicializarProductos();
        ListaMultimedia.contexto = getApplicationContext();


        //playAudio("eltiempopasara", getApplicationContext());
        //playVideo("entersandman");
        //playVideo("magia");
        //playStreaming("https://riberadeltajo.es/PMDM_ut4/LME.mp4");
//        c = this;

    }



    private ArrayList<TipoMultimedia> inicializarProductos() {
        ArrayList<TipoMultimedia> JsonTM = new ArrayList<TipoMultimedia>();
        String json = null;
        try {
            InputStream is = getAssets().open("recursosList.json");
            int size = is.available();
            byte [] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jTipos = jsonObject.getJSONArray("recursos_list");
            for (int i = 0; i < jTipos.length(); i++) {
                JSONObject jTipo = jTipos.getJSONObject(i);
                String jnombre = jTipo.getString("nombre");
                String jdescripcion = jTipo.getString("descripcion");
                String jtipo = jTipo.getString("tipo");
                String uri = jTipo.getString("URI");
                String jimagen = jTipo.getString("imagen");
                int idPortada = getApplicationContext().getResources().getIdentifier(jimagen, "drawable", getPackageName());
                JsonTM.add(new TipoMultimedia(jnombre, jdescripcion, idPortada, Integer.parseInt(jtipo),uri));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return JsonTM;
    }
    public void playAudio(String uri, Context c) {
        int idPista = ListaMultimedia.contexto.getResources().getIdentifier(uri, "raw", ListaMultimedia.contexto.getPackageName());
        mp = MediaPlayer.create(ListaMultimedia.contexto, idPista);
        mp.start();



    }
    public void playVideo(String uri, Context c ) {
        Intent i1 = new Intent(ListaMultimedia.contexto, MainActivity2.class);
        i1.putExtra("uri", uri);
        i1.addFlags(i1.FLAG_ACTIVITY_NEW_TASK);
        ListaMultimedia.contexto.startActivity(i1);
    }
    public void playStreaming(String uri, Context c ) {
        Intent i2 = new Intent(ListaMultimedia.contexto, MainActivity3.class);
        i2.putExtra("uri", uri);
        i2.addFlags(i2.FLAG_ACTIVITY_NEW_TASK);
        ListaMultimedia.contexto.startActivity(i2);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_multimedia, menu);
        return true;
    }
}