package flashcards.vocab.com.germanvocab.parser;

import android.renderscript.Element;
import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by ajit on 20.11.14.
 */
public class ParsePageForWords {


    Document doc;
    WordModelForLetter wordsListForLetter;

    ParsePageForWords(Document doc,String letter){
        this.doc=doc;
        wordsListForLetter=new WordModelForLetter();
        wordsListForLetter.setLetter(letter);

    }


    public  String getWordsList(){

        ArrayList<flashCard> flashCards=new ArrayList<flashCard>();
        int id=1;
         String temp="";
        for (flashCard card:flashCards){

          card.setWordId(id++);

           Elements germanWord=doc.getElementsByTag("TABLE");
            Log.d("word",germanWord.toString());

            temp+=" "+id;

        }


       return temp+" ajit";



    }

}
