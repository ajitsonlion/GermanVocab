package flashcards.vocab.com.germanvocab.parser;


import java.util.ArrayList;

/**
 * Created by ajit on 19.11.14.
 */



public class FlashCard  {

    private int wordId;
    private String germanWord;
    private String englishWord;
    private String wordImage;
    private String article;
    private String letter;


    public int getFreqInGerman() {
        return freqInGerman;
    }

    public void setFreqInGerman(int freqInGerman) {
        this.freqInGerman = freqInGerman;
    }

    private int freqInGerman;


    public ArrayList<String> getImagesForWord() {
        return imagesForWord;
    }

    public void setImagesForWord(ArrayList<String> imagesForWord) {
        this.imagesForWord = imagesForWord;
    }

    private ArrayList<String> imagesForWord=new ArrayList<String>();

    private ArrayList<WordToSentence> exampleSentence=new ArrayList<WordToSentence>();


    public ArrayList<WordToSentence> getExampleSentence() {
        return exampleSentence;
    }

    public void setExampleSentence(ArrayList<WordToSentence> exampleSentence) {
        this.exampleSentence = exampleSentence;
    }


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


class WordToSentence{

    public String getGermanSentence() {
        return germanSentence;
    }

    public void setGermanSentence(String germanSentence) {
        this.germanSentence = germanSentence;
    }

    public String getEnglishSentence() {
        return englishSentence;
    }

    public void setEnglishSentence(String englishSentence) {
        this.englishSentence = englishSentence;
    }

    private String germanSentence="";

    private String englishSentence="";




}
