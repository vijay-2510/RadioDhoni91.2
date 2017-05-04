package com.example.vijay.radiodhoni912;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.IOException;

public class Radio extends AppCompatActivity {

    Button play_btn;
    String stream = "http://182.160.110.180:1020/;";
    MediaPlayer mediaPlayer;
    boolean prepared = false;
    boolean started = false;
    VideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        videoview = (VideoView) findViewById(R.id.video_view);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video1);
        videoview.setVideoURI(uri);

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        play_btn = (Button)findViewById(R.id.play_btn);
        play_btn.setEnabled(false);
   //     play_btn.setText("Loading...");
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new PlayerTask().execute(stream);

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(started){
                    started = false;
                    mediaPlayer.pause();
                    videoview.pause();
                 //   play_btn.setText("PLAY");
                    play_btn.setBackgroundResource(R.drawable.pause_img);
                }else{
                    started = true;
                    mediaPlayer.start();
                 //   play_btn.setText("PAUSE");
                    videoview.start();
                    play_btn.setBackgroundResource(R.drawable.play_img);
                }
            }
        });


    }


    private class PlayerTask extends AsyncTask<String,Void,Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {

            try {
                mediaPlayer.setDataSource(params[0]);
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            play_btn.setEnabled(true);
           // play_btn.setText("PLAY");
            play_btn.setBackgroundResource(R.drawable.play_img);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(started){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(started){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(prepared){
            mediaPlayer.release();
        }
    }
}
