package flashcards.vocab.com.germanvocab.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.util.Log;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

import hugo.weaving.DebugLog;

/**
 * Created by ajit on 20.11.14.
 */
public class ParsePageForWords {


    @DebugLog
    public   static  ArrayList<FlashCard> getWordsList(Document doc,String letter){


        ArrayList<FlashCard> flashCards=new ArrayList<FlashCard>();
        int id=1;

        Elements allPara=doc.getElementsByTag("p");
        Element mainPara=allPara.first();

        Element mainTable=mainPara.child(3);

        Elements tableRows=mainTable.getElementsByTag("tr");

        for (Element row:tableRows){

            Elements tableData=row.children();

            for (Element word:tableData){

                Elements wordBlock=word.getElementsByTag("b");

                Element imageAndEnglish=wordBlock.first();
                String imageTag =imageAndEnglish.attr("src");


            }
        }


        Log.d("wordTest","===============================================================================");

        for (FlashCard card:flashCards){

          card.setWordId(id++);



      }


       return flashCards;



    }

}
