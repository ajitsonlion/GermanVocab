package flashcards.vocab.com.germanvocab.cardUI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import flashcards.vocab.com.germanvocab.MainActivity;
import flashcards.vocab.com.germanvocab.R;
import flashcards.vocab.com.germanvocab.parser.FlashCard;


/**
 * Created by ajit on 22.11.14.
 */
public class CardAdapter extends ArrayAdapter<FlashCard> {

    private final Context context;
    private  ArrayList<FlashCard> dictionary;


    public CardAdapter(Activity context, ArrayList<FlashCard> dictionary) {
        super(context, R.layout.cardslayout);
        this.context = context;
        this.dictionary = dictionary;
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


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_main, parent, false);


        TextView germanWord = (TextView) rowView.findViewById(R.id.germanWord);
     //   TextView englishWord = (TextView) rowView.findViewById(R.id.englishWord);


        FlashCard flashCard=dictionary.get(position);

        germanWord.setText(flashCard.getGermanWord());
      //  englishWord.setText(flashCard.getEnglishWord());


        return rowView;
    }


}
