package org.example;

public class WordleGame {

    private final WordleSolver wordleSolver;
    private String gameName;
    private String wordToGuess;
    private int guessNumber = 1;
    private String[] guesses = new String[6];


    public WordleGame(String gameName, String wordToGuess, WordleSolver wordleSolver) {
        this.wordleSolver = wordleSolver;
        this.gameName = gameName;
        this.wordToGuess = wordToGuess;
    }


    public FeedbackValue[] guessWord(String guess) {

        if (guessNumber == 7) { //przy siódmej próbi nie zadziała
            throw new IllegalStateException("you have exceeded the amount of guesses");
        }

        if(guess == null){
            throw new IllegalArgumentException("guess cannot be null");
        }

        int len = guess.length();//jak nie ma tyle liter co trzeba no to nie zadziała
        if (len != 5) {
            throw new IllegalArgumentException("the word is the wrong length");
        }
        this.guesses[this.guessNumber - 1] = guess;
        this.guessNumber++;

        FeedbackValue[] feedback = wordleSolver.giveFeedback(this.wordToGuess,guess);
        wordleSolver.applyFeedback(guess, feedback);
        return feedback;
    }
}



