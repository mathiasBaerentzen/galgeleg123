package com.example.galgelej.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Words {
    public ArrayList <String> Words = new ArrayList<String>();

    public void updateWordList(){
        new Thread(()-> {
            try {
                Document document =  Jsoup.connect("https://dr.dk").get();
                String []word = document.text().toLowerCase().replaceAll("[^a-zæøå ]","").split(" ");
                synchronized (Words){
                    for(int i=0; word.length > i; i++){
                        if(word[i].length() > 1 ){
                            Words.add(word[i]);
                            wait(200);
                            System.out.println(word[i]);
                        }

                    }
                    System.out.println(Words);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        });
    }

    public ArrayList <String> getWordList() throws InterruptedException {
       return Words;

    }

}
