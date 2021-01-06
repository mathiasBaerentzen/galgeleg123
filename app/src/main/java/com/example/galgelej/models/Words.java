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

    public static String hentUrl(String url) throws IOException {
        System.out.println("Henter data fra " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }


    /**
     * Hent ord fra DRs forside (https://dr.dk)
     */
    public void hentOrdFraDr() throws Exception {
        String data = hentUrl("https://dr.dk");
        //System.out.println("data = " + data);

        data = data.substring(data.indexOf("<body")). // fjern headere
                replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
                replaceAll("&#198;", "æ"). // erstat HTML-tegn
                replaceAll("&#230;", "æ"). // erstat HTML-tegn
                replaceAll("&#216;", "ø"). // erstat HTML-tegn
                replaceAll("&#248;", "ø"). // erstat HTML-tegn
                replaceAll("&oslash;", "ø"). // erstat HTML-tegn
                replaceAll("&#229;", "å"). // erstat HTML-tegn
                replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
                replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
                replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

        System.out.println("data = " + data);
        System.out.println("data = " + Arrays.asList(data.split("\\s+")));
        Words.clear();
        Words.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));
        System.out.println("muligeOrd = " + Words);
    }
    public ArrayList <String> getWordList() throws InterruptedException {
        new Thread(()-> {
            try {
                Document document =  Jsoup.connect("https://dr.dk").get();
                String []word = document.text().toLowerCase().replaceAll("[^a-zæøå ]","").split(" ");
                synchronized (Words){
                    for(int i=0; 2 > i; i++){
                        if(word[i].length() > 1 ){
                            Words.add(word[i]);
                            System.out.println(word[i]);
                        }

                    }
                    System.out.println(Words);
                    System.out.println(Words);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(2);
            }
        }).start();return Words;

    }

}
