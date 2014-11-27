package flashcards.vocab.com.germanvocab.ListeningHearing;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;

import java.util.Locale;

/**
 * Created by ajit on 27.11.14.
 */
public class SpeakWord implements TextToSpeech.OnInitListener {
    public TextToSpeech tts;

    public SpeakWord(Context c) {
        tts = new TextToSpeech(c, this);
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
            tts.setLanguage(Locale.GERMAN);

        }
    }
}
