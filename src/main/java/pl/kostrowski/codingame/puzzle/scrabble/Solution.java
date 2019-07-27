package pl.kostrowski.codingame.puzzle.scrabble;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
//https://www.codingame.com/ide/puzzle/scrabble
class Solution {
    
    private static final Map<String,Integer> letterValues = init();
    private static final List<String> allowedWords = new LinkedList<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        System.err.println(N);

        if (in.hasNextLine()) {
            in.nextLine();
        }

        for (int i = 0; i < N; i++) {
            String word = in.nextLine();
            System.err.println(word);
            if (word.length() <= 7){
                allowedWords.add(word);
            }
        }
        String letters = in.nextLine();
        List<String> listOfLetters = createListOfLetters(letters);
        System.err.println(letters);

        String bestWord ="";
        int bestValue = 0;


        for (String allowedWord : allowedWords) {
            System.err.println(calculateValue(allowedWord));
        }


        for (String allowedWord : allowedWords) {
            if (checkIfPossible(allowedWord, listOfLetters)){
                int tmpValue = calculateValue(allowedWord);
                if (tmpValue > bestValue){
                    bestWord = allowedWord;
                    bestValue = tmpValue;
                }
            }
        }


        System.out.println(bestWord);



        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

//        System.out.println("invalid word");
    }

    private static List<String> createListOfLetters(String letters) {
        List<String> ret = new ArrayList<>();
        for (char c : letters.toCharArray()) {
            ret.add(Character.toString(c));
        }
        return ret;
    }

    private static boolean checkIfPossible(String allowedWord, List<String> letters) {
        List<String> tmp = new LinkedList<>(letters);
        List<String> lettersFromWord = new LinkedList<>();

        for (char c : allowedWord.toCharArray()) {
            lettersFromWord.add(Character.toString(c));
        }

        for (String s : lettersFromWord) {
            if (tmp.contains(s)){
                tmp.remove(s);
            } else {
                return false;
            }
        }

        return true;
    }


    private static int calculateValue(String allowedWord) {
        int ret = 0;
        char[] chars = allowedWord.toCharArray();
        for (char aChar : chars) {
            ret += letterValues.get(Character.toString(aChar));
        }
        return ret;
    }


    private static  Map<String,Integer>  init(){
        Map<String,Integer> ret = new HashMap<>();
        ret.put("e",1);
        ret.put("a",1);
        ret.put("i",1);
        ret.put("o",1);
        ret.put("n",1);
        ret.put("r",1);
        ret.put("t",1);
        ret.put("l",1);
        ret.put("s",1);
        ret.put("u",1);
        ret.put("d",2);
        ret.put("g",2);
        ret.put("b",3);
        ret.put("c",3);
        ret.put("m",3);
        ret.put("p",3);
        ret.put("f",4);
        ret.put("h",4);
        ret.put("v",4);
        ret.put("w",4);
        ret.put("y",4);
        ret.put("k",5);
        ret.put("j",8);
        ret.put("x",8);
        ret.put("q",10);
        ret.put("z",10);

        return ret;
    }
}














