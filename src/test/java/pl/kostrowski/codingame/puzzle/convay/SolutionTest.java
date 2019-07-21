package pl.kostrowski.codingame.puzzle.convay;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class SolutionTest {


    @Before
    public void init() {
        String data = "2\n" +
                "1\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }


    @Test
    public void main() {

        Solution.main(null);


    }
}