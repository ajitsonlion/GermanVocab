package flashcards.vocab.com.germanvocab.cardUI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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



    public CardAdapter(Context context, ArrayList<FlashCard> dictionary) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cardslayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        FlashCard flashCard=dictionary.get(position);

        textView.setText(flashCard.getGermanWord());
        Picasso.with(context)
                .load(MainActivity.DOMAIN_NAME+flashCard.getWordImage())
                .into(imageView); //populate the list items with country flag of corresponding countries


        return rowView;
    }


}
