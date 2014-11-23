package flashcards.vocab.com.germanvocab.parser;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by ajit on 19.11.14.
 */



public class FlashCard extends SugarRecord<FlashCard> {

    private int wordId;
    private String germanWord;
    private String englishWord;
    private String wordImage;
    private String article;
    private String letter;

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getGermanWord() {
        return germanWord;
    }

    public void setGermanWord(String germanWord) {
        this.germanWord = germanWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getWordImage() {
        return wordImage;
    }

    public void setWordImage(String wordImage) {
        this.wordImage = wordImage;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }



}
