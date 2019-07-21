package pl.kostrowski.codingame.puzzle.benderEpisode1;

import org.junit.Test;

import java.io.ByteArrayInputStream;

public class SolutionTest {

    @Test
    public void main() {
        String data = "10 10\n" +
                "##########\n" +
                "#        #\n" +
                "#  S   W #\n" +
                "#        #\n" +
                "#  $     #\n" +
                "#        #\n" +
                "#@       #\n" +
                "#        #\n" +
                "#E     N #\n" +
                "##########\n";


        String data1 = "5 5\n" +
                "#####\n" +
                "#@  #\n" +
                "#   #\n" +
                "#  $#\n" +
                "#####";

        String data2 ="8 8\n" +
                "########\n" +
                "# @    #\n" +
                "#     X#\n" +
                "# XXX  #\n" +
                "#   XX #\n" +
                "#   XX #\n" +
                "#     $#\n" +
                "########";

        String data3 = "10 10\n" +
                "##########\n" +
                "#    T   #\n" +
                "#        #\n" +
                "#        #\n" +
                "#        #\n" +
                "#@       #\n" +
                "#        #\n" +
                "#        #\n" +
                "#    T  $#\n" +
                "##########";

        String data4 = "15 15\n" +
                "###############\n" +
                "#      IXXXXX #\n" +
                "#  @          #\n" +
                "#             #\n" +
                "#             #\n" +
                "#  I          #\n" +
                "#  B          #\n" +
                "#  B   S     W#\n" +
                "#  B   T      #\n" +
                "#             #\n" +
                "#         T   #\n" +
                "#         B   #\n" +
                "#            $#\n" +
                "#        XXXX #\n" +
                "###############";
        System.setIn(new ByteArrayInputStream(data4.getBytes()));

        Solution.main(null);
    }
}