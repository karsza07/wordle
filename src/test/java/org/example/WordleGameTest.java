package org.example;
import org.example.WordleGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class WordleGameTest {
    String[] allWords = {
            "APPLE", "BEACH", "BRAIN", "BREAD", "CHAIR",
            "CLOCK", "DREAM", "EARTH", "FLAME", "GHOST",
            "HEART", "HOUSE", "LIGHT", "MUSIC", "PLANT",
            "RIVER", "SMILE", "TRAIN", "WATER", "WORLD" //20 slow
    };

    private WordleSolver solver = new WordleSolver(allWords);
    private WordleGame game = new WordleGame("Test", "WATER", solver);

    @Test
    @DisplayName("Metoda guessWord powinna rzucić IllegalArgumentException gdy słowo nie ma 5 liter")
    void shouldThrowIllegalArgumentException(){
        String word = "New";
        assertThrows(IllegalArgumentException.class, () -> {
            game.guessWord(word);
        });
    }

    @Test
    @DisplayName("guessWord powinno rzucic wyjatek jesli argumentem jest null")
    void shoulThrowExceptionIfArgumentNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            game.guessWord(null);
        });
    }

    @Test
    @DisplayName("Metoda guessWord powinna rzucić IllegalStateException przy przekroczeniu limitu prób")
    void shouldThrowIllegalStateException(){
        for (int i = 0; i < 6; i++) {
            game.guessWord("APPLE");
        }

        assertThrows(IllegalStateException.class, () -> {
            game.guessWord("APPLE");
            //to jest wyrazenie lambda
        });
    }


    @Test
    @DisplayName("metoda po podaniu poprawnego hasła powinna zwrócić wszystko na zielono")
    void shouldReturnAllGreenFeedback() {
        FeedbackValue[] feedback = game.guessWord("WATER");
        for (int i = 0; i < feedback.length; i++) {
            assertEquals(FeedbackValue.GREEN, feedback[i]);
        }
    }

    @Test
    @DisplayName("metoda sprawdza czy kolory dopasowują sie poprawnie")
    void shouldReturnDifferentColors() {
        FeedbackValue[] feedback = game.guessWord("SWEAR");
        assertEquals(FeedbackValue.GRAY, feedback[0]);
        assertEquals(FeedbackValue.YELLOW, feedback[1]);
        assertEquals(FeedbackValue.YELLOW, feedback[2]);
        assertEquals(FeedbackValue.YELLOW, feedback[3]);
        assertEquals(FeedbackValue.GREEN, feedback[4]);
        }
    }


