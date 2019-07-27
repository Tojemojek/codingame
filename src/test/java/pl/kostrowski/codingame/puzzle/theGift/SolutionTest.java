package pl.kostrowski.codingame.puzzle.theGift;

import org.junit.Test;

import java.io.ByteArrayInputStream;

public class SolutionTest {

    @Test
    public void main() {
        String data = "3\n" +
                "100\n" +
                "40\n" +
                "40\n" +
                "40\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Solution.main(null);
    }
}