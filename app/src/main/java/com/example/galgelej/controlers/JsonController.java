package com.example.galgelej.controlers;
import android.content.Context;

import com.google.gson.*;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonController {
    public void JsonCotroller(){

    }
    /*
        følgende funktion har formålet at lave et Json object og gemme det i en Json fil.
    */
    public void SaveJsonObject(Context context, String playerName, String word, int attemps) throws IOException {
        JsonObject jsonObject = new JsonObject(); // her opretter jeg mit jsonbject
        jsonObject.addProperty("playerName", playerName);
        jsonObject.addProperty("word", word);
        jsonObject.addProperty("attemps", attemps);
        // i de 3 linjer over fortæller jeg hvad de skal indholde
        String userString = jsonObject.toString();// så convertes objected til en String
        File file = new File(context.getFilesDir(),"HighScore.json"); // dernæst angiver jeg min file path og hvad json filen skal hede
        FileWriter fileWriter = new FileWriter(file,true);// så opretter jeg filen hvis den ikke eksistere,
        // og hvis den gør så sørrer appen true for at den ikke bliver overwritten men at der bare bliver tilføjet til filen
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);// åbner for mulighed til at skrive til filen

        bufferedWriter.append(userString).append("\n");// her bliver der skrevet til filen
        bufferedWriter.close();// her lukker for mulighed til at skrive til filen
    }
    /*
        følgende funktion læser en Json fil og laver det om til en array list
    */
    public ArrayList<String> LoadJsonFile(Context context) throws IOException {
        File file = new File(context.getFilesDir(),"HighScore.json");// først deklerares filepathen
        FileReader fileReader = new FileReader(file);// dernæst oprettes en filereader til filepathen
        BufferedReader bufferedReader = new BufferedReader(fileReader);// dernæst åbnes for mulighed til at læse filen
        ArrayList<String> highScore = new ArrayList<String>();
        String line = bufferedReader.readLine();// så fortælles programmet at den læste linje
        while (line != null){// her startes en while løkke der kører indtil alle linjer er i json filen er kørt igennem
            highScore.add(line);// her adder jeg linjen til min arraylisten
            line = bufferedReader.readLine();// her læsses linjen
        }
        bufferedReader.close();// lukker for læsning af fillen


        return highScore;// returnerer min arrayliste
    }
    /*
        Koden i følgende dokument kommer fra følgende link
        https://attacomsian.com/blog/gson-read-json-file
        det eneste jeg har gjordt er modificere den så den kan læse og skrive det data jeg har brug for.
    */

}
