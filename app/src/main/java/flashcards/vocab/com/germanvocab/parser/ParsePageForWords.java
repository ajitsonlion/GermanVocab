package flashcards.vocab.com.germanvocab.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.util.Log;

import com.orm.SugarRecord;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import hugo.weaving.DebugLog;

/**
 * Created by ajit on 20.11.14.
 */
public class ParsePageForWords     {



        public   static  ArrayList<FlashCard> getWordsList(Document doc,String letter){


        ArrayList<FlashCard> flashCards=new ArrayList<FlashCard>();
        int id=1;

        Elements allPara=doc.getElementsByTag("table");

        Log.d("tag",allPara.size()+"");

        Element mainPara=allPara.get(6);


        Log.d("tag","===============================================================================");
        Log.d("tagChild",mainPara.tagName());



        Log.d("tagChild",mainPara.children().size()+"");
        Log.d("tag","===============================================================================");


        Elements tableRows=mainPara.getElementsByTag("td");

        for (Element data:tableRows){

            Elements tableData=data.children();





            for (Element currentNode : tableData) {

                 FlashCard card=new FlashCard();

                 card.setWordId(id++);
                 card.setLetter(letter);


                String englishWord=currentNode.getElementsByTag("b").first().text();

                card.setEnglishWord(englishWord);

                String[] germanWord=currentNode.getElementsByTag("b").last().text().split(" ",2);

                boolean ifArticleExists=germanWord[0].equalsIgnoreCase("der") ||germanWord[0].equalsIgnoreCase("die") ||germanWord[0].equalsIgnoreCase("das");

                if(ifArticleExists)
                {
                    card.setArticle(germanWord[0]);
                    card.setGermanWord(germanWord[1]);


                }else{
                    card.setArticle("");

                    card.setGermanWord(Arrays.toString(germanWord));


                }


                String imageURL=currentNode.getElementsByTag("img").first().attr("src");
                card.setWordImage(URLConnection.DOMAIN_NAME+imageURL);

                flashCards.add(card);
                card.save();


            }


        }

       return flashCards;



    }

}
