package flashcards.vocab.com.germanvocab;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Locale;

import flashcards.vocab.com.germanvocab.WordSearch.ClearableAutoCompleteTextView;
import flashcards.vocab.com.germanvocab.WordSearch.SearchInDictionary;
import flashcards.vocab.com.germanvocab.cardUI.CardAdapter;
import flashcards.vocab.com.germanvocab.cardUI.SingleScrollListView;
import flashcards.vocab.com.germanvocab.parser.FlashCard;
import hugo.weaving.DebugLog;


public class MainActivity extends Activity {


    ProgressDialog mProgressDialog;
    ArrayList<FlashCard> wordsDatabase;
    SearchInDictionary searchInDictionary;
     SingleScrollListView flipView;
    private   ClearableAutoCompleteTextView searchBox;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("data", "started");
        new GetWordsInBackground().execute();


         flipView = (SingleScrollListView)findViewById(R.id.flip_view);
         flipView.setSingleScroll(true);
        ActionBar actionBar = this.getActionBar(); // you can use ABS or the non-bc ActionBar
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_HOME
                    | ActionBar.DISPLAY_HOME_AS_UP); // what's mainly important here is DISPLAY_SHOW_CUSTOM. the rest is optional
        }

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflate the view that we created before
        View v = inflater.inflate(R.layout.actionbar_search, null);
         // inflate the view that we created before
         // the view that contains the search "magnifier" icon
        final ImageView searchIcon = (ImageView) v.findViewById(R.id.search_icon);
        // the view that contains the new clearable autocomplete text view
            searchBox =  (ClearableAutoCompleteTextView) v.findViewById(R.id.search_box);

        // start with the text view hidden in the action bar
        searchBox.setVisibility(View.INVISIBLE);
        searchIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toggleSearch(false);
            }
        });

        searchBox.setOnClearListener(new ClearableAutoCompleteTextView.OnClearListener() {

            @Override
            public void onClear() {
                toggleSearch(true);
            }
        });

        searchBox.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // handle clicks on search resaults here
            }

        });

        actionBar.setCustomView(v);





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




    // this toggles between the visibility of the search icon and the search box
// to show search icon - reset = true
// to show search box - reset = false
    protected void toggleSearch(boolean reset) {
        ClearableAutoCompleteTextView searchBox = (ClearableAutoCompleteTextView) findViewById(R.id.search_box);
        ImageView searchIcon = (ImageView) findViewById(R.id.search_icon);
        if (reset) {
            // hide search box and show search icon
            searchBox.setText("");
            searchBox.setVisibility(View.GONE);
            searchIcon.setVisibility(View.VISIBLE);
            // hide the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
        } else {
            // hide search icon and show search box
            searchIcon.setVisibility(View.GONE);
            searchBox.setVisibility(View.VISIBLE);
            searchBox.requestFocus();
            // show the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(searchBox, InputMethodManager.SHOW_IMPLICIT);
        }

    }
















    // Title AsyncTask
    class GetWordsInBackground extends AsyncTask<Void, Void, Void> {


        @Override
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
         protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site



                BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("dictionary.json"), "UTF-8"));

                Gson gson = new GsonBuilder().create();

                wordsDatabase = gson.fromJson(reader,new TypeToken<ArrayList<FlashCard>>() {}.getType());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
         protected void onPostExecute(Void result) {
            // Set title into TextView

           // dictionary = CardAdapter.getUICardsFromFlashCards(getApplicationContext(), wordsDatabase);

            CardAdapter dictionary=new CardAdapter(MainActivity.this,wordsDatabase);

              flipView.setAdapter(dictionary);


             searchInDictionary=new SearchInDictionary(getApplicationContext(),wordsDatabase);


             searchBox.setAdapter(searchInDictionary);
             mProgressDialog.dismiss();


        }
    }
}


