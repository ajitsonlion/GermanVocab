package flashcards.vocab.com.germanvocab.parser;

import java.util.ArrayList;

/**
 * Created by ajit on 19.11.14.
 */
public class WordModelForLetter {

    private String letter;
    private ArrayList<flashCard> flashCardCollectionForLetter= new ArrayList<flashCard>();


    public ArrayList<flashCard> getFlashCardCollectionForLetter() {
        return flashCardCollectionForLetter;
    }

    public void setFlashCardCollectionForLetter(ArrayList<flashCard> flashCardCollectionForLetter) {
        this.flashCardCollectionForLetter = flashCardCollectionForLetter;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }



}



class flashCard{

    private int wordId;
    private String germanWord;
    private String englishWord;
    private String wordImage;
    private String article;

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



}
