package flashcards.vocab.com.germanvocab;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import flashcards.vocab.com.germanvocab.cardUI.CardAdapter;
import flashcards.vocab.com.germanvocab.parser.FlashCard;
import hugo.weaving.DebugLog;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;
import se.emilsjolander.flipview.FlipView;


public class MainActivity extends Activity {


    FlipView flipView;
    ArrayList<Card> dictionary;
    ProgressDialog mProgressDialog;
    ArrayList<FlashCard> wordsDatabase;
    public static final  String DOMAIN_NAME ="http://ajitsonlion.comule.com/germanvocab/";

    public static final  String DICTIONARY_URL ="http://ajitsonlion.comule.com/germanvocab/dictionary.json";


    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("data", "started");
        new GetWordsInBackground().execute();


       flipView = (FlipView) findViewById(R.id.flip_view);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Title AsyncTask
    class GetWordsInBackground extends AsyncTask<Void, Void, Void> {


        @Override
        @DebugLog
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setTitle("Android Basic JSoup Tutorial");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
            Log.d("data", "started");

        }

        @Override
        @DebugLog
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site

                Reader reader = new InputStreamReader(new URL(DICTIONARY_URL).openStream()); //Read the json output
                Gson gson = new GsonBuilder().create();
                wordsDatabase = gson.fromJson(reader,new TypeToken<ArrayList<FlashCard>>() {}.getType());


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        @DebugLog
        protected void onPostExecute(Void result) {
            // Set title into TextView

           // dictionary = CardAdapter.getUICardsFromFlashCards(getApplicationContext(), wordsDatabase);

            CardAdapter dictionary=new CardAdapter(getApplicationContext(),wordsDatabase);

              flipView.setAdapter(dictionary);


                mProgressDialog.dismiss();


        }
    }
}


