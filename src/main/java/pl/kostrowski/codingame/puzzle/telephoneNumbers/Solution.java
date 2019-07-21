package pl.kostrowski.codingame.puzzle.telephoneNumbers;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

//https://www.codingame.com/ide/puzzle/telephone-numbers

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        String[] telephone = new String[N];
        for (int i = 0; i < N; i++) {
            telephone[i] = in.next();
        }

        Arrays.sort(telephone);
        System.err.println(N);
        for (String s : telephone) {
            System.err.println(s);
        }

        int numberOfnumber = telephone[0].length();

        for (int i = 1; i < telephone.length; i++){
            int commonStartingSubstring = findCommonStartingSubstring(telephone[i - 1], telephone[i]);
            if (telephone[i].length() > commonStartingSubstring){
                numberOfnumber+=telephone[i].length() - commonStartingSubstring;
            }
        }


        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");


        // The number of elements (referencing a number) stored in the structure.
        System.out.println(numberOfnumber);
    }

    private static int findCommonStartingSubstring(String s, String s1) {
        int ret = 0;
        int minLen = Math.min(s.length(), s1.length());

        for (int i = 0; i < minLen; i++){
            if (s.charAt(i) == s1.charAt(i)){
                ret++;
            } else {
                break;
            }
        }
        return ret;
    }
}