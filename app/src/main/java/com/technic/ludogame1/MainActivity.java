package com.technic.ludogame1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.WindowManager;
import android.widget.ImageView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    private boolean flag=true;
    private static int tempo=5000;      //da mettere 5000
    Animation ludo;
    ImageView ludoSfondo;
    MediaPlayer song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        song=MediaPlayer.create(MainActivity.this,R.raw.happy);
        if(flag)
        {
            song.start();
            flag=false;
        }
        ludo= AnimationUtils.loadAnimation(this,R.anim.ludo_anim);
        //ludoSfondo= findViewById(R.id.imageView);;
        //ludoSfondo.setAnimation(ludo);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent homeIntent= new Intent(MainActivity.this, Menu.class );
                startActivity(homeIntent);
                finish();
            }
        }, tempo );
    }
}