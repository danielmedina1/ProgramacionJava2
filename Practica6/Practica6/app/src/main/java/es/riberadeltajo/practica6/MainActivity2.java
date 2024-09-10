package es.riberadeltajo.practica6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.MediaController;
import android.widget.VideoView;


public class MainActivity2 extends AppCompatActivity implements MediaController.MediaPlayerControl {

    MediaPlayer mp;
    MediaController mc;
    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mc = new MediaController(this);
        mc.setMediaPlayer(this);
        mc.setAnchorView(findViewById(R.id.videoView));
        vv = findViewById(R.id.videoView2);
        vv.setMediaController(mc);
        Handler h = new Handler(Looper.getMainLooper());
        Intent i1 = getIntent();
        String uri = i1.getStringExtra("uri");
        int uri1 = getApplicationContext().getResources().getIdentifier(uri, "raw", getPackageName());
        vv.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+uri1));
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        mc.show();
                    }
                });
            }
        });

    }

    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void seekTo(int pos) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}