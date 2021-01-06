package com.example.galgelej.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.galgelej.R;
import com.example.galgelej.controlers.JsonController;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.nio.channels.InterruptedByTimeoutException;

public class ActivityEndGame extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_view);

        Button BTN_FrontView = (Button) findViewById(R.id.BTN_mainView);
        Button BTN_scoreboard = (Button) findViewById(R.id.BTN_scoreboard);
        Intent intent = getIntent();
        MediaPlayer mp = MediaPlayer.create(this, R.raw.bensoundgoinghigher);
        mp.stop();
        boolean condition = intent.getBooleanExtra("Condition", false);
        String word = intent.getStringExtra("Word");
        int attemps = intent.getIntExtra("Attemps",0);
        String playerName = intent.getStringExtra("playerName");
        TextView TXT_endGameText = (TextView) findViewById(R.id.TXT_endGameText);
        JsonController jsonController = new JsonController();
        if(condition == true){
            TXT_endGameText.setText("hey du vandt FLOT du gættede ordet "+word+" på "+ attemps+ " forsøg");
            LottieAnimationView aW = (LottieAnimationView) findViewById(R.id.animationViewWin);
            aW.setVisibility(View.VISIBLE);

            try {


                jsonController.SaveJsonObject(this,playerName, word, attemps);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if(condition == false) {
            LottieAnimationView aL = (LottieAnimationView) findViewById(R.id.animationViewLost);
            aL.setVisibility(View.VISIBLE);
            TXT_endGameText.setText("hey du tabte du må hellere øve dig");
        }
        // følgende måde at skifte view kommer herfra
        //https://www.learn2crack.com/2016/10/android-switching-between-activities-example.html#:~:text=In%20Android%20Studio%20select%20File,name%20for%20the%20XML%20layout.
    }
    public void restart(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void scoreboard(View view) {
        Intent intent = new Intent(this, ActivityScoreBoard.class);
        startActivity(intent);
    }


}
