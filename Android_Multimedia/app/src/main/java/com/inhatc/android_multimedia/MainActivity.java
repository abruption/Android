package com.inhatc.android_multimedia;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer objMP;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = this.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        objMP = MediaPlayer.create(this, R.raw.music);


    }

    @Override
    public void onClick(View v) {
        if(objMP != null)
            Music_Player();
        else{
            objMP = MediaPlayer.create(MainActivity.this, R.raw.music);
            Music_Player();
        }
    }

    private void Music_Player(){
        if(!objMP.isPlaying()){
            objMP.start();
            btnPlay.setText("Stop");
        } else{
            btnPlay.setText("Play");
            objMP.stop();
            objMP.release();
            objMP = null;
        }
    }
}