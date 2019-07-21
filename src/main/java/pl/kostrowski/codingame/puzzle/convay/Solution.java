package pl.kostrowski.codingame.puzzle.convay;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

//https://www.codingame.com/ide/puzzle/conway-sequence

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        int L = in.nextInt();

        LinkedList<Line> linie = new LinkedList<>();

        linie.add(new Line(R));
        linie.add(new Line(1, R));

        for (int i = 2; i < L; i++) {
            linie.add(countNextLine(linie.getLast()));
        }
        int i =0;
        for (Line line : linie) {
            i++;
            System.err.println("Linia nr " + i + " : " + line);
        }


        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(linie.get(L-1).toString());
    }

    private static Line countNextLine(Line last) {
        LinkedList<Integer> list = last.getList();
        LinkedList<Integer> nextLine = new LinkedList<>();

        int value = list.getFirst();
        int repetition = 1;

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                repetition++;
            } else {
                nextLine.add(repetition);
                nextLine.add(value);
                value = list.get(i + 1);
                repetition = 1;
            }
        }
        nextLine.add(repetition);
        nextLine.add(value);

        return new Line(nextLine.toArray(new Integer[0]));
    }
}

class Line {

    LinkedList<Integer> list;

    public Line(Integer... integers) {
        this.list = new LinkedList<>();

        for (Integer integer : integers) {
            this.list.add(integer);
        }
    }

    public LinkedList<Integer> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Integer integer : this.list) {
            sb.append(integer).append(" ");
        }

        return sb.substring(0, sb.length() - 1);
    }
}