package pl.kostrowski.codingame.puzzle.theGift;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
//https://www.codingame.com/ide/puzzle/the-gift
class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int participantNumber = in.nextInt();
        int price = in.nextInt();

        long totalBudgets = 0;

        int[] budgets = new int[participantNumber];
        for (int i = 0; i < participantNumber; i++) {
            budgets[i] = in.nextInt();
            totalBudgets += budgets[i];
        }

        System.err.println(participantNumber);
        System.err.println(price);
        System.err.println(Arrays.toString(budgets));

        Arrays.sort(budgets);

        String ret;
        if (price > totalBudgets) {
            ret = "IMPOSSIBLE";
        } else {
            ret = splitIt(price, budgets, totalBudgets, participantNumber);
        }


        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(ret);
    }

    private static String splitIt(int price, int[] budgets, long totalBudgets, int participantNumber) {

        int left = price;
        int avg = price / participantNumber;

        StringBuilder sb = new StringBuilder();

        for (int budget : budgets) {
            if (budget <= avg && participantNumber > 1) {
                left -= budget;
                sb.append(budget).append("\n");
            } else if (participantNumber > 1){
                left -= avg;
                sb.append(avg).append("\n");
            } else {
                sb.append(left);
            }
            participantNumber--;
            if (participantNumber > 0) {
                avg = left / participantNumber;
            }
        }


        return sb.toString();
    }
}