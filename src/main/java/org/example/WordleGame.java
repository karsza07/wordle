package org.example;

public class WordleGame {
    private String[] allWords = {
            "APPLE", "BEACH", "BRAIN", "BREAD", "CHAIR",
            "CLOCK", "DREAM", "EARTH", "FLAME", "GHOST",
            "HEART", "HOUSE", "LIGHT", "MUSIC", "PLANT",
            "RIVER", "SMILE", "TRAIN", "WATER", "WORLD" //20 slow
    };

    private String gameName;
    private String wordToGuess;
    private int guessNumber = 1;

    private String[] guesses = new String[6];
    private String[] candidates;

    public WordleGame(String gameName, String wordToGuess) {
        this.candidates = allWords.clone(); //wszystkie slowa sa kopiowane do candidates
        this.gameName = gameName;
        this.wordToGuess = wordToGuess;
    }

    private FeedbackValue[] giveFeedback(String wordToGuess, String guess) {
        FeedbackValue[] feedback = new FeedbackValue[5];
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == wordToGuess.charAt(i)) { //porównuje literę ze slowa guess z literą wordToGuess
                feedback[i] = FeedbackValue.GREEN;
            } else {
                if (wordToGuess.indexOf(guess.charAt(i)) != -1) { //jeśli w hasle znajduje sie litera to nie zwróci -1
                    feedback[i] = FeedbackValue.YELLOW;
                } else {
                    feedback[i] = FeedbackValue.GRAY;
                } //jak nie ma litery nigdzie w słowie to bedzie szare
            }
        }
        return feedback;
    }

    void applyFeedback(String guess, FeedbackValue[] feedback) { // filtrowanie candidates
        for (int j = 0; j < this.candidates.length; j++) {
            String word = this.candidates[j];
            if (word != null) { //sprawdza czy to słowo nie zostało już odrzucone
                for (int i = 0; i < word.length(); i++) { //petla iteruje po wszystkich literach slowa szukanegp
                    if (feedback[i] == FeedbackValue.GREEN) { // zielone gdy litera jest dokładnie w tym samym miejscu
                        if (word.charAt(i) != guess.charAt(i)) {
                            this.candidates[j] = null;
                            break;
                        }
                    } else if (feedback[i] == FeedbackValue.YELLOW) { //zolte gdy litera jest w innym miejscu
                        if (word.indexOf(guess.charAt(i)) == -1 || word.charAt(i) == guess.charAt(i)) {
                            this.candidates[j] = null;
                            break;
                        }
                    } else {
                        if (word.indexOf(guess.charAt(i)) != -1) {
                            this.candidates[j] = null;
                            break;
                        }
                    }
                }
            }
        }
    }

    public FeedbackValue[] guessWord(String guess) {

        if (guessNumber == 7) { //przy siódmej próbi nie zadziała
            throw new IllegalStateException("you have exceeded the amount of guesses");
        }
        int len = guess.length();//jak nie ma tyle liter co trzeba no to nie zadziała
        if (len != 5) {
            throw new IllegalArgumentException("the word is the wrong length");
        }
        this.guesses[this.guessNumber - 1] = guess;
        this.guessNumber++;

        FeedbackValue[] feedback = giveFeedback(this.wordToGuess,guess);
        applyFeedback(guess, feedback);
        return feedback;
    }


    String[] getCandidates() {
        int length = 0;

        for (String word : this.candidates) {
            if (word != null) {
                length++;
            }
        }

        String[] copy = new String[length];
        int copyIndex = 0;

        for (String word : this.candidates) {
            if (word != null) {
                copy[copyIndex] = word;
                copyIndex++;
            }
        }

        return copy;
    }



}



