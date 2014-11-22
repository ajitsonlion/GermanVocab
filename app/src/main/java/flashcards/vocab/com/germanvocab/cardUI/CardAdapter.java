package flashcards.vocab.com.germanvocab.cardUI;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import flashcards.vocab.com.germanvocab.parser.FlashCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by ajit on 22.11.14.
 */
public class CardAdapter {




    public  static ArrayList<Card> getUICardsFromFlashCards(Context context,Map<String, ArrayList<FlashCard>> dictionary){

        List<Map.Entry<String,ArrayList<FlashCard>>> list = new ArrayList<Map.Entry<String,ArrayList<FlashCard>>>(dictionary.entrySet());


        ArrayList<Card> cardsUI = new ArrayList<Card>();

        for (Map.Entry<String, ArrayList<FlashCard>> entry : list) {
           // String key = entry.getKey();
            ArrayList<FlashCard> wordsForLetter = entry.getValue();


            for (FlashCard flashCard:wordsForLetter){

                Card card=new Card(context);
                CardHeader header=new CardHeader(context);
                header.setTitle(flashCard.getGermanWord());
                card.setTitle(flashCard.getGermanWord());
              //  card.addCardHeader(header);
                //Create thumbnail
                CardThumbnail thumb = new CardThumbnail(context);

                card.setTitle(flashCard.getGermanWord());

                //Set resource
                thumb.setUrlResource(flashCard.getWordImage());

                //Add thumbnail to a card
                card.addCardThumbnail(thumb);

                cardsUI.add(card);


            }

        }

        Collections.shuffle(cardsUI);

        return cardsUI;



    }


}
