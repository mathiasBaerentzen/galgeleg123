package com.example.galgelej.activities;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.galgelej.R;
import com.example.galgelej.controlers.GameController;
import com.example.galgelej.models.Words;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.ArrayList;

public class ActivityGame extends AppCompatActivity implements View.OnClickListener {
    GameController gameController = new GameController();
    String playerName;
    public static MediaPlayer mp;
    String cunciledWord = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mp = MediaPlayer.create(this, R.raw.bensoundgoinghigher);
        TextView TXT_word = (TextView) findViewById(R.id.TXT_word);
        ArrayList<String> wordList = new ArrayList<String>();

        new Thread(()-> {
            try {
                Document document =  Jsoup.connect("https://dr.dk").get();
                String []words = document.text().toLowerCase().replaceAll("[^a-zæøå ]","").split(" ");
                synchronized (wordList){
                    for(int i=0; words.length > i; i++){
                        if(words[i].length() > 1 ){
                            wordList.add(words[i]);
                            System.out.println(words[i]);
                        }

                    }
                    int random = (int) (Math.random() * wordList.size());
                    String word;
                    word = wordList.get(random);// vælger et tilfældigt ord og fra en udvalgt ord.
                    System.out.println(word + "testingRand");
                    gameController.setWord(word);
                    int counts = 0;

                    for (int J = 0; word.length() > J; J++) {
                        cunciledWord = cunciledWord + "*";
                    }

                    gameController.setCunciledWord(cunciledWord);
                    System.out.println(cunciledWord);

                }

            } catch (Exception e) {
                e.printStackTrace();
                TXT_word.setText("error");
            }
        }).start();
        TXT_word.setText(cunciledWord);
        medieController(true);
        //BTN
        Button btnRunTurn = (Button) findViewById(R.id.BTN_pickLetter);
        //TextView
        TextView TXT_playerName = (TextView) findViewById(R.id.TXT_PlayerName);


            ImageView img_Galge = (ImageView) findViewById(R.id.img_Galge);
        img_Galge.setBackgroundResource(R.drawable.galge);
        String playerName = intent.getStringExtra("playerName");
        TXT_playerName.setText(playerName);
        btnRunTurn.setOnClickListener(this);
        TXT_word.setText(cunciledWord);
    }

    private void launchActivity(boolean condition,String Word, int attemps,String playerName) {
        // if condition is true the game is won if condition is false then game lost
        Intent intent = new Intent(this, ActivityEndGame.class);
        intent.putExtra("Condition", condition );
        intent.putExtra("Word", Word );
        intent.putExtra("Attemps", attemps );
        intent.putExtra("playerName", playerName );
        startActivity(intent);
        // følgende måde at skifte view kommer oprindligt herfra
        //https://www.learn2crack.com/2016/10/android-switching-between-activities-example.html#:~:text=In%20Android%20Studio%20select%20File,name%20for%20the%20XML%20layout.
        // er efterfølgende modificeret til at tage imod data og sende det videre til den næste activity
    }

    @Override
    public void onClick(View v) {

        // TextView
        TextView txt_attemps = (TextView) findViewById(R.id.TXT_Attemps);
        TextView txt_Word = (TextView) findViewById(R.id.TXT_word);
        TextView TXT_playerName = (TextView) findViewById(R.id.TXT_PlayerName);
        // input Textfield
        TextView inp_pickLetter = (TextView) findViewById(R.id.INP_pickLetter);
        // imageview
        int attempt = gameController.getAttempt();
        String choosenLetters = inp_pickLetter.getText().toString();

        txt_Word.setText(gameController.findLetters(choosenLetters));


        if(gameController.winCondition(choosenLetters) == true){
            medieController(false);
            launchActivity(true,gameController.getWord(),attempt,TXT_playerName.getText().toString());
        }
        txt_attemps.setText("Forsøg: " + gameController.countDown());
        chanceImg(gameController.getAttempt());

        if(gameController.lostCondition() == false){
            medieController(false);
            launchActivity(false,gameController.getWord(),attempt,TXT_playerName.getText().toString());
        }
    }
    private void medieController(boolean playStatus){

        if(playStatus == true){
            mp.start();
            mp.setLooping(true);
        }
        else{
            mp.stop();
            mp.release();
        }
    }
    private void chanceImg(int attempt){
        ImageView img_Galge = (ImageView) findViewById(R.id.img_Galge);
        switch (attempt){
            case 5:
                img_Galge.setBackgroundResource(R.drawable.img6);
                break;
            case 4:
                img_Galge.setBackgroundResource(R.drawable.img5);
                break;
            case 3:
                img_Galge.setBackgroundResource(R.drawable.img4);
                break;
            case 2:
                img_Galge.setBackgroundResource(R.drawable.img3);
                break;
            case 1:
                img_Galge.setBackgroundResource(R.drawable.img2);
                break;
            case 0:
                img_Galge.setBackgroundResource(R.drawable.img1);
                break;

        }
    }

}
