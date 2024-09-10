package es.riberadeltajo.audiooalgonose;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {

    MediaPlayer mp;
    MediaController mc;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ImageButton plaButton = findViewById(R.id.playButton);
        //ImageButton pauseButton = findViewById(R.id.pauseButton);
        //ImageButton stopButton = findViewById(R.id.stopButton);

        //mp = MediaPlayer.create(this, R.raw.eltiempopasara);
        mc = new MediaController(this);
        mc.setMediaPlayer(this);
        mc.setAnchorView(findViewById(R.id.constraintLayout));
        videoView = findViewById(R.id.videoView2);
        videoView.setMediaController(mc);
        Handler h = new Handler(Looper.getMainLooper());
        //videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.magia));
        videoView.setVideoURI(Uri.parse("https://riberadeltajo.es/PMDM_ut4/LME.mp4"));

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
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
        /** mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mc.show(0);
            }
        });

        plaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying())
                    mp.pause();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying())
                    mp.stop();
            }
        });*/
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
    public boolean onTouchEvent(MotionEvent event) {
        mc.show();
        return super.onTouchEvent(event);
    }
}