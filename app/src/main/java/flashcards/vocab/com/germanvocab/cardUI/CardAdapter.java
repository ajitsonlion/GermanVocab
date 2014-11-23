package flashcards.vocab.com.germanvocab.cardUI;

import android.content.Context;
import android.util.Log;

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




    public  static ArrayList<Card> getUICardsFromFlashCards(Context context, ArrayList<FlashCard> dictionary){




        ArrayList<Card> cardsUI = new ArrayList<Card>();

        for (FlashCard flashCard : dictionary) {
           // String key = entry.getKey();


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
                Log.d("data", flashCard.getWordImage());
                cardsUI.add(card);


            }


        Collections.shuffle(cardsUI);

        return cardsUI;



    }


}
