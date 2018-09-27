package pl.kostrowski.codingame.puzzle.stockExchange;

import org.junit.Test;

import java.io.ByteArrayInputStream;

public class SolutionTest {
    @Test
    public void test1Main() {
        String data = "6\n" +
                "3\n" +
                "2\n" +
                "4\n" +
                "2\n" +
                "1\n" +
                "5\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Solution.main(null);

    }


}