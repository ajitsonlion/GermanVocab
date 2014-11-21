package flashcards.vocab.com.germanvocab;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import flashcards.vocab.com.germanvocab.parser.URLConnection;


public class MainActivity extends Activity {
    String title;
    ProgressDialog mProgressDialog;

    TextView demo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("data","started");
        new GetWordsInBackground().execute();

        demo=(TextView)findViewById(R.id.demo);

        Log.v("data","started");


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
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Android Basic JSoup Tutorial");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
            Log.d("data","started");

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                URLConnection connector=new URLConnection();
                demoToShow= connector.getAllWordsListByLetter();
                // Get the html document title
                Log.d("data","working");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set title into TextView

            Log.d("data","finish");

            mProgressDialog.dismiss();
            demo.setText(demoToShow);
        }
    }
}


