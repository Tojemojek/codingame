package pl.kostrowski.codingame.puzzle.temperatures;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int result = Integer.MAX_VALUE;
        boolean correctInput = false;
        for (int i = 0; i < n; i++) {
            int t = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
            if (t >= -273 && t <= 5526) {
                correctInput = true;
            }
            System.err.print(t + " ");

            if (Math.abs(t) < Math.abs(result)) {
                result = t;
            } else if (Math.abs(t) == Math.abs(result) && t > 0) {
                result = t;
            }
        }
        result = correctInput ? result : 0;

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(result);
    }
}