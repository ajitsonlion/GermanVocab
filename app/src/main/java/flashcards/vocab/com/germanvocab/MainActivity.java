package flashcards.vocab.com.germanvocab;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import flashcards.vocab.com.germanvocab.aphidmobile.flip.FlipViewController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import flashcards.vocab.com.germanvocab.cardUI.CardAdapter;
import flashcards.vocab.com.germanvocab.parser.FlashCard;
import hugo.weaving.DebugLog;
import it.gmariotti.cardslib.library.internal.Card;


public class MainActivity extends Activity  {


    ArrayList<Card> dictionary;
    ProgressDialog mProgressDialog;
    ArrayList<FlashCard> wordsDatabase;
    CardAdapter cardAdapter;
    FlipViewController flipView;
    public static final  String DOMAIN_NAME ="http://ajitsonlion.comule.com/germanvocab/";

    public static final  String DICTIONARY_URL ="http://ajitsonlion.comule.com/germanvocab/dictionary.json";


    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("data", "started");
        new GetWordsInBackground().execute();



         flipView = new FlipViewController(this, FlipViewController.HORIZONTAL);


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

                Reader reader = new InputStreamReader(getAssets().open("dictionary.json")); //Read the json output

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

            CardAdapter dictionary=new CardAdapter(MainActivity.this,wordsDatabase);

              flipView.setAdapter(dictionary);
                setContentView(flipView);



            mProgressDialog.dismiss();


        }
    }
}


