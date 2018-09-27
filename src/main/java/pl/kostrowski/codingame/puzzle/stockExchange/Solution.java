package pl.kostrowski.codingame.puzzle.stockExchange;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private static LinkedList<Integer> wyniki = new LinkedList<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
//        System.err.println(n);
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
//            System.err.println(v);
            wyniki.add(v);
        }

        int min = wyniki.getFirst();
        int max = wyniki.getFirst();
        int loss = max - min;

        int localMin = wyniki.getFirst();
        int localMax = wyniki.getFirst();
        int localLoss = localMax - localMin;


        for (Integer integer : wyniki) {
            if (localMin > integer) {
                localMin = integer;
                localLoss = localMin - localMax;
            }
            if (integer < min) {
                min = integer;
                loss = min - max;
            } else if (integer > localMax) {
                localMax = integer;
                localMin = integer;
                localLoss = localMin - localMax;
            }

            if (localLoss < loss) {
                min = localMin;
                max = localMax;
                loss = localLoss;
            }
//            System.err.println("Min:" + min + " max + " + max + " loss " + loss);
//            System.err.println("localMin:" + localMin + " localmax + " + localMax + " llocaloss " + localLoss);
        }

        String result = "";
        if (loss < 0) {
            result = "" + loss;
        } else {
            result = "0";
        }


        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(result);
    }
}