package pl.kostrowski.codingame.puzzle.war;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class SolutionTest {


    @Before
    public void init() {
    }


    @Test
    public void test1Main() {
        String data = "3\n" +
                "AD\n" +
                "KC\n" +
                "QC\n" +
                "3\n" +
                "KH\n" +
                "QS\n" +
                "JC";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Solution.main(null);

    }

    @Test
    public void test2Main() {
        String data = "26\n5C\n3D\n2C\n7D\n8C\n7S\n5D\n5H\n6D\n5S\n4D\n6H\n6S\n3C\n3S\n7C\n4S\n4H\n7H\n4C\n2H\n6C\n8D\n3H\n2D\n2S\n26\nAC\n9H\nKH\nKC\nKD\nKS\n10S\n10D\n9S\nQD\nJS\n10H\n8S\nQH\nJD\nAD\nJC\nAS\nQS\nAH\nJH\n10C\n9C\n8H\nQC\n9D";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Solution.main(null);
    }


    @Test
    public void test3Main() {
        String data = "7\n10D\n9S\n8D\nKH\n7D\n5H\n6S\n7\n10H\n7H\n5C\nQC\n2C\n4H\n6D";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Solution.main(null);
    }


    @After
    public void teardown() {
        InputStream stdin = System.in;
        System.setIn(stdin);
    }
}
