package org.example;
import org.example.WordleGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class WordleGameTest {
    private WordleGame game = new WordleGame("Test", "WATER");

    @Test
    @DisplayName("Metoda guessWord powinna rzucić IllegalArgumentException gdy słowo nie ma 5 liter")
    void shouldThrowIllegalArgumentException(){
        String word = "New";
        assertThrows(IllegalArgumentException.class, () -> {
            game.guessWord(word);
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
    @DisplayName("testowanie czy metoda getCandidates nie zwraca nulli")
    void shouldReturnCandidtesWithoutNull() {
        String[] candidates = game.getCandidates();
        for (int i = 0; i < candidates.length; i++) {
            assertNotNull(candidates[i]);
        }
    }

    @Test
    @DisplayName("testowanie czy metoda getCandidates napewno zwraca kopie")
    void shouldReturnCopy() {
        String[] can1 = game.getCandidates();
        String[] can2 = game.getCandidates();
        assertNotSame(can1, can2);
    }

    @Test
    @DisplayName("sprawdzający, czy po podaniu próby liczba pozostałych słów w getCandidates() faktycznie się zmniejsza.")
    void shouldReturnLessCandidates() {
        String[] can1 = game.getCandidates();
        game.guessWord("APPLE"); //metoda guess word wywyołuje apply feedback
        String[] can2 = game.getCandidates();
        assertTrue(can1.length > can2.length);
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


