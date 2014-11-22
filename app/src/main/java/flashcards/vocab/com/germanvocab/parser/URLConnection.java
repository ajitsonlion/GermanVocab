package flashcards.vocab.com.germanvocab.parser;

import android.util.Log;
import android.util.Pair;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hugo.weaving.DebugLog;

import static org.jsoup.Jsoup.connect;

/**
 * Created by ajit on 19.11.14.
 */
public class URLConnection {


    private String domainName="http://www.littleexplorers.com/";
    private   String webPage="/languages/german/";
    private  String URLTail="isfor.shtml";

    @DebugLog
    public  Map<String, ArrayList<FlashCard>> getAllWordsListByLetter()  {




             Map<String, ArrayList<FlashCard>> letterAndItsWordsCollection = new HashMap<String, ArrayList<FlashCard>>();

            ArrayList<Pair<String,String>> allURLsByLetter=getAllURLByLetter();



            for(Pair letterURL:allURLsByLetter){


                try {

                Document doc = Jsoup.connect(letterURL.second.toString()).get();

                  ArrayList<FlashCard> letterWords= ParsePageForWords.getWordsList(doc);
                  letterAndItsWordsCollection.put(letterURL.first.toString(),letterWords);

             }catch (Exception e) {

                    e.printStackTrace();
                }


        }
        return letterAndItsWordsCollection;

    }

    @DebugLog
    public  ArrayList<Pair<String,String>> getAllURLByLetter(){

        ArrayList<Pair<String,String>> listOfAllURLByLetter= new ArrayList<Pair<String,String>>();
        char[]  alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        for (char anAlphabet : alphabet) {

            String letterURL = domainName + webPage + anAlphabet + URLTail;
            Log.d("URL of Letter",letterURL);

            Pair letterAndUrl=Pair.create( Character.toString(anAlphabet), letterURL);
            listOfAllURLByLetter.add(letterAndUrl);
        }

        return listOfAllURLByLetter;
    }





}
