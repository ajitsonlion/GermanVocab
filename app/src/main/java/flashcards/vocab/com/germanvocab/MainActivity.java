package flashcards.vocab.com.germanvocab;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import flashcards.vocab.com.germanvocab.cardUI.CardAdapter;
import flashcards.vocab.com.germanvocab.parser.URLConnection;
import flashcards.vocab.com.germanvocab.parser.FlashCard;
import hugo.weaving.DebugLog;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;


public class MainActivity extends Activity {

    ArrayList<Card> dictionary;
    CardArrayAdapter mCardArrayAdapter;
    CardListView listView;
    ProgressDialog mProgressDialog;
    Map<String, ArrayList<FlashCard>> wordsDatabase;
    TextView demo;
    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("data","started");
        new GetWordsInBackground().execute();


        listView = (CardListView) findViewById(R.id.myList);



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

        String demoToShow="";

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
            Log.d("data","started");

        }

        @Override
        @DebugLog
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                URLConnection connector=new URLConnection();
                wordsDatabase= connector.getAllWordsListByLetter();


                // Get the html document title
                Log.d("data","working");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        @DebugLog
        protected void onPostExecute(Void result) {
            // Set title into TextView

            dictionary= CardAdapter.getUICardsFromFlashCards(getApplicationContext(),wordsDatabase);




            mCardArrayAdapter = new CardArrayAdapter(getApplicationContext(),dictionary);


            if (listView!=null){
                listView.setAdapter(mCardArrayAdapter);
            }

            mProgressDialog.dismiss();

        }
    }
}


