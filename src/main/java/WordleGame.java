public class WordleGame {
    private String[] allWords = {
            "APPLE", "BEACH", "BRAIN", "BREAD", "CHAIR",
            "CLOCK", "DREAM", "EARTH", "FLAME", "GHOST",
            "HEART", "HOUSE", "LIGHT", "MUSIC", "PLANT",
            "RIVER", "SMILE", "TRAIN", "WATER", "WORLD"
    };

    private String gameName;
    private String wordToGuess;
    private int guessNumber = 0;

    private String[] guesses;
    private String[] candidates;

    public WordleGame(String gameName, String wordToGuess){
        this.candidates = allWords; //na poacztku wszystko jest kopiowane do candidates
    }

    public FeedbackValue[] gueesWord(String guess){
        this.guessNumber++;
        if (guessNumber == 7){
            throw new IllegalStateException("you have exceeded the amount of guesses");
        }
        int len = guess.length();
        if(len != 5){
            throw new IllegalArgumentException("the word is the wrong length");
        }

        FeedbackValue[] feedback = new FeedbackValue[5];;
        for(int i=0; i<=5; i++){
            if(guess.charAt(i) == wordToGuess.charAt(i)){
                feedback[i] = FeedbackValue.GREEN;
            }
        }

    }

    void applyFeedback(String guess, FeedbackValue[] feedback){

    }

    String[] getCandidates(){

    }

    private FeedbackValue giveFeedback(String wordToGuess, String guess){

    }
}
