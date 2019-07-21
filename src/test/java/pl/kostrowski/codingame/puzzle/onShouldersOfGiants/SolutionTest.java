package pl.kostrowski.codingame.puzzle.onShouldersOfGiants;

import org.junit.Test;

import java.io.ByteArrayInputStream;

public class SolutionTest {

    @Test
    public void main() {
        String data = "3\n" +
                "1 2\n" +
                "1 3\n" +
                "3 4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Solution.main(null);


    }
}