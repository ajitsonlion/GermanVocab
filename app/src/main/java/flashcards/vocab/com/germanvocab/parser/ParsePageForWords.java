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



    public   static  ArrayList<FlashCard> getWordsList(Document doc){


        ArrayList<FlashCard> flashCards=new ArrayList<FlashCard>();
        int id=1;

        Elements allPara=doc.getElementsByTag("p");

        Log.d("tag",allPara.size()+"");

        Element mainPara=allPara.get(0);


        Log.d("tag","===============================================================================");
        Log.d("tagChild",mainPara.tagName());

        Log.d("tagChild",mainPara.children().size()+"");
        Log.d("tag","===============================================================================");



        Element mainTable=mainPara.child(0);

        Log.d("tagTable",mainTable.tagName());
        Log.d("tag","===============================================================================");

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
