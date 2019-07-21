package pl.kostrowski.codingame.puzzle.onShouldersOfGiants;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

// https://www.codingame.com/ide/puzzle/dwarfs-standing-on-the-shoulders-of-giants

class Solution {

    private static int maxPers = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of relationships of influence

        Map<Integer, Set<Integer>> children = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x = in.nextInt(); // a relationship of influence between two people (x influences y)
            int y = in.nextInt();

            if (children.containsKey(x)) {
                Set<Integer> integers = children.get(x);
                integers.add(y);
            } else {
                Set<Integer> tmp = new HashSet<>();
                tmp.add(y);
                children.put(x, tmp);
            }
        }

        Set<Integer> parents = children.keySet();

        for (Integer parent : parents) {
           printPaths(parent, 1, children);
        }


        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");


        // The number of people involved in the longest succession of influences
        System.out.println(maxPers);
    }

    private static void printPaths(Integer parent, int influenced, Map<Integer, Set<Integer>> children) {
        Set<Integer> newParents = children.get(parent);
        if (newParents != null) {
            for (Integer newParent : newParents) {
                int tmpinfluenced = influenced + 1;
                printPaths(newParent, tmpinfluenced, children);
            }
        } else {
            System.err.println(influenced);
            if (influenced > maxPers) {
                maxPers = influenced;
            }
        }
    }
}