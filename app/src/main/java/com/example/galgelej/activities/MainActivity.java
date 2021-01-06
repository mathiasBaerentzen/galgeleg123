package com.example.galgelej.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.galgelej.R;
import com.example.galgelej.activities.ActivityGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> Words = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button btnView = (Button) findViewById(R.id.BTN_startGame);
        final TextView INP_getPlayerName = (TextView) findViewById(R.id.INP_PlayerName);
        btnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                launchActivity(INP_getPlayerName.getText().toString());
            }
        });

        // følgende måde at skifte view kommer herfra
        //https://www.learn2crack.com/2016/10/android-switching-between-activities-example.html#:~:text=In%20Android%20Studio%20select%20File,name%20for%20the%20XML%20layout.
    }


    private void launchActivity(String playerName) {

        Intent intent = new Intent(this, ActivityGame.class);
        intent.putExtra("playerName", playerName );
        startActivity(intent);
    }
}