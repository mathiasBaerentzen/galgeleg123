package com.example.galgelej.controlers;

public class StringController {
    /*
        formålet ved følgende funktion er at tage en String og erstatte den enkelte char i strengen
     */
    public String changeCharInPosition(int position, char chossenChar, String word){
        char[] charArray = word.toCharArray();// her laves Stringen om til et array karakterer
        charArray[position] = chossenChar; // og her er erstates den enkelte char
        return new String(charArray);
        //following lines of code is inspired from the following stackoverflow
        //https://stackoverflow.com/questions/6952363/replace-a-character-at-a-specific-index-in-a-string

    }
}
