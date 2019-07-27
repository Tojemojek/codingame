package pl.kostrowski.codingame.puzzle.scrabble;

import org.junit.Test;

import java.io.ByteArrayInputStream;

public class SolutionTest {

    @Test
    public void main() {
        String data = "5\n" +
                "because\n" +
                "first\n" +
                "these\n" +
                "could\n" +
                "which\n" +
                "hicquwh";

        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Solution.main(null);
    }
}