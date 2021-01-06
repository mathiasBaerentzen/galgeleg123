package com.example.galgelej.controlers;

import com.example.galgelej.controlers.StringController;
import com.example.galgelej.models.Words;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

public class GameController {
private int attempt = 6;
private String word;
private String cunciledWord;
    StringController stringController = new StringController();
    // initiating game
    public String getConsiledWord() throws Exception {
        Words Words = new Words();

        ArrayList<String> wordList = new ArrayList<String>();


        new Thread(()-> {
            try {
                Document document =  Jsoup.connect("https://dr.dk").get();
                String []words = document.text().toLowerCase().replaceAll("[^a-zæøå ]","").split(" ");
                synchronized (wordList){
                    for(int i=0; 2 > i; i++){
                        if(words[i].length() > 1 ){
                            wordList.add(words[i]);
                            System.out.println(words[i]);
                        }

                    }
                    int random = (int) (Math.random() * wordList.size());

                    word = wordList.get(random);// vælger et tilfældigt ord og fra en udvalgt ord.
                    System.out.println(word + "testingRand");
                    int counts = 0;
                    cunciledWord = "";
                    for (int J = 0; word.length() > J; J++) {
                        cunciledWord = cunciledWord + "*";
                    }
                    System.out.println(wordList);

                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(2);
            }
        }).start();

        return cunciledWord;
    }



    // doing game is running
    public String findLetters(String choosenletter) {

        // følgende funktion kører en forløkke gikker igennem en String
        // og kontroller om nogen af charsne er gættet
        for (int j = 0; j < word.length(); j++) {
            for(int i = 0; i < choosenletter.length();i++){
                if (word.charAt(j) == choosenletter.charAt(i)) {
                    cunciledWord = stringController.changeCharInPosition(j, choosenletter.charAt(i), cunciledWord);
                }
            }
        }
        return cunciledWord;
    }
    public int countDown(){
        attempt = attempt-1;
        return attempt;
    }


    // her ligger win conditionsne for spillet
    public boolean winCondition(String chooseGeus){
        if(word.equals(chooseGeus)){
            return true;
        }
        if(!cunciledWord.contains("*")){
            return true;
        }
        // incase none of the conditions are met
        return false;
    }
    // her er ligger mulighederne
    public boolean lostCondition(){
        //TXT_Attemps.setText("Forsøg: " + i);
        if(attempt == 0){
            return false;
        }
        // incase none of the condition are not met
        return true;
    }
    public String getWord(){return word;}
    public int getAttempt(){return attempt;}

    public void setCunciledWord(String cunciledWord) {this.cunciledWord = cunciledWord;}

    public void setWord(String word) {this.word = word;}
}
