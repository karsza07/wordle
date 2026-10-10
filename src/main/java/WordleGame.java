public class WordleGame {
    private String[] allWords = {
            "APPLE", "BEACH", "BRAIN", "BREAD", "CHAIR",
            "CLOCK", "DREAM", "EARTH", "FLAME", "GHOST",
            "HEART", "HOUSE", "LIGHT", "MUSIC", "PLANT",
            "RIVER", "SMILE", "TRAIN", "WATER", "WORLD"
    };

    private String gameName;
    private String wordToGuess;
    private int guessNumber = 1;

    private String[] guesses;
    private String[] candidates;

    public WordleGame(String gameName, String wordToGuess){
        this.candidates = allWords; //na poacztku wszystko jest kopiowane do candidates
    }

    public FeedbackValue[] gueesWord(String guess){
        this.guessNumber++;
        if (guessNumber == 7){ //przy siódmej próbi nie zadziała
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
            else{
                if(guess.indexOf(wordToGuess.charAt(i)) != -1){  //jeśli litera sie znajduje w słowie to nie zwróci -1
                    feedback[i] = FeedbackValue.YELLOW;
                }
                else feedback[i] = FeedbackValue.GRAY; //jak nie ma litery nigdzie indziej to bedzie siwe
            }
        }
        return feedback;
    }

    void applyFeedback(String guess, FeedbackValue[] feedback){ //filtrowanie candidates
        for(String word : this.candidates){ //nie mozna usuwac elementu z tablicy wiec usuwany element jest zmieniany na null
            if(word != null){ //zielone musza byc dokładnie w tym samym miejscu
                FeedbackValue[] feedbackWord = gueesWord(word);
                for(int i=0; i<5; i++){
                    if (feedback[i] != feedbackWord[i]) {
                        this.candidates[i] = null;
                        break;}
                }
            }
        }
    }

    String[] getCandidates(){
        int indeks = 0;

        for(String word : this.candidates){ //usuwanie nullow
            if(word != null){
                indeks ++;
            }
        }
        String[] copy = new String[indeks];

        for(int i=0; i<indeks; i++){
            if(this.candidates[i] != null) copy[i] = this.candidates[i];
        }
        return copy;
    }

    private FeedbackValue giveFeedback(String wordToGuess, String guess){

    }
}
