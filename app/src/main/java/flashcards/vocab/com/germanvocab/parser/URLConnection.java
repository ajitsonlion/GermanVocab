package flashcards.vocab.com.germanvocab.parser;

import android.util.Log;
import android.util.Pair;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.jsoup.Jsoup.connect;

/**
 * Created by ajit on 19.11.14.
 */
public class URLConnection {


    private String domainName="http://www.littleexplorers.com/";
    private   String webPage="/languages/german/";
    private  String URLTail="isfor.shtml";


    public  String getAllWordsListByLetter()  {


        String wholeWord="";



            ArrayList<Pair<String,String>> allURLsByLetter=getAllURLByLetter();

            Log.d("URL of Letter", "b");

            for(Pair letterURL:allURLsByLetter){


                try {
                Document doc = Jsoup.connect(letterURL.second.toString()).get();
                ParsePageForWords wordPage=new ParsePageForWords(doc,letterURL.first.toString());
                wholeWord+= wordPage.getWordsList();

            }catch (Exception e) {

                    e.printStackTrace();
                }


        }
        return wholeWord+"Ajit";

    }


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
