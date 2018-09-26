package pl.kostrowski.codingame.puzzle.war;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private static LinkedList<Card> playerOne = new LinkedList<>();
    private static LinkedList<Card> playerTwo = new LinkedList<>();
    private static int noOfRounds = 0;

    public static void main(String args[]) {
        System.err.println("input rip");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of cards for player 1
        System.err.println(n);
        for (int i = 0; i < n; i++) {
            String cardp1 = in.next(); // the n cards of player 1
            System.err.println(cardp1);
            playerOne.add(new Card(cardp1));
        }
        int m = in.nextInt(); // the number of cards for player 2
        System.err.println(n);
        for (int i = 0; i < m; i++) {
            String cardp2 = in.next(); // the m cards of player 2
            System.err.println(cardp2);
            playerTwo.add(new Card(cardp2));
        }
        System.err.println("Rip off");

        String s = playWar(playerOne, playerTwo);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(s);
    }

    private static String playWar(LinkedList<Card> playerOne, LinkedList<Card> playerTwo) {
        String result = "";
        LinkedList<Card> first = new LinkedList<>();
        LinkedList<Card> second = new LinkedList<>();

        boolean play = true;


        while (playerOne.size() > 0 && playerTwo.size() > 0 && play) {

            System.err.println("Player one size " + playerOne.size() + playerOne);
            System.err.println("Player two size " + playerOne.size() + playerTwo);

            first.add(playerOne.getFirst());
            playerOne.removeFirst();

            second.add(playerTwo.getFirst());
            playerTwo.removeFirst();

            System.err.println("First " + first.getLast() + " second " + second.getLast());
            System.err.println(noOfRounds);
            if (first.getLast().value > second.getLast().value) {
                playerOne.addAll(first);
                playerOne.addAll(second);
                first.clear();
                second.clear();
                noOfRounds++;
            } else if (first.getLast().value < second.getLast().value) {
                playerTwo.addAll(first);
                playerTwo.addAll(second);
                first.clear();
                second.clear();
                noOfRounds++;
            } else {
                if (playerOne.size() < 3 || playerTwo.size() < 3) {
                    result = "PAT";
                    play = false;
                    break;
                }
                for (int k = 0; k < 3; k++) {
                    first.add(playerOne.getFirst());
                    playerOne.removeFirst();
                    second.add(playerTwo.getFirst());
                    playerTwo.removeFirst();
                }

            }

        }

        if (play) {
            if (playerOne.size() > 0) {
                result = 1 + " " + noOfRounds;
            } else if (playerTwo.size() > 0) {
                result = 2 + " " + noOfRounds;
            }
        }

        return result;
    }
}


class Card {
    String fullName;
    Integer value;

    public Card(String fullName) {
        this.fullName = fullName;
        value = calculateValue(fullName);
    }

    @Override
    public String toString() {
        return "pl.kostrowski.codingame.puzzle.war.Card{" +
                "fullName='" + fullName + '\'' +
                ", value=" + value +
                '}';
    }

    private Integer calculateValue(String fullName) {
        String substring = fullName.substring(0, 1);
        if (fullName.length() == 3) {
            substring = fullName.substring(0, 2);
        }
        switch (substring) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.valueOf(substring);
        }
    }
}