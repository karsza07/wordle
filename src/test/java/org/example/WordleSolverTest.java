package org.example;
import org.example.WordleGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class WordleSolverTest {
    String[] allWords = {
            "APPLE", "BEACH", "BRAIN", "BREAD", "CHAIR",
            "CLOCK", "DREAM", "EARTH", "FLAME", "GHOST",
            "HEART", "HOUSE", "LIGHT", "MUSIC", "PLANT",
            "RIVER", "SMILE", "TRAIN", "WATER", "WORLD" //20 slow
    };

    public WordleSolver solver = new WordleSolver(allWords);
    private WordleGame game = new WordleGame("Test", "WATER", solver);


    @Test
    @DisplayName("testowanie czy metoda getCandidates nie zwraca nulli")
    void shouldReturnCandidtesWithoutNull() {
        String[] candidates = solver.getCandidates();
        for (int i = 0; i < candidates.length; i++) {
            assertNotNull(candidates[i]);
        }
    }

    @Test
    @DisplayName("testowanie czy metoda getCandidates napewno zwraca kopie")
    void shouldReturnCopy() {
        String[] can1 = solver.getCandidates();
        String[] can2 = solver.getCandidates();
        assertNotSame(can1, can2);
    }

    @Test
    @DisplayName("sprawdzający, czy po podaniu próby liczba pozostałych słów w getCandidates() faktycznie się zmniejsza.")
    void shouldReturnLessCandidates() {
        String[] can1 = solver.getCandidates();
        game.guessWord("APPLE"); //metoda guess word wywyołuje apply feedback
        String[] can2 = solver.getCandidates();
        assertTrue(can1.length > can2.length);
    }
}
