package pl.kostrowski.codingame.puzzle.Norris;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String MESSAGE = in.nextLine();

        byte[] bytes = MESSAGE.getBytes();

        String s = "";

        for (byte aByte : bytes) {
            Integer integer = (int) aByte;
            String s1 = Integer.toString(integer, 2);
            String s2 = "";
            for (int i = s1.length(); i < 7; i++) {
                s2 = s2 + "0";
            }
            s = s + s2 + s1;
        }
        System.err.println(s);
        String codeit = codeit(s);


        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(codeit);
    }

    private static String codeit(String string) {

        String result = "";
        String sign = "";
        String howMany = "0";

        char[] chars = string.toCharArray();


        for (int i = 0; i < chars.length - 1; i++) {
            System.err.println("Znak: " + chars[i]);
            sign = chars[i] == '0' ? "00" : "0";
            System.err.println("Kod znaku: " + sign);

            if (chars[i] == chars[i + 1]) {
                howMany = howMany + "0";
                System.err.println("howMany: " + howMany);
            } else {
                result = result + sign + " " + howMany + " ";
                howMany = "0";
                System.err.println("result: " + result);
            }
        }

        if (chars[chars.length - 1] == chars[chars.length - 2]) {
            result = result + sign + " " + howMany;
        } else {
            sign = chars[chars.length - 1] == '0' ? "00" : "0";
            result = result + sign + " " + "0";
        }

        return result;
    }

}