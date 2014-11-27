package flashcards.vocab.com.germanvocab.cardUI;

import android.app.Activity;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import flashcards.vocab.com.germanvocab.ListeningHearing.SpeakWord;
import flashcards.vocab.com.germanvocab.MainActivity;
import flashcards.vocab.com.germanvocab.R;
import flashcards.vocab.com.germanvocab.parser.FlashCard;


/**
 * Created by ajit on 22.11.14.
 */
public class CardAdapter extends ArrayAdapter<FlashCard> {

    private final Context context;
    private  ArrayList<FlashCard> dictionary;
    SpeakWord speakWord;


    public CardAdapter(Activity context, ArrayList<FlashCard> dictionary) {
        super(context, R.layout.cardslayout);
        this.context = context;
        this.dictionary = dictionary;
        speakWord = new SpeakWord(context);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dictionary.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private int lastPosition = -1;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.cardslayout, parent, false);


        final View cardFace =  rowView.findViewById(R.id.main_activity_card_face);
        final View cardBack =   rowView.findViewById(R.id.main_activity_card_back);
        cardFace.setMinimumHeight(parent.getMeasuredHeight());
        cardBack.setMinimumHeight(parent.getMeasuredHeight());


        TextView germanWordTextView = (TextView) rowView.findViewById(R.id.germanWord);
        TextView englishWordTextView = (TextView) rowView.findViewById(R.id.englishWord);
        ImageView flipIconFront=(ImageView)rowView.findViewById(R.id.flip_icon_front);
        ImageView flipIconBack=(ImageView)rowView.findViewById(R.id.flip_icon_back);

        flipIconFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(rowView,cardFace,cardBack);
            }
        });

        flipIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(rowView,cardFace,cardBack);
            }
        });


        FlashCard flashCard=dictionary.get(position);

        String germanWord=flashCard.getGermanWord();

        germanWordTextView.setText(germanWord);
        englishWordTextView.setText(flashCard.getEnglishWord());

        speakWord.tts.speak(germanWord, TextToSpeech.QUEUE_FLUSH, null);

        Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        rowView.startAnimation(animation);
        lastPosition = position;


        return rowView;
    }

    public  void flipCard(View view,View cardFace, View cardBack)
    {


        FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

        if (cardFace.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
        }
        view.startAnimation(flipAnimation);
    }





}
