package pl.kostrowski.codingame.puzzle.telephoneNumbers;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class SolutionTest {

    @Before
    public void setUp() throws Exception {

        String data = "2\n" +
                "0123456789\n" +
                "0123";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    public void main() {

        Solution.main(null);

    }
}