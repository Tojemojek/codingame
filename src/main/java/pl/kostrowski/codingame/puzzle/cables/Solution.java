package pl.kostrowski.codingame.puzzle.cables;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        Integer[] X = new Integer[N];
        Integer[] Y = new Integer[N];

        long sumY = 0;
        for (int i = 0; i < N; i++) {
            X[i] = in.nextInt();
            Y[i] = in.nextInt();
            sumY += Y[i];
        }

        Arrays.sort(X);
        Arrays.sort(Y);

        long lenX = Math.abs(X[0] - X[N-1]);
        System.err.println("lenx " + lenX);
        long medianY = Y[N/2];

        long lenY1 = calculateMinMedianYDist(medianY, Y);
        long lenY2 = calculateMinAvgYDist(sumY/N,  Y);
        System.err.println(lenY1 + " " + lenY2);
        long lenY = Math.min(lenY1,lenY2);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(lenX + lenY);
    }

    private static long calculateMinAvgYDist(long avg, Integer[] y) {

        long baseDist = 0;
        long baseDistMinus = 0;
        long baseDistPlus = 0;
        long bestScore;

        for (Integer integer : y) {
            baseDist += Math.abs(avg - integer);
            baseDistMinus += Math.abs((avg - 1)  - integer);
            baseDistPlus += Math.abs((avg + 1)  - integer);
        }

        bestScore = Math.min(Math.min(baseDist,baseDistMinus),baseDistPlus);

        return bestScore;
    }

    private static long calculateMinMedianYDist(long medianY, Integer[] y) {

        long baseDist = 0;
        long bestScore = 0;

        for (Integer integer : y) {
            baseDist += Math.abs(medianY - integer);
        }
        bestScore = baseDist;

        return bestScore;

    }
}