package com.example.galgelej.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.galgelej.R;

import java.io.IOException;

import com.example.galgelej.controlers.GameController;
import com.example.galgelej.controlers.JsonController;

public class ActivityScoreBoard extends AppCompatActivity {
    JsonController jsonController = new JsonController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        ListView LW_scoreBoard = (ListView) findViewById(R.id.LW_scoreBoard);
        try {

            ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1, jsonController.LoadJsonFile(this));
            LW_scoreBoard.setAdapter(arrayAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void home(View v) {
        launchActivity();
    }
    private void launchActivity() {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}