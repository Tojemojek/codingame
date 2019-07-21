package pl.kostrowski.codingame.puzzle.horseduels;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Integer[] all = new Integer[N];
        for (int i = 0; i < N; i++) {
            all[i] = in.nextInt();
        }

        Arrays.sort(all);

        int diff = Integer.MAX_VALUE;
        if (N > 1) {
            diff = all[1] - all[0];

            for (int i = 1; i < N - 1; i++) {
                if (all[i + 1] - all[i] < diff) {
                    diff = all[i + 1] - all[i];
                }
            }

        }


        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(diff);
    }
}