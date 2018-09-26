package pl.kostrowski.codingame.puzzle.asciiart;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private static Map<String, AsciLetter> literki = new HashMap<>();
    private static ArrayList<String> litery = new ArrayList<>();

    public static void main(String args[]) {
        init();

        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String T = in.nextLine().toUpperCase();
        // System.err.println(T);
        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            // System.err.println(ROW);
            int j = 0;
            for (String s : litery) {
                String substring = ROW.substring(j * L, (j + 1) * L);
                literki.get(s).text.add(substring);
                j++;
            }
        }

        String result = "";
        for (int j = 0; j < H; j++) {
            for (int i = 0; i < T.length(); i++) {
                String literka = T.substring(i, i + 1);
                if (!literki.containsKey(literka)) {
                    literka = "?";
                }
                result = result + literki.get(literka).text.get(j);
            }
            result = result + "\n";
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(result);
    }


    private static void init() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ?".toCharArray();
        for (char aChar : chars) {
            String s = Character.toString(aChar);
            litery.add(s);
            literki.put(s, new AsciLetter());
        }
    }


    static class AsciLetter {
        List<String> text;

        public AsciLetter() {
            this.text = new ArrayList<>();
        }
    }


//    Line 1: the width L of a letter represented in ASCII art. All letters are the same width.
//
//    Line 2: the height H of a letter represented in ASCII art. All letters are the same height.
//
//    Line 3: The line of text T, composed of N ASCII characters.
//
//    Following lines: the string of characters ABCDEFGHIJKLMNOPQRSTUVWXYZ? Represented in ASCII art.

}